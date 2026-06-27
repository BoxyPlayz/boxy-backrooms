package com.boxyplayz.backrooms.world.generators;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level11ChunkGen extends BaseChunkGen {

	Range<Integer> buildingBounds = Range.of(0, 14);

	public Level11ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<Level11ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL11_BIOME)).apply(instance,
					instance.stable(Level11ChunkGen::new)));

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int chunkX = Math.floorMod(x, 16);
		int chunkZ = Math.floorMod(z, 16);
		if (inBuildingChunk(x, z)) {

			if (withinBuilding(x, z)) {
				RandomSource buildingRandom = randomFactory.at(Math.floorDiv(x, 16), 0, Math.floorDiv(z, 16));
				int buildingHeightMul = buildingRandom.nextIntBetweenInclusive(8, 36);
				int floorHeight = 6;
				int floorY = Math.floorMod(y, floorHeight);
				if (y <= 5) {
					return Blocks.SMOOTH_STONE.defaultBlockState();
				}
				if (y <= buildingHeightMul * floorHeight) {
					if (isWalls(x, z)) {
						if (chunkX == 4 && chunkZ == 14) {
							if (y == 7) {
								return Blocks.OAK_DOOR.defaultBlockState();
							} else if (y == 8) {
								return Blocks.OAK_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
										DoubleBlockHalf.UPPER);
							}
						}
						if (floorY == 0 || floorY == floorHeight - 1) {
							return Blocks.STONE.defaultBlockState();
						}
						return Blocks.GLASS.defaultBlockState();
					}
					if (floorY == 0) {
						return Blocks.SMOOTH_STONE.defaultBlockState();
					} else if (floorY == floorHeight - 1) {
						return Blocks.QUARTZ_BLOCK.defaultBlockState();
					}
				}
			} else {
				if (y <= 5) {
					if (y == 5)
						return Blocks.GRASS_BLOCK.defaultBlockState();
					else
						return Blocks.DIRT.defaultBlockState();
				}
				if (chunkX == 4 && chunkZ == 15) {
					if (y == 6)
						return Blocks.STONE_STAIRS.defaultBlockState();
				} else if (y == 6 || y == 7) {
					return Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);

				}
			}
		} else {
			if (y <= 5) {
				return Blocks.BLACKSTONE.defaultBlockState();
			}
		}

		return Blocks.AIR.defaultBlockState();

	}

	private boolean withinBuilding(int x, int z) {
		return buildingBounds.contains(Math.floorMod(x, 16)) && buildingBounds.contains(Math.floorMod(z, 16));
	}

	private boolean inBuildingChunk(int x, int z) {
		return Math.floorMod(Math.floorDiv(x, 16), 2) == 0 && Math.floorMod(Math.floorDiv(z, 16), 2) == 0;

	}

	private boolean isWalls(int x, int z) {
		int chunkX = Math.floorMod(x, 16);
		int chunkZ = Math.floorMod(z, 16);

		return chunkX == buildingBounds.getMinimum() || chunkZ == buildingBounds.getMinimum()
				|| chunkX == buildingBounds.getMaximum() || chunkZ == buildingBounds.getMaximum();
	}

	@Override
	public String getSeed() {
		return "citynoend";
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 256;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
