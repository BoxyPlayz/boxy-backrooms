package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.common.networking.SetShadyGrayPayload;

import dev.architectury.networking.NetworkManager;

public class NetManClient {
	public static void register() {
		NetworkManager.registerReceiver(NetworkManager.Side.S2C, SetShadyGrayPayload.TYPE, SetShadyGrayPayload.CODEC,
				(payload, context) -> {
					ClientVariables.setShadyGray(payload.enabled());
				});
	}
}
