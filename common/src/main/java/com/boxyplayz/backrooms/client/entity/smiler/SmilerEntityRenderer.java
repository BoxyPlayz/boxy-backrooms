package com.boxyplayz.backrooms.client.entity.smiler;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.client.entity.ClientEntityRenderers;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class SmilerEntityRenderer
		extends MobRenderer<SmilerEntity, SmilerRenderState, SmilerModel<SmilerEntity>> {

	public SmilerEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SmilerModel<SmilerEntity>(context.bakeLayer(ClientEntityRenderers.MODEL_SMILER_LAYER)), 0f);
	}

	@Override
	public Identifier getTextureLocation(SmilerRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "textures/entity/smiler.png");
	}

	@Override
	public SmilerRenderState createRenderState() {
		return new SmilerRenderState();
	}
}