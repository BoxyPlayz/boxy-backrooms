package com.boxyplayz.backrooms.world.biomesources;

import java.util.stream.Stream;

import com.boxyplayz.backrooms.biome.ModBiomes;
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

public class Level1BiomeSource extends BiomeSource {

	private SimplexNoise noise;

	protected SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("level1_seed");
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	public static final MapCodec<Level1BiomeSource> CODEC = RecordCodecBuilder
			.mapCodec(instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.AQUILA_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GARDEN_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.FABLED_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.OUROBOROS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GOTHIC_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GILDED_BIOME)

			).apply(
					instance,
					instance.stable(Level1BiomeSource::new)));

	private final Holder<Biome> aquila;
	private final Holder<Biome> garden;
	private final Holder<Biome> fabled;
	private final Holder<Biome> ouroboros;
	private final Holder<Biome> gothic;
	private final Holder<Biome> gilded;

	public Level1BiomeSource(
			Holder<Biome> aquila,
			Holder<Biome> garden,
			Holder<Biome> fabled,
			Holder<Biome> ouroboros,
			Holder<Biome> gothic,
			Holder<Biome> gilded) {
		this.aquila = aquila;
		this.garden = garden;
		this.fabled = fabled;
		this.ouroboros = ouroboros;
		this.gothic = gothic;
		this.gilded = gilded;
	}

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.aquila, this.garden, this.fabled, this.ouroboros, this.gothic, this.gilded);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int x, int y, int z, Sampler sampler) {
		PositionalRandomFactory randomFactory = new LegacyPositionalRandomFactory(1);

		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		double noiseValue = getNoise(randomFactory).getValue(chunkX * 0.03, chunkZ * 0.03);
		if (noiseValue > 0.6) {
			if (noiseValue > 0.8) {
				if (noiseValue > 0.9) {
					if (noiseValue > 0.91) {
						if (noiseValue > 0.99) {
							return this.fabled;
						} else {
							return this.garden;
						}
					} else {
						return this.ouroboros;
					}
				} else {
					return this.gothic;
				}
			} else {
				return this.gilded;
			}
		}

		return this.aquila;
	}

}
