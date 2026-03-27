package com.boxyplayz.backrooms.tags;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;

public class ModTags {
	public static final TagKey<DamageType> physicalAttacks = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "physical_attacks"));

	public static final TagKey<Item> LIGHT_BLOCKS = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "light_blocks"));

	public static void RegisterModTags() {

	}
}
