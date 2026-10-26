package com.boxyplayz.backrooms.common.world.generators.custom;

import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.world.ModBiomes;
import com.boxyplayz.backrooms.common.world.generators.BaseChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
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

public class Hayrooms extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {

		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x >> 2, y >> 2, z >> 2, null);
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);
		if (!biome.is(ModBiomes.Level0Biomes.PITFALLS_BIOME)
				&& (y == this.getMinY() || y == this.getMinY() + this.getGenDepth() - 1)) {
			return Blocks.BEDROCK.defaultBlockState();
		}

		RandomSource random = randomFactory.at(new BlockPos(x, y, z));

		// Floor
		if (y <= 0) {
			if (y == 0 && random.nextIntBetweenInclusive(0, 800) == 0) {
				return Blocks.HAY_BLOCK.defaultBlockState();
			}
			return Blocks.HAY_BLOCK.defaultBlockState();
		}

		// Ceiling
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
				return ModBlocks.LEVEL0_CEILING_LIGHT.get().defaultBlockState();
			}
			return Blocks.HAY_BLOCK.defaultBlockState();
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
			return Blocks.HAY_BLOCK.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localZ == 3) {
			return Blocks.HAY_BLOCK.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 0) {
			return Blocks.HAY_BLOCK.defaultBlockState();
		}
		if (getRandomBool(cellRandom) && localX == 3) {
			return Blocks.HAY_BLOCK.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Hayrooms(Holder.Reference<Biome> normal) {
		super(new FixedBiomeSource(normal));
	}

	public static final MapCodec<Hayrooms> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(Biomes.THE_VOID))
					.apply(instance,
							instance.stable(Hayrooms::new)));

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
