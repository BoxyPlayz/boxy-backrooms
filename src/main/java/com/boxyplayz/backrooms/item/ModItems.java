package com.boxyplayz.backrooms.item;

import org.jspecify.annotations.NonNull;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
// import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModItems {
	private static Item registerItem(@NonNull String name, Item item) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name);
		return Registry.register(BuiltInRegistries.ITEM, id, item);
	}

	public static Item GRAY_ALMOND_WATER, GREEN_ALMOND_WATER, RED_ALMOND_WATER, ROYAL_RATION, FIRESALT_SHARD,
			SHADOW_DUST, SMILER_REPELLANT;

	public static void registerModItems() {

		BoxysBackrooms.LOGGER.info("Registering items for " + BoxysBackrooms.MOD_ID);

		ResourceKey<Item> grayAlmondWaterKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "gray_almond_water"));
		GRAY_ALMOND_WATER = registerItem("gray_almond_water", new Item(new Item.Properties()
				.food(new FoodProperties.Builder().nutrition(5).saturationModifier(1f).build())
				.setId(grayAlmondWaterKey)));

		ResourceKey<Item> royalRationKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "royal_ration"));
		ROYAL_RATION = registerItem("royal_ration", new Item(new Item.Properties()
				.food(new FoodProperties.Builder().nutrition(20).saturationModifier(8f).build())
				.setId(royalRationKey)));

		ResourceKey<Item> greenAlmondWaterKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "green_almond_water"));
		GREEN_ALMOND_WATER = registerItem("green_almond_water", new Item(new Item.Properties()
				.food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).build(),
						Consumables.defaultFood()
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.SPEED, 10 * 20, 2)))
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.NAUSEA, 11 * 20, 1)))
								.build())
				.setId(greenAlmondWaterKey)));

		ResourceKey<Item> redAlmondWaterKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "red_almond_water"));
		RED_ALMOND_WATER = registerItem("red_almond_water", new Item(new Item.Properties()
				.food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).build(),
						Consumables.defaultFood()
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.SLOWNESS, 30 * 20, 1)))
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.REGENERATION, 25 * 20, 2)))
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.MINING_FATIGUE, 30 * 20, 1)))
								.onConsume(new ApplyStatusEffectsConsumeEffect(
										new MobEffectInstance(MobEffects.WEAKNESS, 30 * 20, 3)))
								.build())
				.setId(redAlmondWaterKey)));

		ResourceKey<Item> fireSaltShardKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "firesalt_shard"));
		FIRESALT_SHARD = registerItem("firesalt_shard",
				new FireSaltShard(new Item.Properties().setId(fireSaltShardKey)));

		ResourceKey<Item> shadowDustKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "shadow_dust"));
		SHADOW_DUST = registerItem("shadow_dust",
				new Item(new Item.Properties().setId(shadowDustKey)));

		ResourceKey<Item> smilerRepellantKey = ResourceKey.create(
				BuiltInRegistries.ITEM.key(),
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler_repellant"));
		SMILER_REPELLANT = registerItem("smiler_repellant",
				new Item(new Item.Properties().setId(smilerRepellantKey)));

		CreativeModeTabEvents.modifyOutputEvent(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY).register(itemGroup -> {
			itemGroup.accept(GRAY_ALMOND_WATER);
			itemGroup.accept(GREEN_ALMOND_WATER);
			itemGroup.accept(RED_ALMOND_WATER);
			itemGroup.accept(ROYAL_RATION);
			itemGroup.accept(FIRESALT_SHARD);
			itemGroup.accept(SHADOW_DUST);
			itemGroup.accept(SMILER_REPELLANT);
		});
	}
}