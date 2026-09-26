package com.boxyplayz.backrooms.neoforge.client;

import com.boxyplayz.backrooms.client.BoxysBackroomsClientConfig;
import com.boxyplayz.backrooms.client.BoxysBackroomsCommonClient;
import com.boxyplayz.backrooms.client.entity.ClientEntityRenderers;
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

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = BoxysBackroomsCommon.MOD_ID, dist = Dist.CLIENT)
public class BoxysBackroomsClientNeoforge {
	public BoxysBackroomsClientNeoforge(IEventBus bus, ModContainer container) {
		bus.addListener(BoxysBackroomsClientNeoforge::clientSetup);
		bus.addListener(BoxysBackroomsClientNeoforge::registerRenderers);
		bus.addListener(BoxysBackroomsClientNeoforge::registerLayers);

		container.registerConfig(ModConfig.Type.CLIENT,
				BoxysBackroomsClientConfig.SPEC);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		BoxysBackroomsCommonClient.init();
	}

	private static void registerRenderers(
			EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.PARTYPOOPER.get(), PartyPooperEntityRenderer::new);

		event.registerEntityRenderer(ModEntities.BALLOON.get(), BalloonEntityRenderer::new);

		event.registerEntityRenderer(ModEntities.NEIGHBORHOOD_WATCH.get(), NeighborhoodWatchRenderer::new);

		event.registerEntityRenderer(ModEntities.SMILER.get(), SmilerEntityRenderer::new);

		event.registerEntityRenderer(ModEntities.WRETCH.get(), WretchedRenderer::new);

		event.registerEntityRenderer(ModEntities.SKINSTEALER.get(), SkinStealerEntityRenderer::new);

		event.registerEntityRenderer(ModEntities.PARTYGOER.get(), PartygoerEntityRenderer::new);

		event.registerEntityRenderer(ModEntities.LIQUID_PAIN_PROJECTILE.get(), ThrownItemRenderer::new);

	}

	private static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_PARTYGOER_LAYER, PartygoerModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_PARTYPOOPER_LAYER, PartyPooperModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_BALLOON_LAYER, BalloonModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_NEIGHBORHOOD_WATCH_LAYER,
				NeighborhoodWatchModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_SMILER_LAYER, SmilerModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_WRETCH_LAYER, WretchModel::createBodyLayer);
		event.registerLayerDefinition(ClientEntityRenderers.MODEL_SKIN_STEALER_LAYER,
				SkinStealerModel::createBodyLayer);
	}
}
