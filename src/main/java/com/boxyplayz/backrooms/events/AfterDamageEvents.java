package com.boxyplayz.backrooms.events;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;

public class AfterDamageEvents {
	public static void RegisterAfterDamageEvents() {
		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
			if (source.is(DamageTypes.IN_WALL)) {
				if (entity.level().dimension() == Level.OVERWORLD) {
					if (entity.getHealth() < 3) {
						if (entity.level().getFluidState(new BlockPos((int) Math.floor(entity.position().x),
								(int) Math.floor(entity.position().y), (int) Math.floor(entity.position().z)))
								.isEmpty()) {
							ServerLevel target = entity.level().getServer().getLevel(ModDimensions.BROKEN_DIMENSION);
							if (target == null)
								return;
							entity.teleportTo(target, 0, 120, 0, Set.of(),
									entity.getYRot(), entity.getXRot(),
									false);
						}
					}
				}
			}
		});
	}
}
