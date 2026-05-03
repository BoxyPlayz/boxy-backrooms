package com.boxyplayz.backrooms.entity.balloon;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.living.Balloon.BalloonEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class BalloonEntityRenderer
		extends MobRenderer<BalloonEntity, BalloonRenderState, BalloonModel<BalloonEntity>> {

	public BalloonEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new BalloonModel<BalloonEntity>(context.bakeLayer(BoxysBackroomsClient.MODEL_BALLOON_LAYER)),
				1f);
	}

	@Override
	public Identifier getTextureLocation(BalloonRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/balloon.png");
	}

	@Override
	public BalloonRenderState createRenderState() {
		return new BalloonRenderState();
	}
}