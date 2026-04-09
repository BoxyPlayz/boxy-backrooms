package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.clock.ModWorldClocks;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.events.ModEvents;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.loot.ModLootTables;
import com.boxyplayz.backrooms.tags.ModTags;
import com.boxyplayz.backrooms.toolMaterials.ModToolMaterials;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.dimension.ModDimensionTypes;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;
import com.boxyplayz.backrooms.world.dimension.ModLevelStems;
import com.boxyplayz.backrooms.world.generators.ModChunkGenerators;
import com.boxyplayz.backrooms.world.spawning.ModEntitySpawner;

public class BoxysBackrooms implements ModInitializer {
	public static final String MOD_ID = "boxys_backrooms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeTabs.RegisterModCreativeTabs();
		ModEntities.RegisterModEntities();
		ModBlocks.RegisterModBlocks();
		ModDimensionTypes.RegisterModDimensionTypes();
		ModDimensions.RegisterModDimensions();
		ModEvents.RegisterModEvents();
		ModChunkGenerators.registerModChunkGenerators();
		ModLootTables.RegisterLootTables();
		ModBiomes.RegisterModBiomes();
		ModTags.RegisterModTags();
		ModEntitySpawner.RegisterModMobSpawning();
		ModEffects.RegisterModEffects();
		ModToolMaterials.RegisterToolMaterials();
		ModWorldClocks.RegisterModWorldClocks();
		ModLevelStems.RegisterModStems();
	}
}