package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.damagetypes.ModDamageTypes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;

public class DamageTypeProvider extends FabricDynamicRegistryProvider {

	private static void register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key,
			DamageType damageType) {
		context.register(key, damageType);
	}

	public DamageTypeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "DamageTypeProvider";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));
	}

	public static void bootstrap(BootstrapContext<DamageType> context) {
		register(context, ModDamageTypes.ANOMALY_DAMAGE,
				new DamageType("anomaly_damage", DamageScaling.ALWAYS, 0, DamageEffects.HURT,
						DeathMessageType.DEFAULT));
	}

}
