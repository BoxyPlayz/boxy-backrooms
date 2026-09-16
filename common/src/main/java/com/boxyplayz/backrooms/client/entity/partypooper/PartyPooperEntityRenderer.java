package com.boxyplayz.backrooms.client.entity.partypooper;

import com.boxyplayz.backrooms.client.entity.ClientEntityRenderers;
import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PartyPooperEntityRenderer
		extends MobRenderer<PartypooperEntity, PartyPooperRenderState, PartyPooperModel<PartypooperEntity>> {

	public PartyPooperEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new PartyPooperModel<PartypooperEntity>(
						context.bakeLayer(ClientEntityRenderers.MODEL_PARTYPOOPER_LAYER)),
				1f);
	}

	@Override
	public Identifier getTextureLocation(PartyPooperRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "textures/entity/partypooper.png");
	}

	@Override
	public PartyPooperRenderState createRenderState() {
		return new PartyPooperRenderState();
	}

}
