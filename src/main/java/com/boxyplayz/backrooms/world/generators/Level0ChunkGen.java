package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.biomesources.Level0BiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class Level0ChunkGen extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		RandomSource random = randomFactory.at(new BlockPos(x, y, z));

		int relativeChunkX = Math.floorMod(x, 16) + 1;
		int relativeChunkZ = Math.floorMod(z, 16) + 1;

		RandomSource chunkRandom = randomFactory.at((int) chunkX, 0, (int) chunkZ);

		if (chunkRandom.nextIntBetweenInclusive(0, 76) == 2) {
			if (y <= 0) {
				return ModBlocks.PREMIUM_CARPET.defaultBlockState();
			}
			if (y >= 4) {
				if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
					return ModBlocks.LEVEL0_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.PREMIUM_CEILING_TILE.defaultBlockState();
			}

			short cellX = (short) Math.floorDiv(Math.floorMod(x, 16), 4);
			short cellZ = (short) Math.floorDiv(Math.floorMod(z, 16), 4);
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
			return Blocks.AIR.defaultBlockState();
		}

		// Floor
		if (y <= 0) {
			if (biome.is(ModBiomes.Level0Biomes.PITFALLS_BIOME)) {
				if ((relativeChunkX / 2 % 2) == 0 && (relativeChunkZ / 2 % 2) == 0) {
					return Blocks.AIR.defaultBlockState();
				} else {
					return ModBlocks.LEVEL0_CARPET.defaultBlockState();
				}
			} else {
				if (y == 0 && random.nextIntBetweenInclusive(0, 800) == 0) {
					return ModBlocks.LEVEL0_CARPET_GLITCHED.defaultBlockState();
				}
				return ModBlocks.LEVEL0_CARPET.defaultBlockState();
			}
		}

		// Ceiling
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2
					&& !(biome.is(ModBiomes.Level0Biomes.BLACKOUT_BIOME))) {
				return ModBlocks.LEVEL0_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.LEVEL0_CEILING_TILE.defaultBlockState();
		}

		// Maze logic start!
		if (biome.is(ModBiomes.Level0Biomes.COLUMNS_BIOME)) {
			if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
				return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
			}
		} else {
			if (biome.is(ModBiomes.Level0Biomes.BLACKOUT_BIOME) || biome.is(ModBiomes.Level0Biomes.NORMAL_BIOME)) {
				int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
				int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

				int localX = Math.abs(Math.floorMod(x, 4));
				int localZ = Math.abs(Math.floorMod(z, 4));

				RandomSource cellRandom = randomFactory.at(
						(int) (chunkX * 4 + cellX),
						0,
						(int) (chunkZ * 4 + cellZ));

				if (getRandomBool(cellRandom) && localZ == 0) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localZ == 3) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localX == 0) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
				if (getRandomBool(cellRandom) && localX == 3) {
					return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
				}
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level0ChunkGen(Holder.Reference<Biome> normal, Holder.Reference<Biome> columns,
			Holder.Reference<Biome> blackout, Holder.Reference<Biome> pitfalls) {
		super(new Level0BiomeSource(normal, columns, blackout, pitfalls));
	}

	public static final MapCodec<Level0ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.NORMAL_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.COLUMNS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.BLACKOUT_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.PITFALLS_BIOME))
					.apply(instance,
							instance.stable(Level0ChunkGen::new)));

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
		return "level0_maze";
	}

}
