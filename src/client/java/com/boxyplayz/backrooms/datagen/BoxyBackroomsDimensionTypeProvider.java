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
import net.minecraft.world.level.dimension.DimensionType.Skybox;

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

		register(context, ModDimensionTypes.LEVEL0_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(32)
				.setFixedTime(true)
				.setMinY(-16)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensionTypes.LEVEL1_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(64)
				.setFixedTime(true)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensionTypes.LEVEL7_OCEAN_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(304)
				.setFixedTime(false)
				.setAmbientLight(0)
				.setSkylight(false)
				.setCeiling(false)
				.build());

		register(context, ModDimensionTypes.LEVEL94_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(false)
				.setAmbientLight(0)
				.setSkylight(true)
				.setCeiling(false)
				.build());
	}
}
