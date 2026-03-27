package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.block.ModBlocks;
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
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class Level7ChunkGen extends ChunkGenerator {

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int minY = getMinY();

		RandomSource chunkId = randomFactory.fromSeed(x * 341873128712L + z * 132897987541L + y * 1328712L);

		// Bedrock Floor
		if (y == minY) {
			return Blocks.BEDROCK.defaultBlockState();
		}

		// Main Deepslate Layer
		if (y < 20) {
			boolean isErrorSlate = (chunkId.nextIntBetweenInclusive(1, 80) == 1);
			if (isErrorSlate) {
				return ModBlocks.ERRORSLATE.defaultBlockState();
			} else {
				return Blocks.DEEPSLATE.defaultBlockState();
			}
		}

		// Secondary Deepslate Layer
		if (y < 40) {
			boolean isDeepSlate = (chunkId.nextIntBetweenInclusive(1, 60 - y) == 1);
			if (isDeepSlate) {
				return Blocks.DEEPSLATE.defaultBlockState();
			}
		}

		// Water
		if (y < this.getSeaLevel()) {
			return Blocks.WATER.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level7ChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
	}

	public static final MapCodec<Level7ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL7_OCEAN_BIOME)).apply(instance,
					instance.stable(Level7ChunkGen::new)));

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
		return 304;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));
		int minY = getMinY();
		int maxY = minY + getGenDepth();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = minY; y < maxY; y++) {
					BlockState block = getBlockAt(worldSeed, globalX, y, globalZ);
					chunkAccess.setBlockState(
							new BlockPos(x, y, z),
							block,
							0);
				}
			}
		}
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getSeaLevel() {
		return 200;
	}

	@Override
	public int getMinY() {
		return -64;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		return this.getMinY() + 1;
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height; y++) {
			blocks[y] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
