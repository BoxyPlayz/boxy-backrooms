package com.boxyplayz.backrooms.world.spawning;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.custom.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.entity.custom.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.entity.custom.Wretch.WretchEntity;
import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap.Types;

public class ModEntitySpawner {
	public static void RegisterModMobSpawning() {
		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(ModBiomes.Level0Biomes.BLACKOUT_BIOME,
						ModBiomes.Level1Biomes.OUROBOROS_BIOME, ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.MONSTER, ModEntities.SMILER, 20, 1, 3);

		SpawnPlacements.register(ModEntities.SMILER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				SmilerEntity::CheckSpawnRules);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.OUROBOROS_BIOME, ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.MONSTER, ModEntities.SKINSTEALER, 5, 1, 1);

		SpawnPlacements.register(ModEntities.SKINSTEALER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				SkinStealerEntity::CheckSpawnRules);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.OUROBOROS_BIOME, ModBiomes.Level1Biomes.GOTHIC_BIOME),
				MobCategory.MONSTER, ModEntities.WRETCH, 2, 1, 1);

		SpawnPlacements.register(ModEntities.WRETCH, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				WretchEntity::CheckSpawnRules);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME),
				MobCategory.MONSTER, ModEntities.PARTYGOER, 2, 3, 8);

		SpawnPlacements.register(ModEntities.PARTYGOER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				PartygoerEntity::CheckSpawnRules);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(
						ModBiomes.Level1Biomes.AQUILA_BIOME),
				MobCategory.AMBIENT, ModEntities.BALLOON, 1, 1, 1);

		SpawnPlacements.register(ModEntities.BALLOON, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				BalloonEntity::CheckSpawnRules);
	}
}
