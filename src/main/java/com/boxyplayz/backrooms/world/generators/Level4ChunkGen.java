package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.block.custom.ElevatorBlock;
import com.boxyplayz.backrooms.block.custom.WaterFountainBlock;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level4ChunkGen extends BaseChunkGen {

	protected boolean getRandom(RandomSource random) {
		return (random.nextIntBetweenInclusive(1, 20) == 1);
	}

	protected boolean getRandom(RandomSource random, int max) {
		return (random.nextIntBetweenInclusive(1, max) == 1);
	}

	public Level4ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		if (y <= 0) {
			return ModBlocks.LEVEL4_CARPET.defaultBlockState();
		}
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
				return ModBlocks.PURE_WHITE_GLOW.defaultBlockState();
			}
			return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
		}

		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);
		int localX = Math.floorMod(x, 16);
		int localZ = Math.floorMod(z, 16);

		RandomSource chunkRandom = randomFactory.at(chunkX, 0, chunkZ);

		// Walls
		if (getRandom(chunkRandom)) {
			if (localX == 15) {
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
		}
		if (getRandom(chunkRandom)) {
			if (localX == 0) {
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
		}
		if (getRandom(chunkRandom)) {
			if (localZ == 15) {
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
		}
		if (getRandom(chunkRandom)) {
			if (localZ == 0) {
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
		}
		if (getRandom(chunkRandom, 50)) {
			boolean north = localZ == 15;
			boolean south = localZ == 0;
			boolean east = localX == 15;
			boolean west = localX == 0;
			if (south || west || east || north) {
				RandomSource horizRandom = randomFactory.at(x, 32, z);
				if ((south ^ north ^ east ^ west) && getRandom(horizRandom, 30)) {
					if (north) {
						if (y == 1) {
							return Blocks.SPRUCE_DOOR.defaultBlockState();
						}
						if (y == 2) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
									DoubleBlockHalf.UPPER);
						}
					}
					if (south) {
						if (y == 1) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.FACING, Direction.SOUTH);
						}
						if (y == 2) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
									DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING, Direction.SOUTH);
						}
					}
					if (east) {
						if (y == 1) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.FACING, Direction.WEST);
						}
						if (y == 2) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
									DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING, Direction.WEST);
						}
					}
					if (west) {
						if (y == 1) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.FACING, Direction.EAST);
						}
						if (y == 2) {
							return Blocks.SPRUCE_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
									DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING, Direction.EAST);
						}
					}
				}
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
			if (y == 1) {
				if (randomFactory.at(x, 23, z).nextInt(140) == 4) {
					return ModBlocks.WATER_FOUNTAIN.defaultBlockState().setValue(WaterFountainBlock.FACING,
							Direction.Plane.HORIZONTAL.getRandomDirection(chunkRandom));
				}
			}
		}

		if (randomFactory.at(x, 12, z).nextInt(40000) == 4) {
			if (y == 1) {
				return ModBlocks.ELEVATOR.defaultBlockState();
			}
			if (y == 2) {
				return ModBlocks.ELEVATOR.defaultBlockState().setValue(ElevatorBlock.TOP, true);
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "abandonment";
	}

	public static final MapCodec<Level4ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL4_BIOME))
					.apply(instance,
							instance.stable(Level4ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 32;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
