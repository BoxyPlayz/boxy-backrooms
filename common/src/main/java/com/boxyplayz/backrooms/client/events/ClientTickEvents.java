package com.boxyplayz.backrooms.client.events;

import com.boxyplayz.backrooms.client.ModKeybinds;
import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.networking.DashPayload;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.networking.NetworkManager;

public class ClientTickEvents {
	public static void register() {
		ClientTickEvent.CLIENT_POST.register(client -> {
			if (client.player != null) {
				if (client.player.level().getBiome(client.player.blockPosition()).is(ModTags.DASH_ENABLED)) {
					if (ModKeybinds.DASH.consumeClick()) {
						if (client.player.getFoodData().getFoodLevel() >= 8) {
							DashPayload payload = new DashPayload();
							NetworkManager.sendToServer(payload);
						}
					}
				}
			}
		});
	}
}
