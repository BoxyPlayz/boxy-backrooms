package com.boxyplayz.backrooms.world.generators;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.block.ModBlocks;
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

public class Level2ChunkGen extends BaseChunkGen {

	public static final MapCodec<Level2ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL2_BIOME))
					.apply(instance,
							instance.stable(Level2ChunkGen::new)));

	public Level2ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int localX = Math.floorMod(x, 16);
		int localZ = Math.floorMod(z, 16);
		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);

		RandomSource chunkRandom = randomFactory.at(chunkX, 0, chunkZ);

		boolean xDir = !(chunkRandom.nextInt(18) == 2);
		boolean zDir = !(chunkRandom.nextInt(18) == 2) || !xDir;

		int maxR = 9;

		Range<Integer> horizontalRange = Range.of(7, maxR);

		Range<Integer> horizontalRangeSmol = Range.of(7,
				maxR - 1);
		if (y > 4) {
			if (randomFactory.at(x, y, z).nextBoolean()) {
				return ModBlocks.LEVEL3_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (y < 0) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		if (xDir && zDir) {
			if (!(horizontalRange.contains(localX) || horizontalRange.contains(localZ))) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			if (Math.floorMod(y, 2) == 0) {
				if (((localX == maxR) && !horizontalRangeSmol.contains(localZ)
						|| (localZ == maxR) && !horizontalRangeSmol.contains(localX))) {
					return ModBlocks.LEVEL2_PIPE.defaultBlockState();
				}
			}
		} else {
			if (xDir && !horizontalRange.contains(localX)) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			if (zDir && !horizontalRange.contains(localZ)) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			if (Math.floorMod(y, 2) == 0) {

				if ((xDir
						&& localX == maxR
						&& !horizontalRangeSmol.contains(localZ))

						||

						(zDir
								&& localZ == maxR
								&& !horizontalRangeSmol.contains(localX))) {
					return ModBlocks.LEVEL2_PIPE.defaultBlockState();
				}
			}
		}
		if (y == 0) {
			if (randomFactory.at(x, 2, z).nextInt(2000) == 4) {
				return ModBlocks.LEVEL2_FIRE_EXIT.defaultBlockState();
			}
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "pipedreams";
	}

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
