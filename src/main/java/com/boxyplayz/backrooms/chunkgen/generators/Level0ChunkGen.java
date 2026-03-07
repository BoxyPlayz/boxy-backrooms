package com.boxyplayz.backrooms.chunkgen.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
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
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class Level0ChunkGen extends ChunkGenerator {

	private BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		long seed = chunkX * 341873128712L + chunkZ * 132897987541L;
		RandomSource chunkId = randomFactory.fromSeed(seed);

		int chunkType = chunkId.nextIntBetweenInclusive(1, 3);

		int relativeChunkX = Math.floorMod(x, 16) + 1;
		int relativeChunkZ = Math.floorMod(z, 16) + 1;

		boolean hasColumns = (chunkId.nextIntBetweenInclusive(1, 4) == 4);
		boolean hasPitFalls = (chunkId.nextIntBetweenInclusive(1, 8) == 4 && !hasColumns);

		if (y <= 4) {

			// Floor
			if (y <= 0) {
				if (hasPitFalls) {
					if ((relativeChunkX / 2 % 2) == 0 && (relativeChunkZ / 2 % 2) == 0) {
						return Blocks.AIR.defaultBlockState();
					} else {
						return ModBlocks.LEVEL0_CARPET.defaultBlockState();
					}
				} else {
					return ModBlocks.LEVEL0_CARPET.defaultBlockState();
				}
			}

			// Ceiling
			if (y == 4) {
				return ModBlocks.LEVEL0_CEILING_TILE.defaultBlockState();
			}

			// Maze logic start!
			switch (chunkType) {
				case 1:
					if (relativeChunkX == 1 || relativeChunkX == 16) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}

					if (hasColumns) {
						if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
							return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
						}
					}
					break;

				case 2:
					if (relativeChunkZ == 1 || relativeChunkZ == 16) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}

					if (hasColumns) {
						if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
							return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
						}

					}
					break;

				default:
					return Blocks.AIR.defaultBlockState();
			}

		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level0ChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
	}

	public static final MapCodec<Level0ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(Biomes.THE_VOID)).apply(instance,
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
		return 0;
	}

	@Override
	public int getMinY() {
		return -16;
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

		for (int y = -16; y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
