package com.boxyplayz.backrooms.datagen.tags;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.damagetypes.ModDamageTypes;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

public class DamageTypeTagProvider extends FabricTagsProvider<DamageType> {

	public DamageTypeTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		builder(ModTags.PHYSICAL_ATTACKS)
				.add(DamageTypes.FALLING_ANVIL)
				.add(DamageTypes.PLAYER_ATTACK)
				.add(DamageTypes.FALL)
				.add(DamageTypes.FALLING_ANVIL)
				.add(DamageTypes.SPEAR)
				.add(DamageTypes.STALAGMITE)
				.add(DamageTypes.STARVE);

		builder(ModTags.FIRE_ATTACKS)
				.add(DamageTypes.FIREBALL)
				.add(DamageTypes.IN_FIRE)
				.add(DamageTypes.ON_FIRE)
				.add(DamageTypes.UNATTRIBUTED_FIREBALL)
				.add(DamageTypes.LAVA);

		builder(DamageTypeTags.BYPASSES_ARMOR)
				.add(ModDamageTypes.ANOMALY_DAMAGE);

		builder(ModTags.ANOMALY_DAMAGE)
				.add(ModDamageTypes.ANOMALY_DAMAGE);
	}

}
