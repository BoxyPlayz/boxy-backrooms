package com.boxyplayz.backrooms.entity.partygoer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.Partygoer.PartygoerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PartygoerEntityRenderer
		extends MobRenderer<PartygoerEntity, PartygoerRenderState, PartygoerModel<PartygoerEntity>> {

	public PartygoerEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new PartygoerModel<PartygoerEntity>(context.bakeLayer(BoxysBackroomsClient.MODEL_PARTYGOER_LAYER)),
				1f);
	}

	@Override
	public Identifier getTextureLocation(PartygoerRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/partygoer.png");
	}

	@Override
	public PartygoerRenderState createRenderState() {
		return new PartygoerRenderState();
	}
}