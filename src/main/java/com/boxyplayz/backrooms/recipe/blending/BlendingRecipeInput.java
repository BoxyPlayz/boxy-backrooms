package com.boxyplayz.backrooms.recipe.blending;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record BlendingRecipeInput(ItemStack baseItem, ItemStack blendItem) implements RecipeInput {

	@Override
	public ItemStack getItem(int index) {
		return switch (index) {
			case 0 -> baseItem;
			case 1 -> blendItem;
			default -> ItemStack.EMPTY;
		};
	}

	@Override
	public int size() {
		return 2;
	}

}
