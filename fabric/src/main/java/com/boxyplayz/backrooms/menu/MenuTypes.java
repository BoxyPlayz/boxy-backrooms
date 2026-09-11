package com.boxyplayz.backrooms.menu;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class MenuTypes {
	public static final MenuType<BlenderMenu> BLENDING_MENU_TYPE = Registry.register(
			BuiltInRegistries.MENU,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blending"),
			new MenuType<>(BlenderMenu::new, FeatureFlags.VANILLA_SET));

	public static final MenuType<ElevatorMenu> ELEVATOR_MENU_TYPE = Registry.register(
			BuiltInRegistries.MENU,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "elevator"),
			new MenuType<>(ElevatorMenu::new, FeatureFlags.VANILLA_SET));
}
