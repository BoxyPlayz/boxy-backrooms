package com.boxyplayz.backrooms.entity.skinstealer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.PlayerSkin;

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
		if (renderState.cachedSkin != null) {
			return renderState.cachedSkin;
		}
		if (renderState.maskedPlayerUsername != null &&
				!renderState.maskedPlayerUsername.isEmpty() && renderState.maskedPlayerUUID != null) {
			Minecraft client = Minecraft.getInstance();
			SkinManager manager = client.getSkinManager();
			GameProfile profile = new GameProfile(renderState.maskedPlayerUUID, renderState.maskedPlayerUsername);
			PlayerSkin skin = manager.createLookup(profile, false).get();
			Identifier texture = skin.body().texturePath();
			renderState.cachedSkin = texture;
			return texture;
		}
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/skinstealer.png");
	}

	@Override
	public void extractRenderState(SkinStealerEntity entity, SkinStealerRenderState state, float partialTicks) {
		if (entity.getPlayerKilled() != null && !entity.getPlayerKilled().isEmpty()) {
			state.maskedPlayerUsername = entity.getPlayerKilled();
			state.maskedPlayerUUID = entity.getPlayerKilledUUID();
		}
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public SkinStealerRenderState createRenderState() {
		return new SkinStealerRenderState();
	}
}