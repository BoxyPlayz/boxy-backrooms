package com.boxyplayz.backrooms.events;

import java.util.Set;

import com.boxyplayz.backrooms.networking.ElevatorPayload;
import com.boxyplayz.backrooms.utils.Misc.ElevatorDestination;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

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
	}
}
