package com.boxyplayz.backrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.clock.WorldClock;

public class ModWorldClocks {
	public static final ResourceKey<WorldClock> LEVEL_94_CLOCK = ResourceKey.create(
			Registries.WORLD_CLOCK,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));

	public static void RegisterModWorldClocks() {
	}
}
