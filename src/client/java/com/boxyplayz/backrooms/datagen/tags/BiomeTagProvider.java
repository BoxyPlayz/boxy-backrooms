package com.boxyplayz.backrooms.datagen.tags;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.world.biome.ModBiomes;

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
	}

}
