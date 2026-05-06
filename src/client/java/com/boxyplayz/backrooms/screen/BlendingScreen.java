package com.boxyplayz.backrooms.screen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.menu.BlenderMenu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class BlendingScreen extends AbstractContainerScreen<BlenderMenu> {
	private final Identifier screenTexture = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
			"textures/gui/container/blender.png");

	public BlendingScreen(BlenderMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, this.screenTexture, this.leftPos, this.topPos,
				0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
	}

}
