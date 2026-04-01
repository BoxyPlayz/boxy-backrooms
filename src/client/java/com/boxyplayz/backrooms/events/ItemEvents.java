package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;

public class ItemEvents {
	public static void RegisterItemEvents() {
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
			if (itemStack.is(ModItems.GRAY_ALMOND_WATER)) {
				list.add(Component.translatable("item.gray_almond_water.tooltip"));
			}
			if (itemStack.is(ModItems.GREEN_ALMOND_WATER)) {
				list.add(Component.translatable("item.green_almond_water.tooltip"));
			}
			if (itemStack.is(ModItems.RED_ALMOND_WATER)) {
				list.add(Component.translatable("item.red_almond_water.tooltip"));
			}
			if (itemStack.is(ModItems.FIRESTEEL_SWORD)) {
				list.add(Component.translatable("item.firesteel_sword.tooltip"));
			}
			if (itemStack.is(ModItems.FIRESTEEL_ALLOY)) {
				list.add(Component.translatable("item.firesteel_alloy.tooltip"));
			}
		});
	}
}
