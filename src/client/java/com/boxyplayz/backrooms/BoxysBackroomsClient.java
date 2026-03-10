package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.events.ItemEvents;

import net.fabricmc.api.ClientModInitializer;

public class BoxysBackroomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemEvents.RegisterItemEvents();
	}
}