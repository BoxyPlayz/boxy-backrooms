package com.boxyplayz.backrooms.item;

import java.util.function.Function;

import org.jspecify.annotations.NonNull;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.toolMaterials.ModToolMaterials;

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
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class ModItems {
	private static <T extends Item> T registerItem(@NonNull String name, Function<Item.Properties, T> itemFactory,
			Item.Properties settings) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name);
		ResourceKey<Item> key = ResourceKey.create(
				BuiltInRegistries.ITEM.key(), id);
		T item = itemFactory.apply(settings.setId(key));
		return Registry.register(BuiltInRegistries.ITEM, id, item);
	}

	public static final Item GRAY_ALMOND_WATER = registerItem("gray_almond_water",
			Item::new,
			new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(5).saturationModifier(1f).build(),
							Consumables.defaultDrink()
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.NAUSEA)).build()));

	public static final Item ROYAL_RATION = registerItem("royal_ration",
			Item::new,
			new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(20).saturationModifier(8f).build()));

	public static final Item GREEN_ALMOND_WATER = registerItem("green_almond_water",
			Item::new,
			new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).alwaysEdible().build(),
							Consumables.defaultDrink()
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.SPEED, 10 * 20, 2)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.NAUSEA, 11 * 20, 1)))
									.build()));

	public static final Item RED_ALMOND_WATER = registerItem("red_almond_water",
			Item::new,
			new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).alwaysEdible().build(),
							Consumables.defaultDrink()
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.SLOWNESS, 30 * 20, 1)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.REGENERATION, 25 * 20, 2)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.MINING_FATIGUE, 30 * 20, 1)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.WEAKNESS, 30 * 20, 3)))
									.build()));

	public static final FireSaltItem FIRESALT_SHARD = registerItem("firesalt_shard",
			FireSaltItem::new, new Item.Properties());

	public static final Item SHADOW_DUST = registerItem("shadow_dust",
			Item::new,
			new Item.Properties());

	public static final Item SMILER_REPELLANT = registerItem("smiler_repellant",
			Item::new,
			new Item.Properties());

	public static final Item FIRESTEEL_ALLOY = registerItem("firesteel_alloy",
			Item::new,
			new Item.Properties());

	public static final FireSaltItem FIRESTEEL_SWORD = registerItem("firesteel_sword",
			FireSaltItem::new,
			new Item.Properties().sword(ModToolMaterials.FIRESTEEL_MATERIAL, 3,
					-2));

	public static final Item NEON_WATER = registerItem("neon_water",
			Item::new,
			new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(8).saturationModifier(2f).alwaysEdible().build(),
							Consumables.defaultDrink()
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.DARKNESS))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.BLINDNESS))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.HUNGER))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.INFESTED))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.MINING_FATIGUE))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.NAUSEA))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.POISON))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.SLOWNESS))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.UNLUCK))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.WEAKNESS))
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.WITHER))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.REGENERATION, 240 * 20, 2)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.RESISTANCE, 240 * 20, 1)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.STRENGTH, 240 * 20, 1)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 240 * 20)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.SATURATION, 480 * 20)))
									.build()));

	public static void registerModItems() {
		BoxysBackrooms.LOGGER.debug("Registering items for " + BoxysBackrooms.MOD_ID);
	}
}