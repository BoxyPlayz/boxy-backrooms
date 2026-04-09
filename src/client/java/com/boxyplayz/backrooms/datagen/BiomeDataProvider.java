package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class BiomeDataProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key,
			Biome type) {
		context.register(key, type);
	}

	public BiomeDataProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "BiomeRoomsorsomething";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.BIOME));
	}

	public static void bootstrap(BootstrapContext<Biome> context) {
		register(context, ModBiomes.PITFALLS_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build())
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 16106001)
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL94_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(20479)
						.foliageColorOverride(65290)
						.grassColorOverride(65290)
						.dryFoliageColorOverride(15098112)
						.build())
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 53759)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 53759)
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());
	}
}
