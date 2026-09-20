package com.boxyplayz.backrooms.common.item;

import java.util.function.Function;

import org.jspecify.annotations.NonNull;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.ModCreativeTabs;
import com.boxyplayz.backrooms.common.ModToolMaterials;
import com.boxyplayz.backrooms.common.effect.ModEffects;
import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.item.custom.FireSaltItem;
import com.boxyplayz.backrooms.common.item.custom.GrayKeyItem;
import com.boxyplayz.backrooms.common.item.custom.LiquidPainItem;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class ModItems {

	private static <T extends Item> RegistrySupplier<T> registerItem(@NonNull String name,
			Function<Item.Properties, T> itemFactory, Function<ResourceKey<Item>, Item.Properties> settingsFactory) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, name);

		ResourceKey<Item> key = ResourceKey.create(
				BuiltInRegistries.ITEM.key(), id);

		return BoxysBackroomsCommon.ITEMS.register(id,
				() -> itemFactory.apply(
						settingsFactory.apply(key).setId(key).arch$tab(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY)));
	}

	public static final RegistrySupplier<Item> EMPTY_ALMOND_WATER = registerItem(
			"empty_almond_water",
			Item::new,
			key -> new Item.Properties());

	public static final RegistrySupplier<Item> GRAY_ALMOND_WATER = registerItem(
			"gray_almond_water",
			Item::new,
			key -> new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(5).saturationModifier(1f).build(),
							Consumables.defaultDrink()
									.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.NAUSEA))
									.onConsume(new RemoveStatusEffectsConsumeEffect(ModEffects.WRETCHED_CYCLE))
									.build())
					.usingConvertsTo(EMPTY_ALMOND_WATER.get()));

	public static final RegistrySupplier<Item> ROYAL_RATION = registerItem(
			"royal_ration",
			Item::new,
			key -> new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(20).saturationModifier(8f).build()));

	public static final RegistrySupplier<Item> GREEN_ALMOND_WATER = registerItem(
			"green_almond_water",
			Item::new,
			key -> new Item.Properties()
					.food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).alwaysEdible().build(),
							Consumables.defaultDrink()
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.SPEED, 10 * 20, 2)))
									.onConsume(new ApplyStatusEffectsConsumeEffect(
											new MobEffectInstance(MobEffects.NAUSEA, 11 * 20, 1)))
									.onConsume(new RemoveStatusEffectsConsumeEffect(ModEffects.WRETCHED_CYCLE))
									.build())
					.usingConvertsTo(EMPTY_ALMOND_WATER.get()));

	public static final RegistrySupplier<Item> RED_ALMOND_WATER = registerItem(
			"red_almond_water",
			Item::new,
			key -> new Item.Properties()
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
									.onConsume(new RemoveStatusEffectsConsumeEffect(ModEffects.WRETCHED_CYCLE))
									.build())
					.usingConvertsTo(EMPTY_ALMOND_WATER.get()));

	public static final RegistrySupplier<FireSaltItem> FIRESALT_SHARD = registerItem(
			"firesalt_shard",
			FireSaltItem::new,
			key -> new Item.Properties());

	public static final RegistrySupplier<Item> SHADOW_DUST = registerItem(
			"shadow_dust",
			Item::new,
			key -> new Item.Properties());

	public static final RegistrySupplier<Item> SMILER_REPELLANT = registerItem(
			"smiler_repellant",
			Item::new,
			key -> new Item.Properties());

	public static final RegistrySupplier<Item> FIRESTEEL_ALLOY = registerItem(
			"firesteel_alloy",
			Item::new,
			key -> new Item.Properties());

	public static final RegistrySupplier<FireSaltItem> FIRESTEEL_SWORD = registerItem(
			"firesteel_sword",
			FireSaltItem::new,
			key -> new Item.Properties().sword(ModToolMaterials.FIRESTEEL_MATERIAL, 3,
					-2));

	public static final RegistrySupplier<Item> NEON_WATER = registerItem(
			"neon_water",
			Item::new,
			key -> new Item.Properties()
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
									.onConsume(new RemoveStatusEffectsConsumeEffect(ModEffects.WRETCHED_CYCLE))
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

	public static final RegistrySupplier<SpawnEggItem> SMILER_SPAWN_EGG = registerItem(
			"smiler_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.SMILER.get()));

	public static final RegistrySupplier<SpawnEggItem> WRETCH_SPAWN_EGG = registerItem(
			"wretch_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.WRETCH.get()));

	public static final RegistrySupplier<SpawnEggItem> SKINSTEALER_SPAWN_EGG = registerItem(
			"skinstealer_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.SKINSTEALER.get()));

	public static final RegistrySupplier<SpawnEggItem> PARTYGOER_SPAWN_EGG = registerItem(
			"partygoer_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.PARTYGOER.get()));

	public static final RegistrySupplier<SpawnEggItem> PARTYPOOPER_SPAWN_EGG = registerItem(
			"partypooper_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.PARTYPOOPER.get()));

	public static final RegistrySupplier<SpawnEggItem> NEIGHBORHOOD_WATCH_SPAWN_EGG = registerItem(
			"neighborhood_watch_spawn_egg",
			SpawnEggItem::new,
			key -> new SpawnEggItem.Properties().spawnEgg(ModEntities.NEIGHBORHOOD_WATCH.get()));

	public static final RegistrySupplier<Item> LIQUID_PAIN = registerItem(
			"liquid_pain",
			LiquidPainItem::new,
			key -> new LiquidPainItem.Properties().useCooldown(0.6f));

	public static final RegistrySupplier<Item> GRAY_KEY = registerItem(
			"gray_key",
			GrayKeyItem::new,
			key -> new Item.Properties().stacksTo(1));

	public static void registerModItems() {
		BoxysBackroomsCommon.ITEMS.register();
	}
}