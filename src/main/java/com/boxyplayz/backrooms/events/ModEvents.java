package com.boxyplayz.backrooms.events;

public class ModEvents {
	public static void RegisterModEvents() {
		Level7Events.RegisterLevel7Events();
		EntityEvents.RegisterEntityEvents();
	}
}
