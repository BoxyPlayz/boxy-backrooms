package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.chunkgen.ModChunkGenerators;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.dimension.ModDimensions;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.events.ModEvents;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.loot.ModLootTables;

public class BoxysBackrooms implements ModInitializer {
	public static final String MOD_ID = "boxys_backrooms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeTabs.RegisterModCreativeTabs();
		ModEntities.RegisterModEntities();
		ModBlocks.RegisterModBlocks();
		ModDimensions.RegisterModDimensions();
		ModEvents.RegisterModEvents();
		ModChunkGenerators.registerModChunkGenerators();
		ModLootTables.RegisterLootTables();
		ModBiomes.RegisterModBiomes();
	}
}