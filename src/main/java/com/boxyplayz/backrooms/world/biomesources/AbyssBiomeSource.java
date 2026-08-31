package com.boxyplayz.backrooms.world.biomesources;

import java.util.stream.Stream;

import com.boxyplayz.backrooms.utils.Misc;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.generators.AbyssChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate.Sampler;

public class AbyssBiomeSource extends BiomeSource {

	public static final MapCodec<AbyssBiomeSource> CODEC = RecordCodecBuilder
			.mapCodec(instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.ABYSS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_COLD_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_HOT_BIOME),
					RegistryOps.retrieveElement(ModBiomes.ABYSS_END_BIOME)).apply(
							instance,
							instance.stable(AbyssBiomeSource::new)));

	private final Holder<Biome> startHolder;
	private final Holder<Biome> coldHolder;
	private final Holder<Biome> hotHolder;
	private final Holder<Biome> endHolder;

	public AbyssBiomeSource(Holder<Biome> start, Holder<Biome> cold, Holder<Biome> hot, Holder<Biome> end) {
		this.startHolder = start;
		this.coldHolder = cold;
		this.hotHolder = hot;
		this.endHolder = end;
	}

	@Override
	protected MapCodec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.startHolder, this.coldHolder, this.hotHolder, this.endHolder);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ, Sampler sampler) {
		int x = QuartPos.toBlock(quartX);
		int z = QuartPos.toBlock(quartZ);

		int deepness = (int) Math.floor(Misc.normalizeValues(x, z));

		int i = AbyssChunkGen.START_SIZE;
		if (deepness <= i) {
			return this.startHolder;
		}

		i += AbyssChunkGen.COLD_SIZE;
		if (deepness <= i) {
			return this.coldHolder;
		}

		i += AbyssChunkGen.HOT_SIZE;
		if (deepness <= i) {
			return this.hotHolder;
		}

		return this.endHolder;
	}

}
