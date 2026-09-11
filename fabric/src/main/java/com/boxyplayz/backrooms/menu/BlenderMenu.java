package com.boxyplayz.backrooms.menu;

import java.util.List;
import java.util.Optional;

import com.boxyplayz.backrooms.recipe.ModRecipes;
import com.boxyplayz.backrooms.recipe.blending.BlendingRecipe;
import com.boxyplayz.backrooms.recipe.blending.BlendingRecipeInput;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class BlenderMenu extends AbstractContainerMenu {
	private final Container input = new SimpleContainer(2) {
		@Override
		public void setChanged() {
			super.setChanged();
			slotsChanged(this);
		}
	};

	private final ResultContainer output = new ResultContainer();

	private final Level level;

	public BlenderMenu(int i, Inventory inventory) {
		super(MenuTypes.BLENDING_MENU_TYPE, i);

		this.level = inventory.player.level();

		addSlot(new Slot(input, 0, 76, 47));
		addSlot(new Slot(input, 1, 76, 15));

		addSlot(new Slot(output, 0, 134, 47) {
			@Override
			public void onTake(Player player, ItemStack itemStack) {
				BlenderMenu.this.onTake(player, itemStack);
			}
		});

		addStandardInventorySlots(inventory, 8, 84);
	}

	@Override
	public void slotsChanged(Container container) {
		super.slotsChanged(container);

		if (container == input) {
			if (level instanceof ServerLevel serverLevel) {
				BlendingRecipeInput recipeInput = new BlendingRecipeInput(input.getItem(0), input.getItem(1));
				Optional<RecipeHolder<BlendingRecipe>> recipe = serverLevel.recipeAccess()
						.getRecipeFor(ModRecipes.BLENDING_RECIPE_TYPE, recipeInput, serverLevel);

				if (recipe.isPresent()) {
					output.setItem(0, recipe.get().value().assemble(recipeInput));
					output.setRecipeUsed(recipe.get());
				} else {
					output.clearContent();
					output.setRecipeUsed(null);
				}
			}
		}
	}

	public void onTake(Player player, ItemStack stack) {
		stack.onCraftedBy(player, stack.getCount());
		output.awardUsedRecipes(player, List.of(input.getItem(0), input.getItem(1)));

		input.removeItem(0, stack.getCount());
		input.removeItem(1, stack.getCount());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int i) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		clearContainer(player, input);
	}
}