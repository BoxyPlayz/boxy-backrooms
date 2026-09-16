package com.boxyplayz.backrooms.client.entity;

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
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.entity.ModEntities;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;

public class ClientEntityRenderers {
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

	public static void RegisterRenderers() {
		EntityRendererRegistry.register(() -> ModEntities.PARTYPOOPER, PartyPooperEntityRenderer::new);
		EntityModelLayerRegistry.register(MODEL_PARTYPOOPER_LAYER, PartyPooperModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.BALLOON, BalloonEntityRenderer::new);
		EntityModelLayerRegistry.register(MODEL_BALLOON_LAYER, BalloonModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.NEIGHBORHOOD_WATCH, NeighborhoodWatchRenderer::new);
		EntityModelLayerRegistry.register(MODEL_NEIGHBORHOOD_WATCH_LAYER, NeighborhoodWatchModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.SMILER, SmilerEntityRenderer::new);
		EntityModelLayerRegistry.register(MODEL_SMILER_LAYER, SmilerModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.WRETCH, WretchedRenderer::new);
		EntityModelLayerRegistry.register(MODEL_WRETCH_LAYER, WretchModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.SKINSTEALER, SkinStealerEntityRenderer::new);
		EntityModelLayerRegistry.register(MODEL_SKIN_STEALER_LAYER, SkinStealerModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.PARTYGOER, PartygoerEntityRenderer::new);
		EntityModelLayerRegistry.register(MODEL_PARTYGOER_LAYER, PartygoerModel::createBodyLayer);

		EntityRendererRegistry.register(() -> ModEntities.LIQUID_PAIN_PROJECTILE, ThrownItemRenderer::new);
	}
}
