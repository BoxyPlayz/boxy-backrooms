package com.boxyplayz.backrooms.world.biomesources;

import java.util.stream.Stream;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate.Sampler;
import net.minecraft.world.level.levelgen.LegacyRandomSource.LegacyPositionalRandomFactory;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class LevelFunBiomeSource extends BiomeSource {

	private SimplexNoise noise;

	protected SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("levelfun_seed");
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	public static final MapCodec<LevelFunBiomeSource> CODEC = RecordCodecBuilder
			.mapCodec(instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PLAYROOMS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.TRAMPOLINE_PARK_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME)

			).apply(
					instance,
					instance.stable(LevelFunBiomeSource::new)));

	private final Holder<Biome> hallways;
	private final Holder<Biome> playrooms;
	private final Holder<Biome> trampoline_park;
	private final Holder<Biome> partyrooms;

	public LevelFunBiomeSource(
			Holder<Biome> hallways,
			Holder<Biome> playrooms,
			Holder<Biome> trampoline,
			Holder<Biome> partyrooms) {
		this.hallways = hallways;
		this.playrooms = playrooms;
		this.trampoline_park = trampoline;
		this.partyrooms = partyrooms;
	}

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.hallways, this.playrooms, this.trampoline_park, this.partyrooms);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int x, int y, int z, Sampler sampler) {
		PositionalRandomFactory randomFactory = new LegacyPositionalRandomFactory(102 + 117 + 110);

		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		double noiseValue = getNoise(randomFactory).getValue(chunkX * 0.02,
				chunkZ * 0.02);

		if (noiseValue > 0.8) {
			return this.partyrooms;
		} else if (noiseValue > 0.6) {
			return this.trampoline_park;
		} else if (noiseValue > 0.4) {
			return this.playrooms;
		}
		return this.hallways;
	}

}
