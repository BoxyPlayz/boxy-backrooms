package com.boxyplayz.backrooms.events;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class EntityEvents {
	public static void RegisterEntityEvents() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
			if ((entity instanceof SmilerEntity)) {
				if (source.is(DamageTypes.PLAYER_ATTACK)) {
					return false;
				}
			}
			return true;
		});

		AttackEntityCallback.EVENT.register(
				(Player player, Level world, InteractionHand hand, Entity entity, EntityHitResult hitResult) -> {
					if (entity instanceof SmilerEntity) {
						return InteractionResult.FAIL;
					}
					return InteractionResult.PASS;
				});
	}

}
