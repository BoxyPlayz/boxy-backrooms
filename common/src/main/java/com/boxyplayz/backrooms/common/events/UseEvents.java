package com.boxyplayz.backrooms.common.events;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.item.ModItems;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UseEvents {
	public static void RegisterUseEvents() {
		InteractionEvent.INTERACT_ENTITY.register(
				(Player player, Entity entity, InteractionHand hand) -> {
					if (!player.level().isClientSide()) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
								|| player.getItemBySlot(EquipmentSlot.OFFHAND)
										.is(ModItems.FIRESALT_SHARD.asItem())) {
							entity.setRemainingFireTicks(120);
							return EventResult.interruptTrue();
						}

						if (entity.is(ModEntities.PARTYPOOPER)) {
							ItemStack equipSlot = player.getItemBySlot(EquipmentSlot.MAINHAND);
							if (equipSlot.is(ModTags.ALMOND_WATERS)) {
								if (player.getInventory().getFreeSlot() != -1) {
									equipSlot.shrink(1);
									player.getInventory().add(new ItemStack(ModItems.NEON_WATER));
									return EventResult.interruptTrue();
								}
							}
						}
					}

					return EventResult.pass();
				});
	}
}
