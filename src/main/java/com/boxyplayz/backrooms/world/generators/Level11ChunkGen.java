package com.boxyplayz.backrooms.world.generators;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.loot.ModLootTables;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

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
		int localChunkX = Math.floorMod(x, thisChunkSize);
		int localChunkZ = Math.floorMod(z, thisChunkSize);
		RandomSource blockRandom = randomFactory.at(x, y, z);
		if (inBuildingChunk(x, z)) {
			RandomSource buildingRandom = randomFactory.at(Math.floorDiv(x, thisChunkSize), 0,
					Math.floorDiv(z, thisChunkSize));
			if (withinBuilding(x, z)) {
				int buildingHeightMul = buildingRandom.nextIntBetweenInclusive(8, 36);
				int floorHeight = 6;
				int floorY = Math.floorMod(y, floorHeight);
				boolean gardenedRooftop = buildingRandom.nextInt(40) == 3;
				if (y <= 5) {
					return Blocks.SMOOTH_STONE.defaultBlockState();
				}
				if (y <= buildingHeightMul * floorHeight) {
					if (y == buildingHeightMul * floorHeight) {
						if (localChunkX == 14 && localChunkZ == 14) {
							return Blocks.LADDER.defaultBlockState().setValue(LadderBlock.FACING, Direction.WEST);
						}
						return Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState();
					}
					if (localChunkX == 15 && localChunkZ == 14) {
						return Blocks.IRON_BLOCK.defaultBlockState();
					} else if (localChunkX == 14 && localChunkZ == 14) {
						if (y == 6)
							return Blocks.GRAY_CONCRETE.defaultBlockState();
						return Blocks.LADDER.defaultBlockState().setValue(LadderBlock.FACING, Direction.WEST);
					} else if (isWalls(x, z)) {
						if (localChunkX == localChunkZ || (localChunkX == 15 && localChunkZ == 1)
								|| (localChunkX == 1 && localChunkZ == 15)) {
							return Blocks.IRON_BLOCK.defaultBlockState();
						}
						if (localChunkX == 2 && localChunkZ == 15) {
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
						if (Math.floorMod(localChunkZ, 4) == 0 && lightRange.contains(localChunkX)) {
							return Blocks.SEA_LANTERN.defaultBlockState();
						}
						return Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState();
					} else if (floorContentRange.contains(floorY)) {
						Optional<BlockState> state = getFloorInterior(x, y, z, randomFactory);
						if (!state.isEmpty()) {
							return state.get();
						}
					}
				} else if (y == (buildingHeightMul * floorHeight) + 1) {
					if (localChunkX == buildingBounds.getMinimum() || localChunkX == buildingBounds.getMaximum()
							|| localChunkZ == buildingBounds.getMinimum()
							|| localChunkZ == buildingBounds.getMaximum()) {
						return Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState();
					} else if (Math.floorMod(localChunkX, 4) != 2 && Math.floorMod(localChunkZ, 4) != 2
							&& gardenedRooftop) {
						return Blocks.GRASS_BLOCK.defaultBlockState().setValue(SnowyBlock.SNOWY, true);
					} else if (blockRandom.nextInt(100) == 3 && gardenedRooftop) {
						return Blocks.CHEST.defaultBlockState();
					}

				} else if (y == (buildingHeightMul * floorHeight) + 2 || y == (buildingHeightMul * floorHeight) + 3) {
					if (Math.floorMod(localChunkX, 4) != 2 && Math.floorMod(localChunkZ, 4) != 2
							&& !(localChunkX == buildingBounds.getMinimum()
									|| localChunkX == buildingBounds.getMaximum()
									|| localChunkZ == buildingBounds.getMinimum()
									|| localChunkZ == buildingBounds.getMaximum())
							&& gardenedRooftop) {
						return Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);
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
					if (localChunkX == 2 && localChunkZ == 16) {
						return Blocks.STONE_STAIRS.defaultBlockState();
					} else if (!((localChunkX == 1 || localChunkX == 3) && localChunkZ == 16)) {
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
		int roomType = floorRandom.nextIntBetweenInclusive(1, 5);

		boolean wallN = false;
		boolean wallE = false;
		boolean wallS = false;
		boolean wallW = false;

		switch (roomType) {
			case 1:
				if (Math.floorMod(localX, 3) == 1) {
					if (floorY == 1) {
						int zed = Math.floorMod(localZ, 5);
						if (zed == 2) {
							return Optional.of(Blocks.RED_WOOL.defaultBlockState());

						} else if (zed == 3) {
							return Optional.of(Blocks.RED_WOOL.defaultBlockState());
						}
					} else if (floorY == 2) {
						if (Math.floorMod(localZ, 5) == 2) {
							return Optional.of(Blocks.WHITE_CARPET.defaultBlockState());
						}
					}
				}
				break;

			case 2:
				if (floorY == 1) {
					if (blockRandom.nextBoolean()) {
						return Optional.ofNullable(switch (blockRandom.nextIntBetweenInclusive(1, 4)) {
							case 1 -> Blocks.OAK_PLANKS.defaultBlockState();
							case 2 -> Blocks.OAK_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
									Direction.Plane.HORIZONTAL.getRandomDirection(blockRandom));
							case 3 -> Blocks.OAK_LOG.defaultBlockState();
							default -> Blocks.OAK_SLAB.defaultBlockState();
						});
					}
				} else if (floorY == 2) {
					if (blockRandom.nextInt(7) == 3) {
						return Optional.ofNullable(switch (blockRandom.nextIntBetweenInclusive(1, 4)) {
							case 1 -> Blocks.SPRUCE_PLANKS.defaultBlockState();
							case 2 -> Blocks.SPRUCE_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
									Direction.Plane.HORIZONTAL.getRandomDirection(blockRandom));
							case 3 -> Blocks.SPRUCE_LOG.defaultBlockState();
							default -> Blocks.SPRUCE_SLAB.defaultBlockState();
						});
					}
				} else if (floorY == 3) {
					if (blockRandom.nextInt(20) == 6) {
						return Optional.ofNullable(switch (blockRandom.nextIntBetweenInclusive(1, 6)) {
							case 1 -> Blocks.SPRUCE_SAPLING.defaultBlockState();
							case 2 -> Blocks.JUNGLE_SAPLING.defaultBlockState();
							case 3 -> Blocks.DARK_OAK_SAPLING.defaultBlockState();
							case 4 -> Blocks.BIRCH_SAPLING.defaultBlockState();
							case 5 -> Blocks.ACACIA_SAPLING.defaultBlockState();
							default -> Blocks.OAK_SAPLING.defaultBlockState();
						});
					}
				}
				break;

			case 3:
				wallN = floorRandom.nextInt(7) == 3;
				wallS = floorRandom.nextInt(7) == 3;
				wallE = floorRandom.nextInt(7) == 3;
				wallW = floorRandom.nextInt(7) == 3;

			default:
				break;
		}

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
					if (block.is(Blocks.CHEST)) {
						ChestBlockEntity blockEntity = BlockEntityType.CHEST
								.create(new BlockPos(globalX, y, globalZ), block);
						blockEntity.setLootTable(ModLootTables.LEVEL_11_ROOF_CHEST_LOOT);
						if (blockEntity != null) {
							chunkAccess.setBlockEntity(blockEntity);
						}
					}
				}
			}
		}

		chunkAccess.getOrCreateHeightmapUnprimed(Types.WORLD_SURFACE_WG);
		chunkAccess.getOrCreateHeightmapUnprimed(Types.OCEAN_FLOOR_WG);
		return CompletableFuture.completedFuture(chunkAccess);
	}

}
