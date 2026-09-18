package com.boxyplayz.backrooms.common.world;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.entity.living.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.common.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;
import com.boxyplayz.backrooms.common.entity.living.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.entity.living.Wretch.WretchEntity;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.Heightmap.Types;

/**
 * Mob Spawner
 */
public class ModEntitySpawner {
	/**
	 * Register mob spawns
	 */
	public static void RegisterModMobSpawning() {
		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_SMILER), (context, properties) -> {
			properties.getSpawnProperties().addSpawn(MobCategory.MONSTER, new SpawnerData(ModEntities.SMILER, 1, 3),
					20);
		});

		SpawnPlacementsRegistry.register(() -> ModEntities.SMILER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				SmilerEntity::CheckSpawnRules);

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_SKINSTEALER),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.MONSTER,
							new SpawnerData(ModEntities.SKINSTEALER, 1, 2),
							5);
				});

		SpawnPlacementsRegistry.register(() -> ModEntities.SKINSTEALER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				SkinStealerEntity::CheckSpawnRules);

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_WRETCH),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.MONSTER,
							new SpawnerData(ModEntities.WRETCH, 1, 1),
							2);
				});

		SpawnPlacementsRegistry.register(() -> ModEntities.WRETCH, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				WretchEntity::CheckSpawnRules);

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_PARTYGOER),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.MONSTER,
							new SpawnerData(ModEntities.PARTYGOER, 3, 8),
							7);
				});

		SpawnPlacementsRegistry.register(() -> ModEntities.PARTYGOER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				PartygoerEntity::CheckSpawnRules);

		SpawnPlacementsRegistry.register(() -> ModEntities.BALLOON, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				BalloonEntity::CheckSpawnRules);

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_PARTYGOAT_partypooper),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.CREATURE,
							new SpawnerData(ModEntities.PARTYPOOPER, 1, 2),
							7);
				});

		SpawnPlacementsRegistry.register(() -> ModEntities.PARTYPOOPER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				PartypooperEntity::CheckSpawnRules);

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_BALLOON),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.CREATURE,
							new SpawnerData(ModEntities.BALLOON, 1, 1),
							1);
				});

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_PARTYGOAT_partypooper),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.CREATURE,
							new SpawnerData(ModEntities.PARTYPOOPER, 1, 2),
							7);
				});

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_SPIDER),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.MONSTER,
							new SpawnerData(EntityType.SPIDER, 1, 2),
							3);
				});

		BiomeModifications.addProperties(context -> context.hasTag(ModTags.SPAWN_NEIGHBORHOOD_WATCH),
				(context, properties) -> {
					properties.getSpawnProperties().addSpawn(MobCategory.MONSTER,
							new SpawnerData(ModEntities.NEIGHBORHOOD_WATCH, 1, 1),
							38);
				});

		SpawnPlacementsRegistry.register(() -> ModEntities.NEIGHBORHOOD_WATCH, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				NeighborhoodWatchEntity::CheckSpawnRules);
	}
}
