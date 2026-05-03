package com.boxyplayz.backrooms.entity.skinstealer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.living.SkinStealer.SkinStealerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SkinStealerEntityRenderer
		extends MobRenderer<SkinStealerEntity, SkinStealerRenderState, SkinStealerModel<SkinStealerEntity>> {

	private static final Identifier PASSIVE = Identifier.withDefaultNamespace("textures/entity/player/wide/steve.png");

	private static final Identifier NORMAL = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
			"textures/entity/skinstealer.png");

	public SkinStealerEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new SkinStealerModel<SkinStealerEntity>(
						context.bakeLayer(BoxysBackroomsClient.MODEL_SKIN_STEALER_LAYER)),
				0.5f);
	}

	@Override
	public Identifier getTextureLocation(SkinStealerRenderState renderState) {
		return renderState.isPassive ? PASSIVE : NORMAL;
	}

	@Override
	public SkinStealerRenderState createRenderState() {
		SkinStealerRenderState state = new SkinStealerRenderState();
		state.isPassive = false;
		return state;
	}

	@Override
	public void extractRenderState(SkinStealerEntity entity, SkinStealerRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isPassive = entity.getAttachedOrElse(SkinStealerEntity.SKINSTEALER_PASSIVE_TIMER, 0) > 0;
	}
}