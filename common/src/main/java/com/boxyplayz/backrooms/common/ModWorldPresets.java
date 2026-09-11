package com.boxyplayz.backrooms.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

public class ModWorldPresets {
	public static ResourceKey<WorldPreset> CITY_LIFE = ResourceKey.create(Registries.WORLD_PRESET,
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "city"));

	public static void RegisterPresets() {

	}
}
