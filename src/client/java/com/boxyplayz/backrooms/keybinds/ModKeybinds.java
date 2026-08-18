package com.boxyplayz.backrooms.keybinds;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClientConfig;
import com.boxyplayz.backrooms.networking.DashPayload;
import com.boxyplayz.backrooms.tags.ModTags;
import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
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
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "custom_hud"), ModKeybinds::extractHudRenderer);

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

	public static void extractHudRenderer(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		int black = 0xFF000000;
		int white = 0xFFFFFFFF;

		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null) {
			LocalPlayer player = minecraft.player;
			if (player != null)
				if (BoxysBackroomsClientConfig.ADD_HINTS.get()) {
					if (player.level().getBiome(player.blockPosition())
							.is(ModTags.DASH_ENABLED)) {
						String dashHint = "Press " + dashKey.getTranslatedKeyMessage().getString() + " to dash!";

						graphics.fill(0, 0, minecraft.font.width(dashHint) + 4,
								minecraft.font.lineHeight + 4, white);

						graphics.text(minecraft.font, dashHint, 2, 2, black, false);
					}
					if (player.level().getBiome(player.blockPosition())
							.is(ModTags.LARGE_JUMP)) {
						String dashHint = "The gravity is lighter here.";

						graphics.fill(0, 0, minecraft.font.width(dashHint) + 4,
								minecraft.font.lineHeight + 4, white);

						graphics.text(minecraft.font, dashHint, 2, 2, black, false);
					}
				}
		}
	}

}
