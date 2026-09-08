package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.ModBiomes;
import com.boxyplayz.backrooms.ModWorldPresets;
import com.boxyplayz.backrooms.world.ModDimensions;
import com.boxyplayz.backrooms.world.generators.Level11ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level3ChunkGen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

public class WorldPresetGenerator extends FabricDynamicRegistryProvider {

	public WorldPresetGenerator(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "presets";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.WORLD_PRESET));
	}

	public static void bootstrap(BootstrapContext<WorldPreset> context) {
		HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		context.register(ModWorldPresets.CITY_LIFE,
				new WorldPreset(
						Map.of(LevelStem.OVERWORLD, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL11.type),
								new Level11ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL11_BIOME))),
								LevelStem.NETHER, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL3.type),
										new Level3ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL3_BIOME))))));
	}

}
