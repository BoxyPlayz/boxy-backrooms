package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.block.ModBlockEntities;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.events.ModEvents;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.networking.ModPayloads;
import com.boxyplayz.backrooms.recipe.ModRecipes;
import com.boxyplayz.backrooms.world.ModChunkGenerators;
import com.boxyplayz.backrooms.world.ModDimensions;
import com.boxyplayz.backrooms.world.ModEntitySpawner;
import com.boxyplayz.backrooms.world.ModStructures;

public class BoxysBackrooms implements ModInitializer {
	/**
	 * Mod ID for Boxy's Backrooms
	 */
	public static final String MOD_ID = "boxys_backrooms";
	/**
	 * Logger for Boxy's Backrooms
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
		ModRecipes.RegisterModRecipes();
		ModPayloads.RegsiterModPayloads();
		ModChunkGenerators.registerModChunkGenerators();
		ModEvents.RegisterModEvents();
		ModLootTables.RegisterLootTables();
		ModBiomes.RegisterModBiomes();
		ModTags.RegisterModTags();
		ModEntitySpawner.RegisterModMobSpawning();
		ModEffects.RegisterModEffects();
		ModToolMaterials.RegisterToolMaterials();
		ModWorldClocks.RegisterModWorldClocks();
		ModDamageTypes.RegisterDamageTypes();
		ModEnchantments.RegisterModEnchantments();
		ModBlockEntities.RegisterModBlockEntities();
		ModWorldPresets.RegisterPresets();
		ModStructures.RegisterStructures();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipes.BLENDING_RECIPE_SERIALIZER);
	}
}