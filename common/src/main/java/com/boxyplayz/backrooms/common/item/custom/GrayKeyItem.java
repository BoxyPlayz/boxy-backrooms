package com.boxyplayz.backrooms.common.item.custom;

import com.boxyplayz.backrooms.common.networking.SetShadyGrayPayload;
import com.boxyplayz.backrooms.common.savedata.ShadyGrayAllowedData;

import dev.architectury.networking.NetworkManager;
// import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class GrayKeyItem extends Item {

	public GrayKeyItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level.isClientSide()) {
			// LocalPlayer clientPlayer = (LocalPlayer) player;
		} else {
			ServerPlayer serverPlayer = (ServerPlayer) player;
			NetworkManager.sendToPlayer(serverPlayer, new SetShadyGrayPayload(true));
			ShadyGrayAllowedData.getSavedShadyPlayerList(serverPlayer.level().getServer())
					.addPlayerToList(serverPlayer.getUUID());
		}
		return super.use(level, player, hand);
	}

}
