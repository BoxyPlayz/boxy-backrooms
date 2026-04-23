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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec2;

public class ModEntities {
	/**
	 * Registers a new entity (type)
	 * 
	 * @param <T>      Entity Type
	 * @param factory  Entity Factory
	 * @param id       Id of the entity
	 * @param size     Size of the entity in width and height
	 * @param category Category of the mob
	 * @return Entity Type
	 */
	protected static <T extends Entity> EntityType<T> RegisterEntity(EntityType.EntityFactory<T> factory, String id,
			Vec2 size, MobCategory category) {
		ResourceKey<EntityType<?>> resourceKey = ResourceKey.create(
				BuiltInRegistries.ENTITY_TYPE.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, id));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, id),
				EntityType.Builder.of(factory, category).sized(size.x, size.y)
						.build(resourceKey));
	}

	/**
	 * Registers a new entity (type)
	 * 
	 * @param <T>     Entity Type
	 * @param factory Entity Factory
	 * @param id      Id of the entity
	 * @param size    Size of the entity in width and height
	 * @return Entity Type
	 */
	protected static <T extends Entity> EntityType<T> RegisterEntity(EntityType.EntityFactory<T> factory, String id,
			Vec2 size) {
		return RegisterEntity(factory, id, size, MobCategory.MONSTER);
	}

	/**
	 * Registers a new entity (type)
	 * 
	 * @param <T>     Entity Type
	 * @param factory Entity Factory
	 * @param id      Id of the entity
	 * @return Entity Type
	 */
	protected static <T extends Entity> EntityType<T> RegisterEntity(EntityType.EntityFactory<T> factory, String id) {
		return RegisterEntity(factory, id, new Vec2(1, 2));
	}

	/**
	 * Registers a new entity (type)
	 * 
	 * @param <T>      Entity Type
	 * @param factory  Entity Factory
	 * @param id       Id of the entity
	 * @param category Category of the mob
	 * @return Entity Type
	 */
	protected static <T extends Entity> EntityType<T> RegisterEntity(EntityType.EntityFactory<T> factory, String id,
			MobCategory category) {
		return RegisterEntity(factory, id, new Vec2(1, 2), category);
	}

	public static final EntityType<SmilerEntity> SMILER = RegisterEntity(SmilerEntity::new, "smiler");

	public static final EntityType<SkinStealerEntity> SKINSTEALER = RegisterEntity(SkinStealerEntity::new,
			"skinstealer");

	public static final EntityType<WretchEntity> WRETCH = RegisterEntity(WretchEntity::new, "wretch",
			new Vec2(1f, 2.5f));

	public static final EntityType<PartygoerEntity> PARTYGOER = RegisterEntity(PartygoerEntity::new, "partygoer",
			new Vec2(1f, 2.5f));

	public static final EntityType<BalloonEntity> BALLOON = RegisterEntity(BalloonEntity::new, "balloon",
			new Vec2(1f, 1.6f), MobCategory.CREATURE);

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
