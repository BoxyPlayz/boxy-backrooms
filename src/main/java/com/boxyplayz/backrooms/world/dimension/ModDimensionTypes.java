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

	public static void RegisterModDimensionTypes() {
	}
}
