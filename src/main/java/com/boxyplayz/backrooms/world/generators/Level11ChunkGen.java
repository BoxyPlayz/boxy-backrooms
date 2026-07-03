package com.boxyplayz.backrooms.world.generators;

import java.util.Optional;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level11ChunkGen extends BaseChunkGen {

	int thisChunkSize = 17;

	Range<Integer> buildingBounds = Range.of(1, 15);
	Range<Integer> lightRange = Range.of(2, 14);
	Range<Integer> floorContentRange = Range.of(1, 4);

	public Level11ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<Level11ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL11_BIOME)).apply(instance,
					instance.stable(Level11ChunkGen::new)));

	@Override
	public BlockState getBlockAt(final PositionalRandomFactory randomFactory, final int x, final int y, final int z) {
		int chunkX = Math.floorMod(x, thisChunkSize);
		int chunkZ = Math.floorMod(z, thisChunkSize);
		if (inBuildingChunk(x, z)) {
			if (withinBuilding(x, z)) {
				RandomSource buildingRandom = randomFactory.at(Math.floorDiv(x, thisChunkSize), 0,
						Math.floorDiv(z, thisChunkSize));
				int buildingHeightMul = buildingRandom.nextIntBetweenInclusive(8, 36);
				int floorHeight = 6;
				int floorY = Math.floorMod(y, floorHeight);
				if (y <= 5) {
					return Blocks.SMOOTH_STONE.defaultBlockState();
				}
				if (y <= buildingHeightMul * floorHeight) {
					if (y == buildingHeightMul * floorHeight) {
						if (chunkX == 14 && chunkZ == 14) {
							return Blocks.LADDER.defaultBlockState().setValue(LadderBlock.FACING, Direction.WEST);
						}
						return Blocks.BLACK_CONCRETE.defaultBlockState();
					}
					if (chunkX == 15 && chunkZ == 14) {
						return Blocks.IRON_BLOCK.defaultBlockState();
					} else if (chunkX == 14 && chunkZ == 14) {
						if (y == 6)
							return Blocks.GRAY_CONCRETE.defaultBlockState();
						return Blocks.LADDER.defaultBlockState().setValue(LadderBlock.FACING, Direction.WEST);
					} else if (isWalls(x, z)) {
						if (chunkX == chunkZ || (chunkX == 15 && chunkZ == 1) || (chunkX == 1 && chunkZ == 15)) {
							return Blocks.IRON_BLOCK.defaultBlockState();
						}
						if (chunkX == 2 && chunkZ == 15) {
							if (y == 7) {
								return Blocks.OAK_DOOR.defaultBlockState();
							} else if (y == 8) {
								return Blocks.OAK_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
										DoubleBlockHalf.UPPER);
							}
						}
						if (floorY == 0) {
							return Blocks.GRAY_CONCRETE.defaultBlockState();
						} else if (floorY == floorHeight - 1) {
							return Blocks.GRAY_CONCRETE.defaultBlockState();
						}
						return Blocks.LIGHT_BLUE_STAINED_GLASS.defaultBlockState();
					} else if (floorY == 0) {
						return Blocks.GRAY_CONCRETE.defaultBlockState();
					} else if (floorY == floorHeight - 1) {
						if (Math.floorMod(chunkZ, 4) == 0 && lightRange.contains(chunkX)) {
							return Blocks.SEA_LANTERN.defaultBlockState();
						}
						return Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState();
					} else if (floorContentRange.contains(floorY)) {
						Optional<BlockState> state = getFloorInterior(x, y, z, randomFactory);
						if (!state.isEmpty()) {
							return state.get();
						}
					}
				}
			} else {
				if (y <= 5) {
					if (y == 5)
						return Blocks.GRASS_BLOCK.defaultBlockState();
					else
						return Blocks.DIRT.defaultBlockState();
				}
				if (y == 6) {
					if (chunkX == 2 && chunkZ == 16) {
						return Blocks.STONE_STAIRS.defaultBlockState();
					} else if (!((chunkX == 1 || chunkX == 3) && chunkZ == 16)) {
						return Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT,
								true);
					}
				}
			}
		} else if (y <= 5) {
			return Blocks.BLACKSTONE.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();

	}

	private boolean withinBuilding(int x, int z) {
		return buildingBounds.contains(Math.floorMod(x, thisChunkSize))
				&& buildingBounds.contains(Math.floorMod(z, thisChunkSize));
	}

	private boolean inBuildingChunk(int x, int z) {
		return Math.floorMod(Math.floorDiv(x, thisChunkSize), 2) == 1
				&& Math.floorMod(Math.floorDiv(z, thisChunkSize), 2) == 1;
	}

	private boolean isWalls(final int x, int z) {
		int chunkX = Math.floorMod(x, thisChunkSize);
		int chunkZ = Math.floorMod(z, thisChunkSize);

		return chunkX == buildingBounds.getMinimum() || chunkZ == buildingBounds.getMinimum()
				|| chunkX == buildingBounds.getMaximum() || chunkZ == buildingBounds.getMaximum();
	}

	private Optional<BlockState> getFloorInterior(final int x, final int y, final int z,
			final PositionalRandomFactory randomFactory) {
		final int chunkX = Math.floorDiv(x, thisChunkSize);
		final int chunkZ = Math.floorDiv(z, thisChunkSize);
		final int localX = Math.floorMod(x, thisChunkSize);
		final int localZ = Math.floorMod(z, thisChunkSize);
		final int floorHeight = 6;
		/*
		 * Base Level: 1
		 * Below Ceiling: 4
		 */
		int floorY = Math.floorMod(y, floorHeight);
		int floorId = Math.floorDiv(y, floorHeight);
		RandomSource floorRandom = randomFactory.at(chunkX, floorId, chunkZ);
		RandomSource blockRandom = randomFactory.at(x, y, z);

		boolean wallN = floorRandom.nextInt(5) == 3;
		boolean wallE = floorRandom.nextInt(5) == 3;
		boolean wallS = floorRandom.nextInt(5) == 3;
		boolean wallW = floorRandom.nextInt(5) == 3;

		if (wallN) {
			if (localX == 9 && localZ >= 9) {
				return Optional.of(Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState());
			}
		}

		if (wallE) {
			if (localZ == 9 && localX >= 9) {
				return Optional.of(Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState());
			}
		}

		if (wallS) {
			if (localX == 9 && localZ <= 9) {
				return Optional.of(Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState());
			}
		}

		if (wallW) {
			if (localZ == 9 && localX <= 9) {
				return Optional.of(Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState());
			}
		}

		if (floorY == 1 && blockRandom.nextInt(5) == 3) {
			return Optional.of(Blocks.ACACIA_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
					Direction.Plane.HORIZONTAL.getRandomDirection(blockRandom)));
		}

		return Optional.empty();
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
