package com.boxyplayz.backrooms.common.gui;

import com.boxyplayz.backrooms.client.BoxysBackroomsClientConfig;
import com.boxyplayz.backrooms.common.ModTags;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;

public class ModHudRenderer {
	public static void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		int black = 0xFF000000;
		int white = 0xFFFFFFFF;

		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null) {
			LocalPlayer player = minecraft.player;
			if (player != null)
				if (BoxysBackroomsClientConfig.getHintsValue()) {
					if (player.level().getBiome(player.blockPosition())
							.is(ModTags.DASH_ENABLED)) {
						String dashHint = "Press " + /* dashKey.getTranslatedKeyMessage().getString() */ ""
								+ " to dash!";

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
