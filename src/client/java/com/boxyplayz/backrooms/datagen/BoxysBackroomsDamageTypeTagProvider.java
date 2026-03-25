package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

public class BoxysBackroomsDamageTypeTagProvider extends FabricTagsProvider<DamageType> {

	public static final TagKey<DamageType> physicalAttacks = TagKey.create(Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "physical_attacks"));

	public BoxysBackroomsDamageTypeTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
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
