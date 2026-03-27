package com.boxyplayz.backrooms.chunkgen.biomesources;

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

public class Level0BiomeSource extends BiomeSource {

	private SimplexNoise noise;

	protected SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("level0_seed");
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	public static final MapCodec<Level0BiomeSource> CODEC = RecordCodecBuilder
			.mapCodec(instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.NORMAL_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.COLUMNS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.PITFALLS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level0Biomes.BLACKOUT_BIOME)

			).apply(
					instance,
					instance.stable(Level0BiomeSource::new)));

	private final Holder<Biome> normal;
	private final Holder<Biome> columns;
	private final Holder<Biome> blackout;
	private final Holder<Biome> pitfalls;

	public Level0BiomeSource(
			Holder<Biome> normal,
			Holder<Biome> columns,
			Holder<Biome> blackout,
			Holder<Biome> pitfalls) {
		this.normal = normal;
		this.columns = columns;
		this.blackout = blackout;
		this.pitfalls = pitfalls;
	}

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.normal, this.columns, this.blackout, this.pitfalls);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int x, int y, int z, Sampler sampler) {
		PositionalRandomFactory randomFactory = new LegacyPositionalRandomFactory(0);

		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		double noiseValue = getNoise(randomFactory).getValue(chunkX * 0.07, chunkZ * 0.07);
		if (noiseValue < 0.4) {
			return this.columns;
		} else if (noiseValue > 0.8) {
			if (noiseValue > 0.9) {
				return this.blackout;
			} else {
				return this.pitfalls;
			}
		}

		return this.normal;
	}

}
