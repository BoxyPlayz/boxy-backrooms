package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

/**
 * Chunk Generator class to eliminate boilerplate code
 */
public abstract class BaseChunkGen extends ChunkGenerator {

	public BaseChunkGen(BiomeSource biomeSource) {
		super(biomeSource);
	}

	@Override
	public void applyCarvers(WorldGenRegion region, long seed, RandomState randomState, BiomeManager biomeManager,
			StructureManager structureManager, ChunkAccess chunk) {
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public void addDebugScreenInfo(List<String> result, RandomState randomState, BlockPos feetPos) {
	}

	@Override
	public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState,
			ChunkAccess chunkAccess) {
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
	}

	public abstract BlockState getBlockAt(final PositionalRandomFactory randomFactory, final int x, final int y,
			final int z);

	public abstract String getSeed();

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, this.getSeed()));

		final int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = this.getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = this.getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, this.getSeed()));

		for (int y = this.getMinY() + this.getGenDepth() - 1; y >= this.getMinY(); y--) {
			if (!getBlockAt(worldSeed, x, y, z).isAir()) {
				return y + 1;
			}
		}

		return this.getMinY();
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, this.getSeed()));

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = this.getMinY(); y < this.getMinY() + this.getGenDepth(); y++) {
					BlockState block = getBlockAt(worldSeed, globalX, y, globalZ);
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

}
