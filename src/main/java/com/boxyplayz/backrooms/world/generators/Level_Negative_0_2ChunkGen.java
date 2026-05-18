package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level_Negative_0_2ChunkGen extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		RandomSource chunkRandom = randomFactory.at((int) chunkX, 0, (int) chunkZ);

		if (chunkRandom.nextIntBetweenInclusive(0, 24) == 2) {
			if (y <= 0) {
				return ModBlocks.PREMIUM_CARPET.defaultBlockState();
			}
			if (y >= 4) {
				if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
					return ModBlocks.LEVEL0_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.PREMIUM_CEILING_TILE.defaultBlockState();
			}

			int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);
			int localX = Math.abs(Math.floorMod(x, 4));
			int localZ = Math.abs(Math.floorMod(z, 4));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					0,
					(int) (chunkZ * 4 + cellZ));

			if (getRandomBool(cellRandom) && localZ == 0) {
				return ModBlocks.PREMIUM_WALLPAPER.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localZ == 3) {
				return ModBlocks.PREMIUM_WALLPAPER.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localX == 0) {
				return ModBlocks.PREMIUM_WALLPAPER.defaultBlockState();
			}
			if (getRandomBool(cellRandom) && localX == 3) {
				return ModBlocks.PREMIUM_WALLPAPER.defaultBlockState();
			}

		}

		// Floor
		if (y <= 0) {
			RandomSource xyRandom = randomFactory.at(x, 0, z);
			if (xyRandom.nextIntBetweenInclusive(1, 300) == 4) {
				return Blocks.AIR.defaultBlockState();
			}
			return ModBlocks.INFERIOR_CARPET.defaultBlockState();
		}

		// Ceiling
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
				return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.INFERIOR_CEILING_TILE.defaultBlockState();
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
			return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localZ == 3) {
			return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 0) {
			return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 3) {
			return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level_Negative_0_2ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<Level_Negative_0_2ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(Biomes.THE_VOID))
					.apply(instance,
							instance.stable(Level_Negative_0_2ChunkGen::new)));

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
		return "level-0.2";
	}

}
