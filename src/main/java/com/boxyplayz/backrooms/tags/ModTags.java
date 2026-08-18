package com.boxyplayz.backrooms.tags;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

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

	public static final TagKey<DamageType> FIRE_ATTACKS = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "fire_attacks"));

	public static final TagKey<DamageType> ANOMALY_DAMAGE = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "anomaly_damage"));

	public static final TagKey<Block> FUN_BLOCKS = TagKey.create(Registries.BLOCK,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "fun_blocks"));

	public static final TagKey<Biome> DASH_ENABLED = TagKey.create(Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "dash_enabled"));

	public static final TagKey<Biome> LARGE_JUMP = TagKey.create(Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "large_jump"));

	public static void RegisterModTags() {

	}
}
