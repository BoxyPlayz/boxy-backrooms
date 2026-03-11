package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class EntityEvents {
	public static void RegisterEntityEvents() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
			if ((entity instanceof SmilerEntity)) {
				if (source.is(DamageTypes.PLAYER_ATTACK)) {
					if (source.getEntity() instanceof Player player) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
								|| player.getItemBySlot(EquipmentSlot.OFFHAND)
										.is(ModItems.FIRESALT_SHARD.asItem())) {
							entity.setRemainingFireTicks(120);
						}
					}
					return false;
				}
				if (source.is(DamageTypes.MACE_SMASH)) {
					return false;
				}
				if (source.is(DamageTypes.SPEAR)) {
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

		UseEntityCallback.EVENT.register(
				(Player player, Level world, InteractionHand hand, Entity entity, EntityHitResult hitResult) -> {
					if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
							|| player.getItemBySlot(EquipmentSlot.OFFHAND)
									.is(ModItems.FIRESALT_SHARD.asItem())) {
						entity.setRemainingFireTicks(120);
					}

					return InteractionResult.PASS;
				});
	}

}
