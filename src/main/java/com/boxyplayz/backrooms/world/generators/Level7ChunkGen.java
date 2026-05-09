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
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class Level7ChunkGen extends BaseChunkGen {

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("level0_seed");
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	/**
	 * Gets block from coordinates
	 * 
	 * @param randomFactory Random Factory
	 * @param x             X Coordinate
	 * @param y             Y Coordinate
	 * @param z             Z Coordinate
	 * @return Blockstate
	 */
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int minY = getMinY();

		RandomSource chunkId = randomFactory.fromSeed(x * 341873128712L + z * 132897987541L + y * 1328712L);

		// Bedrock Floor
		if (y == minY) {
			return Blocks.BEDROCK.defaultBlockState();
		}

		// Main Deepslate Layer
		if (y < 20) {
			boolean isErrorSlate = (chunkId.nextIntBetweenInclusive(1, 80) == 1);
			if (isErrorSlate) {
				return ModBlocks.ERRORSLATE.defaultBlockState();
			} else {
				return Blocks.DEEPSLATE.defaultBlockState();
			}
		}

		// Water
		if (y < this.getSeaLevel()) {
			double noiseValue = getNoise(randomFactory).getValue(x * 0.002, z * 0.002) * 140;
			if (noiseValue + 20 >= y) {
				if (y > 110) {
					return Blocks.SAND.defaultBlockState();
				}
				if (y > 100) {
					return Blocks.GRAVEL.defaultBlockState();
				}
				if (y > 60) {
					return Blocks.STONE.defaultBlockState();
				}
				boolean isErrorSlate = (chunkId.nextIntBetweenInclusive(1, 120) == 1);
				if (isErrorSlate) {
					return ModBlocks.ERRORSLATE.defaultBlockState();
				} else {
					return Blocks.DEEPSLATE.defaultBlockState();
				}
			}
			return Blocks.WATER.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	/**
	 * Chunk Generation
	 * 
	 * @param reference Biome
	 */
	public Level7ChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
	}

	public static final MapCodec<Level7ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL7_OCEAN_BIOME)).apply(instance,
					instance.stable(Level7ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 304;
	}

	@Override
	public int getSeaLevel() {
		return 200;
	}

	@Override
	public int getMinY() {
		return -64;
	}

	@Override
	String getSeed() {
		return "level7";
	}

}
