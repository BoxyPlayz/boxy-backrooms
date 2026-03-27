package com.boxyplayz.backrooms.chunkgen.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.chunkgen.biomesources.Level1BiomeSource;
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
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class Level1ChunkGen extends ChunkGenerator {

	private SimplexNoise noise;

	protected SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromSeed(1);
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	public BlockState getBlockAt(SimplexNoise localNoise, PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(localNoise, randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(SimplexNoise localNoise, PositionalRandomFactory randomFactory, int x, int y, int z,
			Holder<Biome> biome) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		// Floor
		if (Math.floorMod(y, 7) == 0) {
			return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
		}

		// Ceiling
		if (Math.floorMod(y, 7) == 6) {
			return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
		}

		// Fallback
		if ((Math.floorMod(x, 8) == 0 || Math.floorMod(x, 8) == 1)
				&&
				(Math.floorMod(z, 8) == 0 || Math.floorMod(z, 8) == 1)) {
			return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
		}

		if (Math.floorMod(y, 7) == 1) {
			RandomSource random = randomFactory.at(x, y, z);
			if (random.nextIntBetweenInclusive(0, 1000) == 1) {
				return ModBlocks.LEVEL1_CRATE.defaultBlockState();
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level1ChunkGen(Holder.Reference<Biome> aquila, Holder.Reference<Biome> garden,
			Holder.Reference<Biome> fabled, Holder.Reference<Biome> ouroboros, Holder.Reference<Biome> gothic,
			Holder.Reference<Biome> gilded) {
		super(new Level1BiomeSource(aquila, garden, fabled, ouroboros, gothic, gilded));
	}

	public static final MapCodec<Level1ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.AQUILA_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GARDEN_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.FABLED_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.OUROBOROS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GOTHIC_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GILDED_BIOME)).apply(instance,
							instance.stable(Level1ChunkGen::new)));

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
		return 64;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		int minY = getMinY();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = minY; y < minY + this.getGenDepth(); y++) {
					Holder<Biome> biome = chunkAccess.getNoiseBiome(globalX, y, globalZ);
					BlockState block = getBlockAt(this.getNoise(worldSeed), worldSeed, globalX, y, globalZ, biome);
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
		return 0;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		for (int y = getMinY() + getGenDepth() - 1; y >= getMinY(); y--) {
			if (!getBlockAt(this.getNoise(worldSeed), worldSeed, x, y, z).isAir()) {
				return y + 1;
			}

		}

		return this.getMinY();
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(this.getNoise(worldSeed), worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
