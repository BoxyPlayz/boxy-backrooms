package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.client.entity.balloon.BalloonEntityRenderer;
import com.boxyplayz.backrooms.client.entity.balloon.BalloonModel;
import com.boxyplayz.backrooms.client.entity.neighborhood_watch.NeighborhoodWatchModel;
import com.boxyplayz.backrooms.client.entity.neighborhood_watch.NeighborhoodWatchRenderer;
import com.boxyplayz.backrooms.client.entity.partygoer.PartygoerEntityRenderer;
import com.boxyplayz.backrooms.client.entity.partygoer.PartygoerModel;
import com.boxyplayz.backrooms.client.entity.partypooper.PartyPooperEntityRenderer;
import com.boxyplayz.backrooms.client.entity.partypooper.PartyPooperModel;
import com.boxyplayz.backrooms.client.entity.skinstealer.SkinStealerEntityRenderer;
import com.boxyplayz.backrooms.client.entity.skinstealer.SkinStealerModel;
import com.boxyplayz.backrooms.client.entity.smiler.SmilerEntityRenderer;
import com.boxyplayz.backrooms.client.entity.smiler.SmilerModel;
import com.boxyplayz.backrooms.client.entity.wretch.WretchModel;
import com.boxyplayz.backrooms.client.entity.wretch.WretchedRenderer;
import com.boxyplayz.backrooms.client.events.GrassColors;
import com.boxyplayz.backrooms.client.events.ItemEvents;
import com.boxyplayz.backrooms.client.screens.BlendingScreen;
import com.boxyplayz.backrooms.client.screens.ElevatorScreen;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.menu.MenuTypes;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.config.ModConfig;
import net.fabricmc.api.EnvType;

/**
 * Begin Client.
 */
@Environment(EnvType.CLIENT)
public class BoxysBackroomsClient implements ClientModInitializer {
	public static final ModelLayerLocation MODEL_SMILER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "smiler"), "main");

	public static final ModelLayerLocation MODEL_WRETCH_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "wretch"), "main");

	public static final ModelLayerLocation MODEL_SKIN_STEALER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "skinstealer"), "main");

	public static final ModelLayerLocation MODEL_PARTYGOER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "partygoer"), "main");

	public static final ModelLayerLocation MODEL_BALLOON_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "balloon"), "main");

	public static final ModelLayerLocation MODEL_PARTYPOOPER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "partypooper"), "main");

	public static final ModelLayerLocation MODEL_NEIGHBORHOOD_WATCH_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "neighborhood_watch"), "main");

	@Override
	public void onInitializeClient() {
		ConfigRegistry.INSTANCE.register("boxys_backrooms", ModConfig.Type.CLIENT, BoxysBackroomsClientConfig.SPEC);

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

		ModelLayerRegistry.registerModelLayer(MODEL_NEIGHBORHOOD_WATCH_LAYER, NeighborhoodWatchModel::createBodyLayer);

		EntityRenderers.register(ModEntities.NEIGHBORHOOD_WATCH, NeighborhoodWatchRenderer::new);

		MenuScreens.register(MenuTypes.BLENDING_MENU_TYPE, BlendingScreen::new);

		MenuScreens.register(MenuTypes.ELEVATOR_MENU_TYPE, ElevatorScreen::new);

		ModKeybinds.init();
	}
}