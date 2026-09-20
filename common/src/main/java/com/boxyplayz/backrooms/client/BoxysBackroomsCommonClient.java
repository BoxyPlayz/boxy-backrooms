package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.client.entity.ClientEntityRenderers;
import com.boxyplayz.backrooms.client.events.BlockColors;
import com.boxyplayz.backrooms.client.events.Tooltips;
import com.boxyplayz.backrooms.client.screens.BlendingScreen;
import com.boxyplayz.backrooms.client.screens.ElevatorScreen;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.gui.ModHudRenderer;
import com.boxyplayz.backrooms.common.menu.MenuTypes;

import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.registry.client.gui.MenuScreenRegistry;

public final class BoxysBackroomsCommonClient {
	public static void init() {
		MenuScreenRegistry.registerScreenFactory(MenuTypes.BLENDING_MENU_TYPE.get(), BlendingScreen::new);
		MenuScreenRegistry.registerScreenFactory(MenuTypes.ELEVATOR_MENU_TYPE.get(), ElevatorScreen::new);
		BlockColors.Register();
		Tooltips.Register();
		BoxysBackroomsCommon.LOGGER.info("Start register renders");
		ClientEntityRenderers.RegisterRenderers();
		BoxysBackroomsCommon.LOGGER.info("done register renders");
		NetManClient.register();
		ClientGuiEvent.RENDER_HUD.register(ModHudRenderer::render);
	}
}
