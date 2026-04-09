package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.world.dimension.ModDimensionTypes;
import com.boxyplayz.backrooms.world.dimension.ModLevelStems;
import com.boxyplayz.backrooms.world.generators.TheBrokenChunkGen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.LevelStem;

public class LevelStemProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<LevelStem> context, ResourceKey<LevelStem> key,
			LevelStem stem) {
		context.register(key, stem);
	}

	public LevelStemProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "SteamRooms";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
	}

	public static void bootstrap(BootstrapContext<LevelStem> context) {
		register(context, ModLevelStems.BROKEN_LEVEL_STEM,
				new LevelStem(
						context.lookup(Registries.DIMENSION_TYPE)
								.getOrThrow(ModDimensionTypes.THE_BROKEN_DIMENSION_TYPE),
						(new TheBrokenChunkGen(context.lookup(Registries.BIOME).getOrThrow(Biomes.THE_VOID)))));
	}
}
