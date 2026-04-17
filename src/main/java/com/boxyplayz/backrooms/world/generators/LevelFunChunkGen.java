package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.biomesources.LevelFunBiomeSource;
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

public class LevelFunChunkGen extends ChunkGenerator {

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

		RandomSource chunkRandom = randomFactory.at((int) chunkX, 0, (int) chunkZ);

		if (biome.is(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME)) {
			if (y <= 0) {
				return ModBlocks.FUN_FLOOR.defaultBlockState();
			}
			if (y >= 6) {
				if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}
			BlockState chunkBaseWallBlock;
			switch (chunkRandom.nextIntBetweenInclusive(1, 4)) {
				case 1:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;

				case 2:
					chunkBaseWallBlock = ModBlocks.FUN_GREEN.defaultBlockState();
					break;

				case 3:
					chunkBaseWallBlock = ModBlocks.FUN_PINK.defaultBlockState();
					break;

				case 4:
					chunkBaseWallBlock = ModBlocks.FUN_PURPLE.defaultBlockState();
					break;

				default:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;
			}

			short cellX = (short) Math.floorDiv(Math.floorMod(x, 16), 4);
			short cellZ = (short) Math.floorDiv(Math.floorMod(z, 16), 4);
			int localX = Math.abs(Math.floorMod(x, 4));
			int localZ = Math.abs(Math.floorMod(z, 4));
			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					0,
					(int) (chunkZ * 4 + cellZ));
			if (getRandomBool(cellRandom) && localZ == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localZ == 3) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localX == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localX == 3) {
				return chunkBaseWallBlock;
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public LevelFunChunkGen(Holder.Reference<Biome> hallways, Holder.Reference<Biome> playrooms,
			Holder.Reference<Biome> trampoline_park, Holder.Reference<Biome> partyrooms) {
		super(new LevelFunBiomeSource(hallways, playrooms, trampoline_park, partyrooms));
	}

	public static final MapCodec<LevelFunChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PLAYROOMS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.TRAMPOLINE_PARK_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME))
					.apply(instance,
							instance.stable(LevelFunChunkGen::new)));

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
		return 128;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

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
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

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
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

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
