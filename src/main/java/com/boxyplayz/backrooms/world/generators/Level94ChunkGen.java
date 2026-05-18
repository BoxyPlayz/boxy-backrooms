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

/**
 * Chunk Generation for level 94
 */
public class Level94ChunkGen extends BaseChunkGen {

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(this.getSeed());
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

		int height = (int) (getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 10);

		height += 70;

		if (y <= height) {
			return ModBlocks.PURE_GRASS.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level94ChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
	}

	/**
	 * Chunk Generation Codec
	 */
	public static final MapCodec<Level94ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL94_BIOME)).apply(instance,
					instance.stable(Level94ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 256;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	public String getSeed() {
		return "ninetyfour";
	}

}
