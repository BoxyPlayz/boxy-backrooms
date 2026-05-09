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

public class TheBrokenChunkGen extends BaseChunkGen {
	Level7ChunkGen level7ChunkGen;
	Level94ChunkGen level94ChunkGen;

	protected BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		RandomSource random = randomFactory
				.fromSeed(Math.floorDiv(x, 16) * 341873128712L + Math.floorDiv(z, 16) * 132897987541L);

		int chunkId = random.nextIntBetweenInclusive(1, 4);

		switch (chunkId) {
			case 1:
				if (Math.abs(Math.floorMod(x, 128) + Math.floorMod(z, 128)) == y) {
					return Blocks.STONE.defaultBlockState();
				}

				break;

			case 2:
				switch (Math.floorMod(y, 5)) {
					case 0:
						RandomSource blockRandom = randomFactory.at(x, y, z);
						if (y == 0 && blockRandom.nextIntBetweenInclusive(0, 800) == 0) {
							return ModBlocks.LEVEL0_CARPET_GLITCHED.defaultBlockState();
						}
						return ModBlocks.LEVEL0_CARPET.defaultBlockState();

					case 4:
						return ModBlocks.LEVEL0_CEILING_TILE.defaultBlockState();

					default:
						if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
							return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
						}
						return Blocks.AIR.defaultBlockState();
				}

			case 3:
				return level7ChunkGen.getBlockAt(randomFactory, x, y, z);

			case 4:
				return level94ChunkGen.getBlockAt(randomFactory, x, y, z);
			default:
				return Blocks.AIR.defaultBlockState();
		}
		return Blocks.AIR.defaultBlockState();
	}

	public TheBrokenChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
		level7ChunkGen = new Level7ChunkGen(reference);
		level94ChunkGen = new Level94ChunkGen(reference);
	}

	public static final MapCodec<TheBrokenChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(Biomes.THE_VOID)).apply(instance,
					instance.stable(TheBrokenChunkGen::new)));

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
		return 0;
	}

	@Override
	String getSeed() {
		return "fduhjsabh";
	}

}
