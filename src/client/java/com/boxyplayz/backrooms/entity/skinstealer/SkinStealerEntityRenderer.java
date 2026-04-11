package com.boxyplayz.backrooms.entity.skinstealer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class SkinStealerEntityRenderer
		extends MobRenderer<SkinStealerEntity, SkinStealerRenderState, SkinStealerModel<SkinStealerEntity>> {

	public SkinStealerEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new SkinStealerModel<SkinStealerEntity>(
						context.bakeLayer(BoxysBackroomsClient.MODEL_SKIN_STEALER_LAYER)),
				0.5f);
	}

	@Override
	public Identifier getTextureLocation(SkinStealerRenderState renderState) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/skinstealer.png");
	}

	@Override
	public SkinStealerRenderState createRenderState() {
		return new SkinStealerRenderState();
	}
}