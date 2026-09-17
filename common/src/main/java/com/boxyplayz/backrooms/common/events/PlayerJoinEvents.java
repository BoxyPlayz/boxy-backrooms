package com.boxyplayz.backrooms.common.events;

import com.boxyplayz.backrooms.common.networking.SetShadyGrayPayload;
import com.boxyplayz.backrooms.common.savedata.ShadyGrayAllowedData;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkManager;
import net.minecraft.server.MinecraftServer;

public class PlayerJoinEvents {
	public static void Register() {
		PlayerEvent.PLAYER_JOIN.register((player) -> {
			MinecraftServer server = player.level().getServer();
			if (server != null) {
				ShadyGrayAllowedData data = ShadyGrayAllowedData.getSavedShadyPlayerList(server);
				NetworkManager.sendToPlayer(player, new SetShadyGrayPayload(data.uuidInList(player.getUUID())));
			}
		});
	}
}
