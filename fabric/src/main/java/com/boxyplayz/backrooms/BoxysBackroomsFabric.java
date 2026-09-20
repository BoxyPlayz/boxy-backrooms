package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

import com.boxyplayz.backrooms.block.FabricBlocks;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.recipe.ModRecipes;

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
		FabricBlocks.RegisterFabricSidedBlocks();
		BoxysBackroomsCommon.init();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipes.BLENDING_RECIPE_SERIALIZER.get());
	}
}