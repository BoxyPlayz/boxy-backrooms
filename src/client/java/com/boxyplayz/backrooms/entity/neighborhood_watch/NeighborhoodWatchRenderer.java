package com.boxyplayz.backrooms.entity.neighborhood_watch;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class NeighborhoodWatchRenderer extends
		MobRenderer<NeighborhoodWatchEntity, NeighborhoodWatchRenderState, NeighborhoodWatchModel<NeighborhoodWatchEntity>> {

	public NeighborhoodWatchRenderer(Context context) {
		super(context, new NeighborhoodWatchModel<NeighborhoodWatchEntity>(
				context.bakeLayer(BoxysBackroomsClient.MODEL_NEIGHBORHOOD_WATCH_LAYER)), 1.6f);
	}

	@Override
	public Identifier getTextureLocation(NeighborhoodWatchRenderState state) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/neighborhood_watch.png");
	}

	@Override
	public NeighborhoodWatchRenderState createRenderState() {
		return new NeighborhoodWatchRenderState();
	}

}
