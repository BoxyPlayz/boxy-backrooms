package com.boxyplayz.backrooms.tags;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class ModTags {
	public static final TagKey<DamageType> physicalAttacks = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "physical_attacks"));

	public static void RegisterModTags() {

	}
}
