package com.boxyplayz.backrooms.common.effect;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.effect.custom.GardenersPainEffect;
import com.boxyplayz.backrooms.common.effect.custom.WretchedCycleEffect;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
	private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(
			BoxysBackroomsCommon.MOD_ID,
			Registries.MOB_EFFECT);

	public static final RegistrySupplier<MobEffect> GARDENERS_PAIN = MOB_EFFECTS.register("gardeners_pain",
			() -> new GardenersPainEffect(MobEffectCategory.HARMFUL, 2210324));

	public static final ResourceKey<MobEffect> gardenersPainKey = ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(),
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "gardeners_pain"));

	public static final Holder<MobEffect> WRETCHED_CYCLE = MOB_EFFECTS.register("wretched_cycle",
			() -> new WretchedCycleEffect(MobEffectCategory.HARMFUL, 2210324));

	public static final ResourceKey<MobEffect> wretchedCycleKey = ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(),
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "wretched_cycle"));

	public static void RegisterModEffects() {
		MOB_EFFECTS.register();
	}
}
