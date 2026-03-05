package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.dimension.ModDimensions;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;

public class Level7Events {
	public static void RegisterLevel7Events() {
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				if (player.level().dimension() == ModDimensions.LEVEL7_DIMENSION) {
					if (player.getAirSupply() < player.getMaxAirSupply()) {
						player.setAirSupply(player.getMaxAirSupply());
					}
				}
			}
		});
	}
}
