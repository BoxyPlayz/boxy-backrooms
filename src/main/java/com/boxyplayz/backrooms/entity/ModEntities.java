package com.boxyplayz.backrooms.entity;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;

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

	public static void RegisterModEntities() {
		BoxysBackrooms.LOGGER.debug("Registering Entities");
		FabricDefaultAttributeRegistry.register(SMILER,
				SmilerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(SKINSTEALER,
				SkinStealerEntity.createAttributes());
	}
}
