package com.boxyplayz.backrooms.entity.skinstealer;

import java.util.UUID;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.BoxysBackroomsClient;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerVarient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class SkinStealerEntityRenderer
		extends MobRenderer<SkinStealerEntity, SkinStealerRenderState, SkinStealerModel<SkinStealerEntity>> {

	private final SkinStealerModel<SkinStealerEntity> skinStealerModel;
	private final PlayerModel playerModel;

	public SkinStealerEntityRenderer(EntityRendererProvider.Context context) {
		super(context,
				new SkinStealerModel<SkinStealerEntity>(
						context.bakeLayer(BoxysBackroomsClient.MODEL_SKIN_STEALER_LAYER)),
				0f);
		this.skinStealerModel = new SkinStealerModel<>(
				context.bakeLayer(BoxysBackroomsClient.MODEL_SKIN_STEALER_LAYER));
		this.playerModel = new PlayerModel(context.bakeLayer(BoxysBackroomsClient.MODEL_PERFECT_PLAYER_LAYER), false);
	}

	@Override
	public Identifier getTextureLocation(SkinStealerRenderState renderState) {
		if (renderState.varient == SkinStealerVarient.PASSIVE && renderState.maskedPlayerUsername != null) {
			Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
			if (minecraft.level != null) {
				Player player = minecraft.level.getPlayerByUUID(UUID.fromString(renderState.maskedPlayerUsername));

				if (player instanceof AbstractClientPlayer clientPlayer) {
					return clientPlayer.getSkin().body().texturePath();
				}
			}
		}
		return Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "textures/entity/skin_stealer.png");
	}

	@Override
	public SkinStealerRenderState createRenderState() {
		return new SkinStealerRenderState();
	}
}