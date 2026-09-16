package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

import com.boxyplayz.backrooms.block.ModBlockEntities;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.recipe.ModRecipes;
import com.boxyplayz.backrooms.entity.EntityAttributeRegister;
import com.boxyplayz.backrooms.events.ModEvents;
import com.boxyplayz.backrooms.world.ModChunkGenerators;
import com.boxyplayz.backrooms.world.ModEntitySpawner;

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
		EntityAttributeRegister.RegisterEntityAttributes();
		ModBlocks.RegisterModBlocks();
		ModChunkGenerators.registerModChunkGenerators();
		ModEvents.RegisterModEvents();
		ModEntitySpawner.RegisterModMobSpawning();
		ModBlockEntities.RegisterModBlockEntities();
		BoxysBackroomsCommon.init();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipes.BLENDING_RECIPE_SERIALIZER);
	}
}