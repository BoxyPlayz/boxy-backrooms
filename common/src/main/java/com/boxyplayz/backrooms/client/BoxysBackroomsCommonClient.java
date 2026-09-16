package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.client.events.BlockColors;
import com.boxyplayz.backrooms.client.events.Tooltips;
import com.boxyplayz.backrooms.client.screens.BlendingScreen;
import com.boxyplayz.backrooms.client.screens.ElevatorScreen;
import com.boxyplayz.backrooms.common.menu.MenuTypes;

import dev.architectury.registry.client.gui.MenuScreenRegistry;

public final class BoxysBackroomsCommonClient {
	public static void init() {
		MenuScreenRegistry.registerScreenFactory(MenuTypes.BLENDING_MENU_TYPE, BlendingScreen::new);
		MenuScreenRegistry.registerScreenFactory(MenuTypes.ELEVATOR_MENU_TYPE, ElevatorScreen::new);
		BlockColors.Register();
		Tooltips.Register();
	}
}
