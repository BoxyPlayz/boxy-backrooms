package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class ItemEvents {
	public static String getTooltip(Item item) {
		return item.getDescriptionId() + ".tooltip";
	}

	public static void RegisterItemEvents() {
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
			if (itemStack.is(ModItems.GRAY_ALMOND_WATER)) {
				list.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.GREEN_ALMOND_WATER)) {
				list.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.RED_ALMOND_WATER)) {
				list.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.FIRESTEEL_SWORD)) {
				list.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
			if (itemStack.is(ModItems.FIRESTEEL_ALLOY)) {
				list.add(Component.translatable(getTooltip(itemStack.getItem())));
			}
		});
	}
}
