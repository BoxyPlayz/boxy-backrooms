package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.gui.ModHudRenderer;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.config.ModConfig;

/**
 * Begin Client.
 */
public class BoxysBackroomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BoxysBackroomsCommonClient.init();

		ConfigRegistry.INSTANCE.register("boxys_backrooms", ModConfig.Type.CLIENT, BoxysBackroomsClientConfig.SPEC);

		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "custom_hud"),
				ModHudRenderer::render);
	}
}