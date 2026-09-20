package com.boxyplayz.backrooms.client.entity.partygoer;

import com.boxyplayz.backrooms.client.entity.ClientEntityRenderers;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PartygoerEntityRenderer
		extends MobRenderer<PartygoerEntity, PartygoerRenderState, PartygoerModel<PartygoerEntity>> {

	public PartygoerEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new PartygoerModel<PartygoerEntity>(context.bakeLayer(ClientEntityRenderers.MODEL_PARTYGOER_LAYER)),
				1f);
	}

	@Override
	public Identifier getTextureLocation(PartygoerRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "textures/entity/partygoer.png");
	}

	@Override
	public PartygoerRenderState createRenderState() {
		return new PartygoerRenderState();
	}
}