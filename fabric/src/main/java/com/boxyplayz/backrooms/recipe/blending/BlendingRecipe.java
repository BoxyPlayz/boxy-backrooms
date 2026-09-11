package com.boxyplayz.backrooms.recipe.blending;

import com.boxyplayz.backrooms.recipe.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class BlendingRecipe implements Recipe<BlendingRecipeInput> {
	private final ItemStackTemplate result;
	private final Ingredient baseItem;
	private final Ingredient blendItem;

	public BlendingRecipe(ItemStackTemplate result, Ingredient baseItem, Ingredient blendItem) {
		this.baseItem = baseItem;
		this.blendItem = blendItem;
		this.result = result;
	}

	public ItemStackTemplate getResult() {
		return result;
	}

	public Ingredient getBaseItem() {
		return baseItem;
	}

	public Ingredient getBlendItem() {
		return blendItem;
	}

	@Override
	public boolean matches(BlendingRecipeInput input, Level level) {
		return baseItem.test(input.baseItem()) && blendItem.test(input.blendItem());
	}

	@Override
	public ItemStack assemble(BlendingRecipeInput input) {
		return result.create().copy();
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "blending";
	}

	public static final MapCodec<BlendingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(BlendingRecipe::getResult),
			Ingredient.CODEC.fieldOf("baseItem").forGetter(BlendingRecipe::getBaseItem),
			Ingredient.CODEC.fieldOf("blendItem").forGetter(BlendingRecipe::getBlendItem))
			.apply(instance, BlendingRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, BlendingRecipe> STREAM_CODEC = StreamCodec.composite(
			ItemStackTemplate.STREAM_CODEC,
			BlendingRecipe::getResult,
			Ingredient.CONTENTS_STREAM_CODEC,
			BlendingRecipe::getBaseItem,
			Ingredient.CONTENTS_STREAM_CODEC,
			BlendingRecipe::getBlendItem,
			BlendingRecipe::new);

	@Override
	public RecipeSerializer<? extends Recipe<BlendingRecipeInput>> getSerializer() {
		return ModRecipes.BLENDING_RECIPE_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<BlendingRecipeInput>> getType() {
		return ModRecipes.BLENDING_RECIPE_TYPE;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return null;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

}
