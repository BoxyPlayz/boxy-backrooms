package com.boxyplayz.backrooms.tags;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;

public class ModTags {

	public static final TagKey<Item> NOCLIPPABLES = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "noclippable"));

	public static final TagKey<Item> ALMOND_WATERS = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "almond_waters"));

	public static final TagKey<DamageType> PHYSICAL_ATTACKS = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "physical_attacks"));

	public static final TagKey<Item> LIGHT_ITEMS = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "light_blocks"));

	public static final TagKey<Item> FIRESTEEL_REPAIR_ITEMS = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "firesteel_repair_items"));

	public static void RegisterModTags() {

	}
}
