package com.boxyplayz.backrooms.enchantments;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
	private static ResourceKey<Enchantment> key(String path) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, path);
		return ResourceKey.create(Registries.ENCHANTMENT, id);
	}

	public static final ResourceKey<Enchantment> ANOMALOUS_PROTECTION = key("anomalous_protection");

	public static void RegisterModEnchantments() {

	}
}
