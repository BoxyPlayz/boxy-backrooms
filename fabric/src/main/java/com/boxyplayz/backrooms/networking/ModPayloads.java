package com.boxyplayz.backrooms.networking;

import com.boxyplayz.backrooms.common.networking.DashPayload;
import com.boxyplayz.backrooms.common.networking.ElevatorPayload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {
	public static void RegsiterModPayloads() {
		PayloadTypeRegistry.serverboundPlay().register(ElevatorPayload.TYPE, ElevatorPayload.CODEC);
		PayloadTypeRegistry.serverboundPlay().register(DashPayload.TYPE, DashPayload.CODEC);
	}
}
