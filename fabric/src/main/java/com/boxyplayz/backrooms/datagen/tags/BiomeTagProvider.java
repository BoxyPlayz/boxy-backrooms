package com.boxyplayz.backrooms.datagen.tags;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.world.ModBiomes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

public class BiomeTagProvider extends FabricTagsProvider<Biome> {

	public BiomeTagProvider(FabricPackOutput output,
			CompletableFuture<Provider> registryLookupFuture) {
		super(output, Registries.BIOME, registryLookupFuture);
	}

	@Override
	protected void addTags(Provider registries) {
		builder(BiomeTags.HAS_PILLAGER_OUTPOST)
				.add(ModBiomes.LEVEL10_BIOME);

		builder(ModTags.DASH_ENABLED)
				.add(ModBiomes.LEVEL9_BIOME);

		builder(ModTags.LARGE_JUMP)
				.add(ModBiomes.LEVEL8_CAVESYSTEM_BIOME);

		builder(ModTags.SPAWN_SMILER)
				.add(ModBiomes.Level0Biomes.BLACKOUT_BIOME)
				.add(ModBiomes.Level1Biomes.GOTHIC_BIOME)
				.add(ModBiomes.Level1Biomes.OUROBOROS_BIOME)
				.add(ModBiomes.LEVEL3_BIOME)
				.add(ModBiomes.Level1Biomes.OUROBOROS_BIOME)
				.add(ModBiomes.LEVEL2_BIOME);

		builder(ModTags.SPAWN_SKINSTEALER)
				.add(ModBiomes.Level1Biomes.OUROBOROS_BIOME)
				.add(ModBiomes.LEVEL2_BIOME)
				.add(ModBiomes.LEVEL3_BIOME);

		builder(ModTags.SPAWN_WRETCH)
				.add(ModBiomes.LEVEL2_BIOME)
				.add(ModBiomes.LEVEL3_BIOME);

		builder(ModTags.SPAWN_PARTYGOER)
				.add(ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME)
				.add(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME);

		builder(ModTags.SPAWN_PARTYGOAT_partypooper)
				.add(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME)
				.add(ModBiomes.PROMISED_BIOME);

		builder(ModTags.SPAWN_BALLOON)
				.add(ModBiomes.Level1Biomes.AQUILA_BIOME)
				.add(ModBiomes.LEVEL3_BIOME)
				.add(ModBiomes.LEVEL2_BIOME);

		builder(ModTags.SPAWN_NEIGHBORHOOD_WATCH)
				.add(ModBiomes.LEVEL9_BIOME);

		builder(ModTags.SPAWN_SPIDER)
				.add(ModBiomes.Level1Biomes.GOTHIC_BIOME)
				.add(ModBiomes.LEVEL8_CAVESYSTEM_BIOME);
	}

}
