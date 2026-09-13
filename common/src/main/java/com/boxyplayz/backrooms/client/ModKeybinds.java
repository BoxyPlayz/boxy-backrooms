package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.mojang.blaze3d.platform.InputConstants;

import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ModKeybinds {
	public static KeyMapping.Category CATEGORY = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "backrooms_keybinds"));

	public static final KeyMapping DASH = new KeyMapping(
			"key.boxys_backrooms.dash", // The translation key for the key mapping.
			InputConstants.Type.KEYSYM, // The type of the keybinding; KEYSYM for keyboard, MOUSE for mouse.
			InputConstants.KEY_J, // The keycode of the key.
			CATEGORY // The category of the mapping.
	);

	public static void register() {
		KeyMappingRegistry.register(DASH);
	}
}
