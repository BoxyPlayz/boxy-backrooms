package com.boxyplayz.backrooms.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
	public static DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BoxysBackroomsCommon.MOD_ID,
			Registries.SOUND_EVENT);

	public static final RegistrySupplier<SoundEvent> WEIRD = SOUNDS.register("weird", () -> SoundEvent
			.createFixedRangeEvent(Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "weird"), 0));

	public static void Register() {
		SOUNDS.register();
	}
}
