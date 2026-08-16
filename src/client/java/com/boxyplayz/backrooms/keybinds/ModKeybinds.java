package com.boxyplayz.backrooms.keybinds;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.networking.DashPayload;
import com.boxyplayz.backrooms.tags.ModTags;
import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ModKeybinds {
	public static KeyMapping.Category CATEGORY = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "backrooms_keybinds"));

	public static KeyMapping dashKey = KeyMappingHelper.registerKeyMapping(
			new KeyMapping(
					"key.boxys_backrooms.dash", // The translation key for the key mapping.
					InputConstants.Type.KEYSYM, // The type of the keybinding; KEYSYM for keyboard, MOUSE for mouse.
					InputConstants.KEY_J, // The keycode of the key.
					CATEGORY // The category of the mapping.
			));

	public static void init() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				if (client.player.level().getBiome(client.player.blockPosition()).is(ModTags.DASH_ENABLED)) {
					if (dashKey.consumeClick()) {
						if (client.player.getFoodData().getFoodLevel() >= 8) {
							DashPayload payload = new DashPayload();
							ClientPlayNetworking.send(payload);
						}
					}
				}
			}
		});
	}
}
