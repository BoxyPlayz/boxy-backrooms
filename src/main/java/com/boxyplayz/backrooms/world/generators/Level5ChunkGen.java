package com.boxyplayz.backrooms.world.generators;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.block.custom.Level5CarpetBlock;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level5ChunkGen extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 4) == 0;
	}

	public int HOTEL_HEIGHT = 32;

	public static final MapCodec<Level5ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL5_BIOME))
					.apply(instance,
							instance.stable(Level5ChunkGen::new)));

	public Level5ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int localX = Math.floorMod(x, 24);
		int localZ = Math.floorMod(z, 48);
		Range<Integer> horizHallRange = Range.of(0, 3);

		RandomSource blockRandom = randomFactory.at(x, y, z);
		if (horizHallRange.contains(localX) || horizHallRange.contains(localZ)) {
			if (randomFactory.at(x, 0, z).nextInt(10000) == 4) {
				if (y > HOTEL_HEIGHT + 7) {
					return Blocks.SPRUCE_PLANKS.defaultBlockState();
				}

				if (y <= HOTEL_HEIGHT - 9) {
					return ModBlocks.AGED_CONCRETE.defaultBlockState();
				}
				if (y <= HOTEL_HEIGHT + 3) {
					return Blocks.LADDER.defaultBlockState();
				}
				return Blocks.AIR.defaultBlockState();
			}
		}

		// Hotel
		if (y >= HOTEL_HEIGHT) {
			if (y <= HOTEL_HEIGHT + 3) {
				int carpetType = blockRandom.nextIntBetweenInclusive(0, 2);
				return ModBlocks.LEVEL5_CARPET.defaultBlockState().setValue(Level5CarpetBlock.TYPE, carpetType);
			}
			if (!(horizHallRange.contains(localX) || horizHallRange.contains(localZ))) {
				return Blocks.QUARTZ_BRICKS.defaultBlockState();
			}
			if (y > HOTEL_HEIGHT + 7) {
				return Blocks.SPRUCE_PLANKS.defaultBlockState();
			}
			if (y == HOTEL_HEIGHT + 4) {
				if (blockRandom.nextIntBetweenInclusive(1, 30) == 3) {
					return Blocks.SOUL_LANTERN.defaultBlockState();
				}
			}
		} else {
			// Basement
			if (y > HOTEL_HEIGHT - 5) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (y <= HOTEL_HEIGHT - 9) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			int chunkX = Math.floorDiv(x, 16);
			int chunkZ = Math.floorDiv(z, 16);

			int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

			int localerX = Math.abs(Math.floorMod(x, 4));
			int localerZ = Math.abs(Math.floorMod(z, 4));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					0,
					(int) (chunkZ * 4 + cellZ));

			if (getRandomBool(cellRandom) && localerZ == 0) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localerZ == 3) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localerX == 0) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localerX == 3) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			if (blockRandom.nextInt(2000) == 3) {
				return ModBlocks.LEVEL6_ENTRY.defaultBlockState();
			}
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "terrorhotel";
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 128;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
