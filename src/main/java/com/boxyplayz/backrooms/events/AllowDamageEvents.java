package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
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
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
			if (entity.level().dimension() == ModDimensions.BLUE_CHANNEL_DIMENSION) {
				if (source.is(ModTags.FIRE_ATTACKS)) {
					return false;
				}
			}
			if (entity instanceof SmilerEntity) {
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
						if (!player.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantments().isEmpty()) {
							ItemEnchantments enchantments = player.getItemBySlot(EquipmentSlot.MAINHAND)
									.getEnchantments();
							Reference<Enchantment> fireAspect = entity.level().registryAccess()
									.lookupOrThrow(Registries.ENCHANTMENT)
									.getOrThrow(Enchantments.FIRE_ASPECT);
							if (enchantments.getLevel(fireAspect) > 0) {
								return true;
							}
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
