package com.boxyplayz.backrooms.common.world.biomesources;

import java.util.stream.Stream;

import com.boxyplayz.backrooms.common.world.ModBiomes.ShadyGrayBiomes;
import com.boxyplayz.backrooms.common.world.generators.custom.ShadyGrayChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate.Sampler;

public class ShadyGrayBiomeSource extends BiomeSource {

	public static final MapCodec<ShadyGrayBiomeSource> CODEC = RecordCodecBuilder
			.mapCodec(instance -> instance.group(
					RegistryOps.retrieveElement(ShadyGrayBiomes.ZEROZERO),
					RegistryOps.retrieveElement(ShadyGrayBiomes.TWOTWO),
					RegistryOps.retrieveElement(ShadyGrayBiomes.ZEROZERO),
					RegistryOps.retrieveElement(ShadyGrayBiomes.ZEROZERO)).apply(
							instance,
							instance.stable(ShadyGrayBiomeSource::new)));

	private final Holder<Biome> zeroHolder;
	private final Holder<Biome> twoHolder;
	private final Holder<Biome> threeHolder;
	private final Holder<Biome> fourHolder;

	public ShadyGrayBiomeSource(Holder<Biome> zero, Holder<Biome> two, Holder<Biome> three, Holder<Biome> four) {
		this.zeroHolder = zero;
		this.twoHolder = two;
		this.threeHolder = three;
		this.fourHolder = four;
	}

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.zeroHolder, this.twoHolder, this.threeHolder, this.fourHolder);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ, Sampler sampler) {
		int y = QuartPos.toBlock(quartY);

		if (y >= ShadyGrayChunkGen.ZEROZERO_START) {
			return this.zeroHolder;
		}

		if (y >= ShadyGrayChunkGen.TWOTWO_START) {
			return this.twoHolder;
		}

		if (y >= ShadyGrayChunkGen.THREETHREE_START) {
			return this.threeHolder;
		}

		return this.fourHolder;
	}

}
