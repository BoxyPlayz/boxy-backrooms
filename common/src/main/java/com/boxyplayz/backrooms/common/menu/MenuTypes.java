package com.boxyplayz.backrooms.common.menu;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class MenuTypes {
	private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BoxysBackroomsCommon.MOD_ID,
			Registries.MENU);

	public static final RegistrySupplier<MenuType<BlenderMenu>> BLENDING_MENU_TYPE = MENU_TYPES.register(
			"blending",
			() -> new MenuType<BlenderMenu>(BlenderMenu::new, FeatureFlags.VANILLA_SET));

	public static final RegistrySupplier<MenuType<ElevatorMenu>> ELEVATOR_MENU_TYPE = MENU_TYPES.register(
			"elevator",
			() -> new MenuType<>(ElevatorMenu::new, FeatureFlags.VANILLA_SET));

	public static void Register() {
		MENU_TYPES.register();
	}
}
