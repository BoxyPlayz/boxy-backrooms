package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.balloon.BalloonEntityRenderer;
import com.boxyplayz.backrooms.entity.balloon.BalloonModel;
import com.boxyplayz.backrooms.entity.partygoer.PartygoerEntityRenderer;
import com.boxyplayz.backrooms.entity.partygoer.PartygoerModel;
import com.boxyplayz.backrooms.entity.partypooper.PartyPooperEntityRenderer;
import com.boxyplayz.backrooms.entity.partypooper.PartyPooperModel;
import com.boxyplayz.backrooms.entity.skinstealer.SkinStealerEntityRenderer;
import com.boxyplayz.backrooms.entity.skinstealer.SkinStealerModel;
import com.boxyplayz.backrooms.entity.smiler.SmilerEntityRenderer;
import com.boxyplayz.backrooms.entity.smiler.SmilerModel;
import com.boxyplayz.backrooms.entity.wretch.WretchModel;
import com.boxyplayz.backrooms.entity.wretch.WretchedRenderer;
import com.boxyplayz.backrooms.events.GrassColors;
import com.boxyplayz.backrooms.events.ItemEvents;
import com.boxyplayz.backrooms.menu.MenuTypes;
import com.boxyplayz.backrooms.screen.BlendingScreen;
import com.boxyplayz.backrooms.screen.ElevatorScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.EnvType;

/**
 * Begin Client.
 */
@Environment(EnvType.CLIENT)
public class BoxysBackroomsClient implements ClientModInitializer {
	public static final ModelLayerLocation MODEL_SMILER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"), "main");

	public static final ModelLayerLocation MODEL_WRETCH_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretch"), "main");

	public static final ModelLayerLocation MODEL_SKIN_STEALER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "skinstealer"), "main");

	public static final ModelLayerLocation MODEL_PARTYGOER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "partygoer"), "main");

	public static final ModelLayerLocation MODEL_BALLOON_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "balloon"), "main");

	public static final ModelLayerLocation MODEL_PARTYPOOPER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "partypoopwe"), "main");

	@Override
	public void onInitializeClient() {
		ItemEvents.RegisterItemEvents();

		GrassColors.RegisterGrassColors();

		EntityRenderers.register(ModEntities.SMILER, SmilerEntityRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_SMILER_LAYER, SmilerModel::createBodyLayer);

		EntityRenderers.register(ModEntities.SKINSTEALER, SkinStealerEntityRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_SKIN_STEALER_LAYER, SkinStealerModel::createBodyLayer);

		EntityRenderers.register(ModEntities.WRETCH, WretchedRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_WRETCH_LAYER, WretchModel::createBodyLayer);

		EntityRenderers.register(ModEntities.PARTYGOER, PartygoerEntityRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_PARTYGOER_LAYER, PartygoerModel::createBodyLayer);

		EntityRenderers.register(ModEntities.BALLOON, BalloonEntityRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_BALLOON_LAYER, BalloonModel::createBodyLayer);

		EntityRenderers.register(ModEntities.PARTYPOOPER, PartyPooperEntityRenderer::new);

		ModelLayerRegistry.registerModelLayer(MODEL_PARTYPOOPER_LAYER, PartyPooperModel::createBodyLayer);

		EntityRenderers.register(ModEntities.LIQUID_PAIN_PROJECTILE, ThrownItemRenderer::new);

		MenuScreens.register(MenuTypes.BLENDING_MENU_TYPE, BlendingScreen::new);

		MenuScreens.register(MenuTypes.ELEVATOR_MENU_TYPE, ElevatorScreen::new);
	}
}