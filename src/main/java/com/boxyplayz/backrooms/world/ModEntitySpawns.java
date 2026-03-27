package com.boxyplayz.backrooms.world;

import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.entity.ModEntities;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntitySpawns {
	public static void AddSpawns() {
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.Level0Biomes.BLACKOUT_BIOME),
				MobCategory.MONSTER, ModEntities.SMILER, 20, 1, 3);

		SpawnPlacements.register(ModEntities.SMILER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG,
				null);
	}
}
