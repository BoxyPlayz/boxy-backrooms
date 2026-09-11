package com.boxyplayz.backrooms.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
	public static final ResourceKey<DamageType> ANOMALY_DAMAGE = ResourceKey.create(
			Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "anomaly_damage"));

	public static void RegisterDamageTypes() {

	}
}
