package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.utils.DimensionTypeBuilder;
import com.boxyplayz.backrooms.world.dimension.ModDimensionTypes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.DimensionType.Skybox;

public class DimensionTypeProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<DimensionType> context, ResourceKey<DimensionType> key,
			DimensionType type) {
		context.register(key, type);
	}

	public DimensionTypeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "DimensionTypeRooms";
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
				.setAmbientLight(0.2f)
				.setSkylight(true)
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

		register(context, ModDimensionTypes.THE_BROKEN_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0)
				.setSkylight(true)
				.setCeiling(true)
				.build());

		register(context, ModDimensionTypes.LEVEL8_CAVESYSTEM_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensionTypes.BLUE_CHANNEL_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0.3f)
				.setSkylight(true)
				.setCeiling(false)
				.setInfiniburn(BlockTags.ICE)
				.build());

		register(context, ModDimensionTypes.LEVEL6_DIMENSION_TYPE, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

	}
}
