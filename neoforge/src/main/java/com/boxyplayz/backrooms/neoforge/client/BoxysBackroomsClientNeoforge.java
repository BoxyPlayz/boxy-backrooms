package com.boxyplayz.backrooms.neoforge.client;

import com.boxyplayz.backrooms.client.BoxysBackroomsClientConfig;
import com.boxyplayz.backrooms.client.BoxysBackroomsCommonClient;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = BoxysBackroomsCommon.MOD_ID, dist = Dist.CLIENT)
public class BoxysBackroomsClientNeoforge {
	public BoxysBackroomsClientNeoforge(IEventBus bus, ModContainer container) {
		bus.addListener(BoxysBackroomsClientNeoforge::clientSetup);

		container.registerConfig(ModConfig.Type.CLIENT,
				BoxysBackroomsClientConfig.SPEC);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		BoxysBackroomsCommonClient.init();
	}
}
