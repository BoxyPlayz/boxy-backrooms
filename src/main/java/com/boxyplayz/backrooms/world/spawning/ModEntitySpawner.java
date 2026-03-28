package com.boxyplayz.backrooms.world.spawning;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap.Types;

public class ModEntitySpawner {
	public static void RegisterModMobSpawning() {
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.Level0Biomes.BLACKOUT_BIOME),
				MobCategory.MONSTER, ModEntities.SMILER, 20, 1, 3);

		SpawnPlacements.register(ModEntities.SMILER, SpawnPlacementTypes.ON_GROUND, Types.WORLD_SURFACE,
				SmilerEntity::CheckSmilerSpawnRules);
	}
}
