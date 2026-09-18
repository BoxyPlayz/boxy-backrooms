package com.boxyplayz.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.boxyplayz.backrooms.common.Misc;
import com.boxyplayz.backrooms.common.savedata.PlayerBackroomsEntryLocations;
import com.boxyplayz.backrooms.common.world.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.portal.TeleportTransition.PostTeleportTransition;
import net.minecraft.world.phys.Vec3;

@Mixin(TeleportTransition.class)
public class TeleportTransitionMixin {

	@Inject(method = "missingRespawnBlock", at = @At("HEAD"), cancellable = true)
	private static void backrooms$missingRespawnBlock(
			final ServerPlayer player,
			final PostTeleportTransition postTeleportTransition, CallbackInfoReturnable<TeleportTransition> cir) {
		MinecraftServer server = player.level().getServer();
		PlayerBackroomsEntryLocations locations = PlayerBackroomsEntryLocations
				.getSavedLocations(server);
		if (locations.hasPlayer(player.getUUID())) {
			TeleportTransition transition = new TeleportTransition(server.getLevel(ModDimensions.LEVEL0.level),
					Misc.toVec3(locations.getPosForPlayer(player.getUUID()).orElse(BlockPos.ZERO)), Vec3.ZERO, 0f, 0f,
					TeleportTransition.DO_NOTHING);
			cir.setReturnValue(transition);
			return;
		}
		return;
	}
}
