package com.boxyplayz.backrooms.common.recipe;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.recipe.blending.BlendingRecipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
			BoxysBackroomsCommon.MOD_ID,
			Registries.RECIPE_SERIALIZER);

	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(
			BoxysBackroomsCommon.MOD_ID,
			Registries.RECIPE_TYPE);

	public static final RegistrySupplier<RecipeSerializer<BlendingRecipe>> BLENDING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS
			.register(
					"blending",
					() -> new RecipeSerializer<>(BlendingRecipe.CODEC, BlendingRecipe.STREAM_CODEC));

	public static final RegistrySupplier<RecipeType<BlendingRecipe>> BLENDING_RECIPE_TYPE = RECIPE_TYPES.register(
			"blending",
			() -> new RecipeType<BlendingRecipe>() {
			});

	public static void RegisterModRecipes() {
		RECIPE_SERIALIZERS.register();
		RECIPE_TYPES.register();
	}
}
