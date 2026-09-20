package com.boxyplayz.backrooms.common.events;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.item.ModItems;
import com.boxyplayz.backrooms.common.world.ModDimensions;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class AllowDamageEvents {
	public static void RegisterAllowDamageEvents() {
		EntityEvent.LIVING_HURT.register((LivingEntity entity, DamageSource source, float amount) -> {
			if (entity.level().dimension() == ModDimensions.BLUE_CHANNEL.level) {
				if (source.is(ModTags.FIRE_ATTACKS)) {
					return EventResult.interruptFalse();
				}
			}
			if (entity.level().dimension() == ModDimensions.THE_BROKEN.level) {
				if (source.is(DamageTypes.FALL)) {
					return EventResult.interruptFalse();
				}
			}
			if (entity instanceof SmilerEntity) {
				if (source.is(DamageTypes.PLAYER_ATTACK)) {
					if (source.getEntity() instanceof Player player) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESTEEL_SWORD.get())) {
							return EventResult.pass();
						}
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.get().asItem())
								|| player.getItemBySlot(EquipmentSlot.OFFHAND)
										.is(ModItems.FIRESALT_SHARD.get().asItem())) {
							entity.setRemainingFireTicks(120);
						}
						if (!player.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantments().isEmpty()) {
							ItemEnchantments enchantments = player.getItemBySlot(EquipmentSlot.MAINHAND)
									.getEnchantments();
							Reference<Enchantment> fireAspect = entity.level().registryAccess()
									.lookupOrThrow(Registries.ENCHANTMENT)
									.getOrThrow(Enchantments.FIRE_ASPECT);
							if (enchantments.getLevel(fireAspect) > 0) {
								return EventResult.pass();
							}
						}
					}
					return EventResult.interruptFalse();
				}
				if (source.is(DamageTypes.MACE_SMASH)) {
					return EventResult.interruptFalse();
				}
				if (source.is(DamageTypes.SPEAR)) {
					return EventResult.interruptFalse();
				}
			}
			return EventResult.pass();
		});
	}
}
