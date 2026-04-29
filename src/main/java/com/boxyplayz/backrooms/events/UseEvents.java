package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class UseEvents {
	public static void RegisterUseEvents() {
		UseEntityCallback.EVENT.register(
				(Player player, Level world, InteractionHand hand, Entity entity, EntityHitResult hitResult) -> {
					if (!world.isClientSide()) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
								|| player.getItemBySlot(EquipmentSlot.OFFHAND)
										.is(ModItems.FIRESALT_SHARD.asItem())) {
							entity.setRemainingFireTicks(120);
							return InteractionResult.CONSUME;
						}

						if (entity.is(ModEntities.PARTYPOOPER)) {
							ItemStack equipSlot = player.getItemBySlot(EquipmentSlot.MAINHAND);
							if (equipSlot.is(ModTags.ALMOND_WATERS)) {
								if (player.getInventory().getFreeSlot() != -1) {
									equipSlot.shrink(1);
									player.getInventory().add(new ItemStack(ModItems.NEON_WATER));
									return InteractionResult.CONSUME;
								}
							}
						}
					}

					return InteractionResult.PASS;
				});
	}
}
