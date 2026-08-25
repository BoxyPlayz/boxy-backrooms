package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class Level10ChunkGen extends BaseChunkGen {

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(this.getSeed());
			this.noise = new SimplexNoise(random);
		}

		return this.noise;

	}

	@Override
	public int getSeaLevel() {
		return 15;
	}

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory, String seed) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(seed);
			this.noise = new SimplexNoise(random);
		}

		return this.noise;

	}

	public Level10ChunkGen(Holder<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int noiseVal = (int) Math.floor((getNoise(randomFactory).getValue(x * 0.0003, z * 0.0003)) + 16);
		boolean grassy = getNoise(randomFactory, this.getSeed() + "gloobyandnevergroovy").getValue(x * 0.005,
				z * 0.005) > 0.8;
		boolean isWaterSource = (Math.floorMod(x, 7) == 1 && Math.floorMod(z, 7) == 1);
		double lakeValue = getNoise(randomFactory, this.getSeed() + "hyperdeath").getValue(x * 0.003,
				z * 0.003);
		if (lakeValue > 0.8) {
			double lakeVal = (1 - lakeValue) * 4.8 * 14;
			if (y < this.getSeaLevel())
				if (y > lakeVal) {
					return Blocks.WATER.defaultBlockState();
				} else {
					return getGroundBlock(y, true);
				}

			return Blocks.AIR.defaultBlockState();
		}
		if (y < noiseVal) {
			if (y + 1 < noiseVal) {
				return getGroundBlock(y, false);
			} else {
				if (grassy) {
					return Blocks.GRASS_BLOCK.defaultBlockState();
				}
				if (isWaterSource) {
					return Blocks.WATER.defaultBlockState();
				}
				return Blocks.FARMLAND.defaultBlockState().setValue(FarmlandBlock.MOISTURE, 7);
			}
		} else if (!grassy && !isWaterSource && y < noiseVal + 1) {
			double wheatValue = getNoise(randomFactory, this.getSeed() + "breadyummyyummy").getValue(x * 0.03,
					z * 0.03);
			if (wheatValue > 0.2) {
				return Blocks.WHEAT.defaultBlockState().setValue(CropBlock.AGE, 7);
			} else {
				RandomSource blockRandom = randomFactory.at(x, y, z);
				if (blockRandom.nextInt(400) == 8) {
					return Blocks.PUMPKIN.defaultBlockState();
				}
			}
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "farmingarea";
	}

	public static final MapCodec<Level10ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL10_BIOME)).apply(instance,
					instance.stable(Level10ChunkGen::new)));

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

	public BlockState getGroundBlock(int y, boolean water) {
		if (water) {
			if (y > 10) {
				return Blocks.SAND.defaultBlockState();
			} else if (y > 6) {
				return Blocks.GRAVEL.defaultBlockState();
			}
		} else if (y > 8) {
			return Blocks.DIRT.defaultBlockState();
		}
		return Blocks.STONE.defaultBlockState();
	}

}
