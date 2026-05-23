package com.boxyplayz.backrooms.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class ElevatorMenu extends AbstractContainerMenu {

	public ElevatorMenu(int i, Inventory inventory) {
		super(MenuTypes.ELEVATOR_MENU_TYPE, i);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

}
