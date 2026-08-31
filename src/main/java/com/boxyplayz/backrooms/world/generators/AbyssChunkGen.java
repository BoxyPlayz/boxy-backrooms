package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.utils.Misc;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.biomesources.AbyssBiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class AbyssChunkGen extends BaseChunkGen {

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(this.getSeed());
			this.noise = new SimplexNoise(random);
		}

		return this.noise;

	}

	public AbyssChunkGen(Holder.Reference<Biome> start, Holder.Reference<Biome> cold, Holder.Reference<Biome> hot,
			Holder.Reference<Biome> end) {
		super(new AbyssBiomeSource(start, cold, hot, end));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int deepness = (int) Math.floor(Misc.normalizeValues(x, z));

		if (y == getMinY() && deepness <= 700) {
			return Blocks.BEDROCK.defaultBlockState();
		}

		if (deepness <= 200) {
			if (y <= getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 8 + 16) {
				return Blocks.OBSIDIAN.defaultBlockState();
			}
			return Blocks.AIR.defaultBlockState();
		}

		if (deepness <= 500) {
			double noiseVal = getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 8 + 16;
			if (y <= noiseVal) {
				if (y + 2 <= noiseVal) {
					return Blocks.DIRT.defaultBlockState();
				}
				if (y + 1 > noiseVal) {
					if (randomFactory.at(x, y, z).nextInt(20) == 3) {
						return Blocks.PACKED_ICE.defaultBlockState();
					}
				}
				return Blocks.SNOW_BLOCK.defaultBlockState();
			}
			return Blocks.AIR.defaultBlockState();
		}

		if (deepness <= 700) {
			double noiseVal = getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 8 + 16;
			if (y <= noiseVal) {
				return Blocks.BLACKSTONE.defaultBlockState();
			}
			return Blocks.AIR.defaultBlockState();
		}

		if (deepness <= 1000) {
			double noiseVal = getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 8 + 16;
			if (y <= noiseVal) {
				return Blocks.END_STONE.defaultBlockState();
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "somewhereyouforgot";
	}

	public static final MapCodec<AbyssChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.ABYSS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_COLD_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_HOT_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_END_BIOME))
					.apply(instance,
							instance.stable(AbyssChunkGen::new)));

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
		return -16;
	}

}
