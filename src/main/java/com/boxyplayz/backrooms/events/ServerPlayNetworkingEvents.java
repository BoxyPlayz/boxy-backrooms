package com.boxyplayz.backrooms.events;

import java.util.Set;

import com.boxyplayz.backrooms.networking.DashPayload;
import com.boxyplayz.backrooms.networking.ElevatorPayload;
import com.boxyplayz.backrooms.utils.Misc.ElevatorDestination;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ServerPlayNetworkingEvents {
	public static void RegisterServerPlayNetworking() {
		ServerPlayNetworking.registerGlobalReceiver(ElevatorPayload.TYPE, (payload, context) -> {
			Entity entity = context.player().level().getEntity(payload.entityId());

			if (entity instanceof LivingEntity livingEntity) {
				ElevatorDestination destination = ElevatorDestination.valueOf(payload.destination());
				int targetY = Integer.MAX_VALUE;
				ServerLevel target;

				switch (destination) {
					case LEVEL1:
						target = livingEntity.level().getServer().getLevel(ModDimensions.LEVEL1_DIMENSION);
						targetY = 1;
						break;

					case LEVEL2:
						target = livingEntity.level().getServer().getLevel(ModDimensions.LEVEL2_DIMENSION);
						targetY = 1;
						break;

					case LEVEL3:
						target = livingEntity.level().getServer().getLevel(ModDimensions.LEVEL3_DIMENSION);
						targetY = 1;
						break;

					case LEVEL4:
						target = livingEntity.level().getServer().getLevel(ModDimensions.LEVEL4_DIMENSION);
						targetY = 1;
						break;

					default:
						return;
				}

				if (target == null)
					return;

				int x = livingEntity.blockPosition().getX();
				int z = livingEntity.blockPosition().getZ();
				int y = livingEntity.blockPosition().getY();
				if (targetY != Integer.MAX_VALUE) {
					y = targetY;
				}

				BlockPos center = new BlockPos(x, y, z);

				int searchSize = 10;

				searchLoop: for (int dx = -searchSize; dx <= searchSize; dx++) {
					for (int dz = -searchSize; dz <= searchSize; dz++) {
						BlockPos newPos = center.offset(dx, 0, dz);
						if (!target.getBlockState(newPos).isSuffocating(target, newPos)) {
							x = newPos.getX();
							z = newPos.getZ();
							break searchLoop;
						}
					}
				}

				livingEntity.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), livingEntity.getYRot(),
						livingEntity.getXRot(), false);
			}
		});

		ServerPlayNetworking.registerGlobalReceiver(DashPayload.TYPE, (payload, context) -> {
			ServerPlayer player = context.player();

			context.server().execute(() -> {
				if (player.getFoodData().getFoodLevel() >= 8) {
					player.getFoodData().addExhaustion(6);
					Vec3 look = player.getLookAngle();

					Vec3 dash = new Vec3(
							look.x,
							0,
							look.z).normalize().scale(3).add(0, 0.6, 0);

					player.setDeltaMovement(player.getDeltaMovement().add(dash));
					player.hurtMarked = true;
					player.connection.send(new ClientboundSetEntityMotionPacket(player));
				}
			});
		});
	}
}
