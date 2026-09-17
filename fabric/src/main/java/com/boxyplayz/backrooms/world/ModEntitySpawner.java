package com.boxyplayz.backrooms.world;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.entity.living.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.common.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;
import com.boxyplayz.backrooms.common.entity.living.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.entity.living.Wretch.WretchEntity;
import com.boxyplayz.backrooms.common.world.ModBiomes;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
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
			properties.getSpawnProperties().addSpawn(null, new SpawnerData(ModEntities.SMILER, 1, 3), 20);
		});

		SpawnPlacementsRegistry.register(() -> ModEntities.SMILER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				SmilerEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.OUROBOROS_BIOME, ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.MONSTER, ModEntities.SKINSTEALER, 5, 1, 1);

		SpawnPlacementsRegistry.register(() -> ModEntities.SKINSTEALER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				SkinStealerEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.OUROBOROS_BIOME, ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.MONSTER, ModEntities.WRETCH, 2, 1, 1);

		SpawnPlacementsRegistry.register(() -> ModEntities.WRETCH, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				WretchEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME),
				MobCategory.MONSTER, ModEntities.PARTYGOER, 7, 3, 8);

		SpawnPlacementsRegistry.register(() -> ModEntities.PARTYGOER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				PartygoerEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.AQUILA_BIOME),
				MobCategory.CREATURE, ModEntities.BALLOON, 1, 1, 1);

		SpawnPlacementsRegistry.register(() -> ModEntities.BALLOON, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				BalloonEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.PROMISED_BIOME),
				MobCategory.CREATURE, ModEntities.PARTYPOOPER, 1, 1, 3);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LevelFunBiomes.HALLWAYS_BIOME),
				MobCategory.CREATURE, ModEntities.PARTYPOOPER, 1, 1, 1);

		SpawnPlacementsRegistry.register(() -> ModEntities.PARTYPOOPER, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				PartypooperEntity::CheckSpawnRules);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LEVEL3_BIOME),
				MobCategory.CREATURE, ModEntities.BALLOON, 1, 1, 1);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.CREATURE, EntityType.SPIDER, 1, 1, 2);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LEVEL3_BIOME),
				MobCategory.MONSTER, ModEntities.SKINSTEALER, 12, 1, 1);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LEVEL3_BIOME),
				MobCategory.MONSTER, ModEntities.WRETCH, 14, 1, 3);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LEVEL2_BIOME),
				MobCategory.MONSTER, ModEntities.SKINSTEALER, 7, 1, 1);

		net.fabricmc.fabric.api.biome.v1.BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LEVEL9_BIOME),
				MobCategory.MONSTER, ModEntities.NEIGHBORHOOD_WATCH, 28, 1, 1);

		SpawnPlacementsRegistry.register(() -> ModEntities.NEIGHBORHOOD_WATCH, SpawnPlacementTypes.ON_GROUND,
				Types.WORLD_SURFACE,
				NeighborhoodWatchEntity::CheckSpawnRules);
	}
}
