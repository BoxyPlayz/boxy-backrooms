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

	private SimplexNoise getNoise(PositionalRandomFactory worldSeed, String seedExpand) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("level0_seed" + seedExpand);
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
		RandomSource chunkId = randomFactory.fromSeed(x * 341873128712L + z * 132897987541L + y * 1328712L);

		// Bedrock Floor
		if (y <= this.getMinY() + 2) {
			return Blocks.BEDROCK.defaultBlockState();
		}

		// Main Deepslate Layer
		if (y < -54) {
			boolean isErrorSlate = (chunkId.nextIntBetweenInclusive(1, 7500) == 1);
			if (isErrorSlate) {
				return ModBlocks.ERRORSLATE.defaultBlockState();
			} else {
				return Blocks.DEEPSLATE.defaultBlockState();
			}
		}

		// Water
		double baseNoiseValue = (getNoise(randomFactory).getValue(x * 0.002, z * 0.002)
				+ getNoise(randomFactory, "grug").getValue(x * 0.02, z * 0.02)
				+ getNoise(randomFactory, "beans").getValue(x * -0.002, z * -0.002)) * 120;
		double noiseValue = baseNoiseValue;
		if (noiseValue >= y) {
			if (y > this.getSeaLevel() + 4 + randomFactory.at(x, 3, z).nextIntBetweenInclusive(-1, 1)) {
				return Blocks.MOSS_BLOCK.defaultBlockState();
			}
			if (y > 160 + randomFactory.at(x, 3, z).nextIntBetweenInclusive(-2, 2)) {
				return Blocks.ROOTED_DIRT.defaultBlockState();
			}
			if (y > 110 + randomFactory.at(x, 3, z).nextIntBetweenInclusive(-2, 2)) {
				return Blocks.SAND.defaultBlockState();
			}
			if (y > 100 + randomFactory.at(x, 3, z).nextIntBetweenInclusive(-2, 2)) {
				return Blocks.GRAVEL.defaultBlockState();
			}
			if (y > 60 + randomFactory.at(x, 3, z).nextIntBetweenInclusive(-2, 2)) {
				return Blocks.STONE.defaultBlockState();
			}
			return Blocks.DEEPSLATE.defaultBlockState();
		}
		if (y < this.getSeaLevel()) {
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
	public String getSeed() {
		return "level7";
	}

}
