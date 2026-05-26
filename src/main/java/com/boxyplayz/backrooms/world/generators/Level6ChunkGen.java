package com.boxyplayz.backrooms.world.generators;

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

public class Level6ChunkGen extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);
		RandomSource blockNoVerticalRandom = randomFactory.at(x, 0, z);

		// Floor
		if (y <= 0) {
			if (blockNoVerticalRandom.nextIntBetweenInclusive(1, 4324) == 4 && y != getMinY()) {
				return Blocks.WATER.defaultBlockState();
			}
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		// Ceiling
		if (y >= 4) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		// Maze logic start!
		int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
		int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

		int localX = Math.abs(Math.floorMod(x, 4));
		int localZ = Math.abs(Math.floorMod(z, 4));

		RandomSource cellRandom = randomFactory.at(
				(int) (chunkX * 4 + cellX),
				0,
				(int) (chunkZ * 4 + cellZ));

		if (getRandomBool(cellRandom) && localZ == 0) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localZ == 3) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 0) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 3) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level6ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<Level6ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL6_BIOME))
					.apply(instance,
							instance.stable(Level6ChunkGen::new)));

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

	@Override
	public String getSeed() {
		return "level6";
	}

}
