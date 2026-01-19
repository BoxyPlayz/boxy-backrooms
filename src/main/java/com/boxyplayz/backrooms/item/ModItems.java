package com.boxyplayz.backrooms.item;

import org.jspecify.annotations.NonNull;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModItems {
	private static Item registerItem(@NonNull String name, Item item) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name);
		return Registry.register(BuiltInRegistries.ITEM, id, item);
	}

	public static Item ALMOND_WATER;

	public static void registerModItems() {

		BoxysBackrooms.LOGGER.info("Registering items for " + BoxysBackrooms.MOD_ID);

		ResourceKey<Item> key = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "almond_water"));
		ALMOND_WATER = registerItem("almond_water", new Item(new Item.Properties()
				.food(new FoodProperties.Builder().nutrition(5).saturationModifier(8).build(),
						Consumables.defaultFood()
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 5)))
								.build())
				.setId(key)));

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(itemGroup -> {
			itemGroup.accept(ALMOND_WATER);
		});
	}
}