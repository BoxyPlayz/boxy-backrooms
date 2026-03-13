package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

public class BoxysBackroomsDamageTypeTagProvider extends FabricTagProvider<DamageType> {

	public static final TagKey<DamageType> physicalAttacks = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "physical_attacks"));

	public BoxysBackroomsDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		builder(physicalAttacks)
				.add(DamageTypes.FALLING_ANVIL)
				.add(DamageTypes.PLAYER_ATTACK)
				.add(DamageTypes.FALL)
				.add(DamageTypes.FALLING_ANVIL)
				.add(DamageTypes.SPEAR)
				.add(DamageTypes.STALAGMITE)
				.add(DamageTypes.STARVE);
	}

}
