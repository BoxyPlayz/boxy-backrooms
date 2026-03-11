package com.boxyplayz.backrooms.entity;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class SmilerEntityRenderer
		extends MobRenderer<SmilerEntity, SmilerRenderState, SmilerModel<SmilerEntity>> {

	public SmilerEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SmilerModel<SmilerEntity>(context.bakeLayer(BoxysBackroomsClient.MODEL_SMILER_LAYER)), 0f);
	}

	@Override
	public Identifier getTextureLocation(SmilerRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/smiler.png");
	}

	@Override
	public SmilerRenderState createRenderState() {
		return new SmilerRenderState();
	}
}