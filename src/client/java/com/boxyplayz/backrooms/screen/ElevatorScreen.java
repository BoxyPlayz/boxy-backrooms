package com.boxyplayz.backrooms.screen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.dataattachments.DataAttachments;
import com.boxyplayz.backrooms.menu.ElevatorMenu;
import com.boxyplayz.backrooms.networking.ElevatorPayload;
import com.boxyplayz.backrooms.utils.Misc;
import com.boxyplayz.backrooms.utils.Misc.ElevatorDestination;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ElevatorScreen extends AbstractContainerScreen<ElevatorMenu> {

	private static final Identifier CONTAINER_TEXTURE = Identifier
			.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/gui/elevator.png");

	public ElevatorScreen(ElevatorMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F,
				this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
	}

	@Override
	protected void init() {
		super.init();
		Button levelOneButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(ElevatorDestination.LEVEL1.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL1.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 20, 60, 20).build();
		addRenderableWidget(levelOneButton);

		Button levelTwoButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(ElevatorDestination.LEVEL2.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL2.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 50, 60, 20).build();
		addRenderableWidget(levelTwoButton);

		Button levelThreeButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(ElevatorDestination.LEVEL3.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL3.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 80, 60, 20).build();
		addRenderableWidget(levelThreeButton);

		Button levelFourButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(ElevatorDestination.LEVEL4.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL4.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 110, 60, 20).build();
		addRenderableWidget(levelFourButton);

		if (this.minecraft != null && this.minecraft.player != null) {
			LocalPlayer player = this.minecraft.player;
			if (player.hasAttached(DataAttachments.ACCESS_GRAY)) {
				if (player.getAttached(DataAttachments.ACCESS_GRAY)) {
					Button shadeGrayButton = Button
							.builder(
									Component.translatable(
											Misc.getElevatorLangId(ElevatorDestination.SHADE_GRAY.name())),
									(final Button button) -> {
										ElevatorPayload payload = new ElevatorPayload(
												ElevatorDestination.SHADE_GRAY.name(),
												Minecraft.getInstance().player.getId());
										ClientPlayNetworking.send(payload);
									})
							.bounds(this.leftPos + 5, this.topPos + 140, 90, 20).build();
					addRenderableWidget(shadeGrayButton);
				}
			}
		}

	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, -12566464, false);
	}

}
