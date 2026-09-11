package com.boxyplayz.backrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {

	public static ResourceKey<LootTable> LEVEL_11_ROOF_CHEST_LOOT = ResourceKey.create(Registries.LOOT_TABLE,
			Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "chests/level_11_roof"));

	public static void RegisterLootTables() {

	}
}
