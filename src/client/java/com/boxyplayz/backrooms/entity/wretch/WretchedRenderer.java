package com.boxyplayz.backrooms.entity.wretch;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.Wretch.WretchEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class WretchedRenderer extends MobRenderer<WretchEntity, WretchRenderState, WretchModel<WretchEntity>> {

	public WretchedRenderer(Context context) {
		super(context, new WretchModel<WretchEntity>(context.bakeLayer(BoxysBackroomsClient.MODEL_WRETCH_LAYER)), 1f);
	}

	@Override
	public Identifier getTextureLocation(WretchRenderState state) {
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/wretch.png");
	}

	@Override
	public WretchRenderState createRenderState() {
		return new WretchRenderState();
	}

}
