package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.utils.DimensionTypeBuilder;
import com.boxyplayz.backrooms.world.dimension.ModDimensionTypes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;

public class BoxyBackroomsDimensionTypeProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<DimensionType> context, ResourceKey<DimensionType> key,
			DimensionType type) {
		context.register(key, type);
	}

	public BoxyBackroomsDimensionTypeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
	}

	public static void bootstrap(BootstrapContext<DimensionType> context) {
		register(context, ModDimensionTypes.PITFALLS_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(80)
				.setFixedTime(true)
				.build());
	}
}
