package com.boxyplayz.backrooms.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {
	public static void RegsiterModPayloads() {
		PayloadTypeRegistry.serverboundPlay().register(ElevatorPayload.TYPE, ElevatorPayload.CODEC);
		PayloadTypeRegistry.serverboundPlay().register(DashPayload.TYPE, DashPayload.CODEC);
	}
}
