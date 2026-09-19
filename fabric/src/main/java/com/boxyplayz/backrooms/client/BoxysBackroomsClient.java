package com.boxyplayz.backrooms.client;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;

/**
 * Begin Client.
 */
public class BoxysBackroomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BoxysBackroomsCommonClient.init();

		ConfigRegistry.INSTANCE.register("boxys_backrooms", ModConfig.Type.CLIENT,
				BoxysBackroomsClientConfig.SPEC);
	}
}