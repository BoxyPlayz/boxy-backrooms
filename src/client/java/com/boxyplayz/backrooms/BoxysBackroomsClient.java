package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.skinstealer.SkinStealerEntityRenderer;
import com.boxyplayz.backrooms.entity.skinstealer.SkinStealerModel;
import com.boxyplayz.backrooms.entity.smiler.SmilerEntityRenderer;
import com.boxyplayz.backrooms.entity.smiler.SmilerModel;
import com.boxyplayz.backrooms.entity.wretch.WretchModel;
import com.boxyplayz.backrooms.entity.wretch.WretchedRenderer;
import com.boxyplayz.backrooms.events.GrassColors;
import com.boxyplayz.backrooms.events.ItemEvents;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class BoxysBackroomsClient implements ClientModInitializer {
	public static final ModelLayerLocation MODEL_SMILER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"), "main");

	public static final ModelLayerLocation MODEL_WRETCH_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretch"), "main");

	public static final ModelLayerLocation MODEL_SKIN_STEALER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "skinstealer"), "main");

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
	}
}