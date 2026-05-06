package com.boxyplayz.backrooms.recipe;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.menu.BlenderMenu;
import com.boxyplayz.backrooms.recipe.blending.BlendingRecipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
	public static final RecipeSerializer<BlendingRecipe> BLENDING_RECIPE_SERIALIZER = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blending"),
			new RecipeSerializer<>(BlendingRecipe.CODEC, BlendingRecipe.STREAM_CODEC));

	public static final RecipeType<BlendingRecipe> BLENDING_RECIPE_TYPE = Registry.register(
			BuiltInRegistries.RECIPE_TYPE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blending"),
			new RecipeType<BlendingRecipe>() {
			});

	public static final MenuType<BlenderMenu> BLENDING_MENU_TYPE = Registry.register(
			BuiltInRegistries.MENU,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blending"),
			new MenuType<>(BlenderMenu::new, FeatureFlags.VANILLA_SET));

	public static void RegisterModRecipes() {

	}
}
