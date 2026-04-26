package com.boxyplayz.backrooms.world.dimension;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensionTypes {
	public static final ResourceKey<DimensionType> PITFALLS_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"));

	public static final ResourceKey<DimensionType> LEVEL0_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"));

	public static final ResourceKey<DimensionType> LEVEL1_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1"));

	public static final ResourceKey<DimensionType> LEVEL7_OCEAN_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

	public static final ResourceKey<DimensionType> LEVEL94_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));

	public static final ResourceKey<DimensionType> THE_BROKEN_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "the_broken"));

	public static final ResourceKey<DimensionType> LEVEL8_CAVESYSTEM_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

	public static final ResourceKey<DimensionType> BLUE_CHANNEL_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue_channel"));

	public static final ResourceKey<DimensionType> LEVEL6_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level6"));

	public static final ResourceKey<DimensionType> LEVELFUN_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun"));

	public static final ResourceKey<DimensionType> PROMISED_LAND_DIMENSION_TYPE = ResourceKey.create(
			Registries.DIMENSION_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "promised_land"));

	public static void RegisterModDimensionTypes() {
	}
}
