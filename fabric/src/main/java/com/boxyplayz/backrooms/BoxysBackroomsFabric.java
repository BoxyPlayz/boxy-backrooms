package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

import com.boxyplayz.backrooms.block.ModBlockEntities;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.recipe.ModRecipes;
import com.boxyplayz.backrooms.common.world.ModDimensions;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.events.ModEvents;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.networking.ModPayloads;
import com.boxyplayz.backrooms.world.ModChunkGenerators;
import com.boxyplayz.backrooms.world.ModEntitySpawner;
import com.boxyplayz.backrooms.world.ModStructures;

public class BoxysBackroomsFabric implements ModInitializer {
	/**
	 * Mod ID for Boxy's Backrooms
	 */
	public static final String MOD_ID = "boxys_backrooms";

	/**
	 * Begin.
	 */
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeTabs.RegisterModCreativeTabs();
		ModEntities.RegisterModEntities();
		ModBlocks.RegisterModBlocks();
		ModDimensions.RegisterModDimensions();
		ModPayloads.RegsiterModPayloads();
		ModChunkGenerators.registerModChunkGenerators();
		ModEvents.RegisterModEvents();
		ModLootTables.RegisterLootTables();
		ModTags.RegisterModTags();
		ModEntitySpawner.RegisterModMobSpawning();
		ModEffects.RegisterModEffects();
		ModToolMaterials.RegisterToolMaterials();
		ModEnchantments.RegisterModEnchantments();
		ModBlockEntities.RegisterModBlockEntities();
		ModStructures.RegisterStructures();

		BoxysBackroomsCommon.init();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipes.BLENDING_RECIPE_SERIALIZER);
	}
}