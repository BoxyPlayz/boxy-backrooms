package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class AllowDamageEvents {
	public static void RegisterAllowDamageEvents() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
			if (entity.level().dimension() == ModDimensions.BLUE_CHANNEL_DIMENSION) {
				if (source.is(ModTags.FIRE_ATTACKS)) {
					return false;
				}
			}
			if ((entity instanceof SmilerEntity)) {
				if (source.is(DamageTypes.PLAYER_ATTACK)) {
					if (source.getEntity() instanceof Player player) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESTEEL_SWORD)) {
							return true;
						}
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
	}
}
