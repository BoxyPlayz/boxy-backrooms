package com.boxyplayz.backrooms.client.events;

import com.boxyplayz.backrooms.common.item.ModItems;

import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class Tooltips {
	public static String getTooltip(Item item) {
		return item.getDescriptionId() + ".tooltip";
	}

	public static void Register() {
		ClientTooltipEvent.ITEM.register((itemStack, lines, context, flag) -> {
			if (itemStack.is(ModItems.GRAY_ALMOND_WATER)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.GREEN_ALMOND_WATER)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.RED_ALMOND_WATER)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.FIRESTEEL_SWORD)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.FIRESTEEL_ALLOY)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.GRAY_KEY)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.NEON_WATER)) {
				lines.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
		});
	}
}
