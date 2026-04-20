package com.boxyplayz.backrooms.entity;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.entity.custom.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.entity.custom.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.entity.custom.Wretch.WretchEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
	static ResourceKey<EntityType<?>> smilerResourceKey = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"));
	public static final EntityType<SmilerEntity> SMILER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"),
			EntityType.Builder.of(SmilerEntity::new, MobCategory.MONSTER).sized(1, 2)
					.build(smilerResourceKey));

	static ResourceKey<EntityType<?>> skinStealerResourceKey = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "skinstealer"));
	public static final EntityType<SkinStealerEntity> SKINSTEALER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "skinstealer"),
			EntityType.Builder.of(SkinStealerEntity::new, MobCategory.MONSTER).sized(1, 2)
					.build(skinStealerResourceKey));

	static ResourceKey<EntityType<?>> wretchResourceKey = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretch"));
	public static final EntityType<WretchEntity> WRETCH = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretch"),
			EntityType.Builder.of(WretchEntity::new, MobCategory.MONSTER).sized(1f, 2.5f)
					.build(wretchResourceKey));

	static ResourceKey<EntityType<?>> partygoerKey = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "partygoer"));
	public static final EntityType<PartygoerEntity> PARTYGOER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "partygoer"),
			EntityType.Builder.of(PartygoerEntity::new, MobCategory.MONSTER).sized(1f, 2.5f)
					.build(partygoerKey));

	static ResourceKey<EntityType<?>> balloonKey = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "balloon"));
	public static final EntityType<BalloonEntity> BALLOON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "balloon"),
			EntityType.Builder.of(BalloonEntity::new, MobCategory.CREATURE).sized(1f, 1.6f)
					.build(balloonKey));

	public static void RegisterModEntities() {
		FabricDefaultAttributeRegistry.register(SMILER,
				SmilerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(SKINSTEALER,
				SkinStealerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(WRETCH,
				WretchEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(PARTYGOER,
				PartygoerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(BALLOON,
				BalloonEntity.createAttributes());
	}
}
