package com.boxyplayz.backrooms.screen;

import com.boxyplayz.backrooms.menu.ElevatorMenu;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ElevatorScreen extends AbstractContainerScreen<ElevatorMenu> {

	public ElevatorScreen(ElevatorMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

}
