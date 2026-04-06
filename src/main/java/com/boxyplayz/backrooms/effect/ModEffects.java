package com.boxyplayz.backrooms.effect;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.effect.custom.GardenersPainEffect;
import com.boxyplayz.backrooms.effect.custom.WretchedCycleEffect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
	public static final Holder<MobEffect> GARDENERS_PAIN = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "gardeners_pain"),
			new GardenersPainEffect(MobEffectCategory.HARMFUL, 2210324));

	public static final ResourceKey<MobEffect> gardenersPainKey = ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "gardeners_pain"));

	public static final Holder<MobEffect> WRETCHED_CYCLE = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretched_cycle"),
			new WretchedCycleEffect(MobEffectCategory.HARMFUL, 2210324));

	public static final ResourceKey<MobEffect> wretchedCycleKey = ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretched_cycle"));

	public static void RegisterModEffects() {

	}
}
