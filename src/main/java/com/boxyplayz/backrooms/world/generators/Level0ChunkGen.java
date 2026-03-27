package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biomesources.Level0BiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class Level0ChunkGen extends ChunkGenerator {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z, Holder<Biome> biome) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		RandomSource random = randomFactory.at(new BlockPos(x, y, z));

		int relativeChunkX = Math.floorMod(x, 16) + 1;
		int relativeChunkZ = Math.floorMod(z, 16) + 1;

		// Floor
		if (y <= 0) {
			if (biome.is(ModBiomes.Level0Biomes.PITFALLS_BIOME)) {
				if ((relativeChunkX / 2 % 2) == 0 && (relativeChunkZ / 2 % 2) == 0) {
					return Blocks.AIR.defaultBlockState();
				} else {
					return ModBlocks.LEVEL0_CARPET.defaultBlockState();
				}
			} else {
				if (y == 0 && random.nextIntBetweenInclusive(0, 800) == 0) {
					return ModBlocks.LEVEL0_CARPET_GLITCHED.defaultBlockState();
				}
				return ModBlocks.LEVEL0_CARPET.defaultBlockState();
			}
		}

		// Ceiling
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2
					&& !(biome.is(ModBiomes.Level0Biomes.BLACKOUT_BIOME))) {
				return ModBlocks.LEVEL0_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.LEVEL0_CEILING_TILE.defaultBlockState();
		}

		// Maze logic start!
		if (biome.is(ModBiomes.Level0Biomes.COLUMNS_BIOME)) {
			if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
				return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
			}
		} else {
			if (biome.is(ModBiomes.Level0Biomes.BLACKOUT_BIOME) || biome.is(ModBiomes.Level0Biomes.NORMAL_BIOME)) {
				int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
				int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

				int localX = Math.abs(Math.floorMod(x, 4));
				int localZ = Math.abs(Math.floorMod(z, 4));

				RandomSource cellRandom = randomFactory.at(
						(int) (chunkX * 4 + cellX),
						0,
						(int) (chunkZ * 4 + cellZ));

				if (getRandomBool(cellRandom) && localZ == 0) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localZ == 3) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localX == 0) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localX == 3) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level0ChunkGen(Holder.Reference<Biome> normal, Holder.Reference<Biome> columns,
			Holder.Reference<Biome> blackout, Holder.Reference<Biome> pitfalls) {
		super(new Level0BiomeSource(normal, columns, blackout, pitfalls));
	}

	public static final MapCodec<Level0ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.NORMAL_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.COLUMNS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.BLACKOUT_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.PITFALLS_BIOME))
					.apply(instance,
							instance.stable(Level0ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager,
			StructureManager structureManager, ChunkAccess chunkAccess) {
	}

	@Override
	public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState,
			ChunkAccess chunkAccess) {
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
	}

	@Override
	public int getGenDepth() {
		return 32;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		int minY = getMinY();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = minY; y < minY + this.getGenDepth(); y++) {
					Holder<Biome> biome = chunkAccess.getNoiseBiome(globalX, y, globalZ);
					BlockState block = getBlockAt(worldSeed, globalX, y, globalZ, biome);
					chunkAccess.setBlockState(
							new BlockPos(x, y, z),
							block,
							0);
				}
			}
		}

		chunkAccess.getOrCreateHeightmapUnprimed(Types.WORLD_SURFACE_WG);
		chunkAccess.getOrCreateHeightmapUnprimed(Types.OCEAN_FLOOR_WG);
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getMinY() {
		return -16;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		for (int y = getMinY() + getGenDepth() - 1; y >= getMinY(); y--) {
			if (!getBlockAt(worldSeed, x, y, z).isAir()) {
				return y + 1;
			}
		}

		return this.getMinY();
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
