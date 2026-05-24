package com.boxyplayz.backrooms.screen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.menu.ElevatorMenu;
import com.boxyplayz.backrooms.networking.ElevatorPayload;
import com.boxyplayz.backrooms.utils.Misc;
import com.boxyplayz.backrooms.utils.Misc.ElevatorDestination;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
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
				.builder(Component.translatable(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL1.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL1.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 20, 60, 20).build();
		addRenderableWidget(levelOneButton);

		Button levelTwoButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL2.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL2.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 60, 60, 20).build();
		addRenderableWidget(levelTwoButton);

		Button levelThreeButton = Button
				.builder(Component.translatable(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL3.name())),
						(final Button button) -> {
							ElevatorPayload payload = new ElevatorPayload(ElevatorDestination.LEVEL3.name(),
									Minecraft.getInstance().player.getId());
							ClientPlayNetworking.send(payload);
						})
				.bounds(this.leftPos + 5, this.topPos + 100, 60, 20).build();
		addRenderableWidget(levelThreeButton);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, -12566464, false);
	}

}
