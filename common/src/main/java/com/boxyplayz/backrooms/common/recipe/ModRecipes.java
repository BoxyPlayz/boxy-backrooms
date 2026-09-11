package com.boxyplayz.backrooms.common.recipe;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.recipe.blending.BlendingRecipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
	public static final RecipeSerializer<BlendingRecipe> BLENDING_RECIPE_SERIALIZER = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "blending"),
			new RecipeSerializer<>(BlendingRecipe.CODEC, BlendingRecipe.STREAM_CODEC));

	public static final RecipeType<BlendingRecipe> BLENDING_RECIPE_TYPE = Registry.register(
			BuiltInRegistries.RECIPE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "blending"),
			new RecipeType<BlendingRecipe>() {
			});

	public static void RegisterModRecipes() {

	}
}
