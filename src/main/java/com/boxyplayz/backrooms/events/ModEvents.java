package com.boxyplayz.backrooms.events;

public class ModEvents {
	public static void RegisterModEvents() {
		EntityTickEvents.RegisterEntityTickEvents();
		UseEvents.RegisterUseEvents();
		AllowDamageEvents.RegisterAllowDamageEvents();
		ServerPlayNetworkingEvents.RegisterServerPlayNetworking();
		AfterDamageEvents.RegisterAfterDamageEvents();
	}
}
