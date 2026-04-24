package com.boxyplayz.backrooms.entity.partypooper;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.Partypooper.PartypooperEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PartyPooperEntityRenderer
		extends MobRenderer<PartypooperEntity, PartyPooperRenderState, PartyPooperModel<PartypooperEntity>> {

	public PartyPooperEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new PartyPooperModel<PartypooperEntity>(
						context.bakeLayer(BoxysBackroomsClient.MODEL_PARTYPOOPER_LAYER)),
				1f);
	}

	@Override
	public Identifier getTextureLocation(PartyPooperRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/partypooper.png");
	}

	@Override
	public PartyPooperRenderState createRenderState() {
		return new PartyPooperRenderState();
	}

}
