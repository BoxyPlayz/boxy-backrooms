package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.SmilerEntityRenderer;
import com.boxyplayz.backrooms.entity.SmilerModel;
import com.boxyplayz.backrooms.events.ItemEvents;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.Identifier;

public class BoxysBackroomsClient implements ClientModInitializer {
	public static final ModelLayerLocation MODEL_SMILER_LAYER = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"), "main");

	@Override
	public void onInitializeClient() {
		ItemEvents.RegisterItemEvents();

		EntityRenderers.register(ModEntities.SMILER, SmilerEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(MODEL_SMILER_LAYER, SmilerModel::createBodyLayer);
	}
}