package com.boxyplayz.backrooms.common.entity;

import java.util.ArrayList;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.entity.living.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.common.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;
import com.boxyplayz.backrooms.common.entity.living.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.entity.living.Wretch.WretchEntity;
import com.boxyplayz.backrooms.common.entity.projectile.liquid_pain.LiquidPainProjectile;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.camel.CamelHusk;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.phys.Vec2;

public class ModEntities {

	public static ArrayList<Class<? extends Mob>> partyPooperTargetEntities = new ArrayList<>();

	private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
			BoxysBackroomsCommon.MOD_ID,
			Registries.ENTITY_TYPE);

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
	protected static <T extends Entity> RegistrySupplier<EntityType<T>> RegisterEntity(
			EntityType.EntityFactory<T> factory, String id,
			Vec2 size, MobCategory category) {
		ResourceKey<EntityType<?>> resourceKey = ResourceKey.create(
				BuiltInRegistries.ENTITY_TYPE.key(),
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, id));
		return ENTITY_TYPES.register(id,
				() -> EntityType.Builder.of(factory, category).sized(size.x, size.y)
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
	protected static <T extends Entity> RegistrySupplier<EntityType<T>> RegisterEntity(
			EntityType.EntityFactory<T> factory, String id,
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
	protected static <T extends Entity> RegistrySupplier<EntityType<T>> RegisterEntity(
			EntityType.EntityFactory<T> factory, String id) {
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
	protected static <T extends Entity> RegistrySupplier<EntityType<T>> RegisterEntity(
			EntityType.EntityFactory<T> factory, String id,
			MobCategory category) {
		return RegisterEntity(factory, id, new Vec2(1, 2), category);
	}

	public static final RegistrySupplier<EntityType<SmilerEntity>> SMILER = RegisterEntity(SmilerEntity::new, "smiler");

	public static final RegistrySupplier<EntityType<SkinStealerEntity>> SKINSTEALER = RegisterEntity(
			SkinStealerEntity::new,
			"skinstealer");

	public static final RegistrySupplier<EntityType<WretchEntity>> WRETCH = RegisterEntity(WretchEntity::new, "wretch",
			new Vec2(1f, 2.5f));

	public static final RegistrySupplier<EntityType<PartygoerEntity>> PARTYGOER = RegisterEntity(
			PartygoerEntity::new,
			"partygoer",
			new Vec2(1f, 2.5f));

	public static final RegistrySupplier<EntityType<BalloonEntity>> BALLOON = RegisterEntity(BalloonEntity::new,
			"balloon",
			new Vec2(1f, 1.6f), MobCategory.CREATURE);

	public static final RegistrySupplier<EntityType<PartypooperEntity>> PARTYPOOPER = RegisterEntity(
			PartypooperEntity::new,
			"partypooper", MobCategory.CREATURE);

	public static final RegistrySupplier<EntityType<NeighborhoodWatchEntity>> NEIGHBORHOOD_WATCH = RegisterEntity(
			NeighborhoodWatchEntity::new,
			"neighborhood_watch", new Vec2(1.4f, 1.8f), MobCategory.MONSTER);

	public static final RegistrySupplier<EntityType<LiquidPainProjectile>> LIQUID_PAIN_PROJECTILE = RegisterEntity(
			LiquidPainProjectile::new, "liquid_pain",
			new Vec2(0.25f, 0.25f), MobCategory.MISC);

	public static void Register() {
		ENTITY_TYPES.register();

		partyPooperTargetEntities.add(ZombieHorse.class);
		partyPooperTargetEntities.add(CamelHusk.class);
		partyPooperTargetEntities.add(Skeleton.class);
		partyPooperTargetEntities.add(Stray.class);
		partyPooperTargetEntities.add(Creeper.class);
		partyPooperTargetEntities.add(Silverfish.class);
		partyPooperTargetEntities.add(Husk.class);
		partyPooperTargetEntities.add(Ravager.class);
		partyPooperTargetEntities.add(Zoglin.class);
		partyPooperTargetEntities.add(Spider.class);
		partyPooperTargetEntities.add(Vex.class);
		partyPooperTargetEntities.add(Zombie.class);
		partyPooperTargetEntities.add(Bogged.class);
		partyPooperTargetEntities.add(Parched.class);
	}
}
