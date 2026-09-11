package com.boxyplayz.backrooms.datagen.recipe.builders;

import com.boxyplayz.backrooms.recipe.blending.BlendingRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class BlendingRecipeBuilder {
	private final Ingredient base;
	private final Ingredient mixin;
	private final ItemStackTemplate result;
	private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
	private final RecipeCategory category;

	/**
	 * Blender
	 * 
	 * @param base     Base item for mixing
	 * @param mixin    Item to add to blend
	 * @param category Recipe Category
	 * @param result   What happens when mixed
	 */
	public BlendingRecipeBuilder(
			final Ingredient base, final Ingredient mixin, final RecipeCategory category,
			final ItemStackTemplate result) {
		this.category = category;
		this.base = base;
		this.mixin = mixin;
		this.result = result;
	}

	public static BlendingRecipeBuilder blending(
			final Ingredient base, final Ingredient addition, final RecipeCategory category,
			final Item result) {
		return new BlendingRecipeBuilder(base, addition, category, new ItemStackTemplate(result));
	}

	public BlendingRecipeBuilder unlockedBy(final String name, final Criterion<?> criterion) {
		this.advancementBuilder.unlockedBy(name, criterion);
		return this;
	}

	public void save(final RecipeOutput output, final String id) {
		this.save(output, ResourceKey.create(Registries.RECIPE, Identifier.parse(id)));
	}

	public void save(final RecipeOutput output, final ResourceKey<Recipe<?>> id) {
		BlendingRecipe recipe = new BlendingRecipe(
				this.result, this.base, this.mixin);
		output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
	}
}
