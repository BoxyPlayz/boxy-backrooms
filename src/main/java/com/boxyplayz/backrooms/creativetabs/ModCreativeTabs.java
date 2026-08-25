package com.boxyplayz.backrooms.creativetabs;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
	public static final ResourceKey<CreativeModeTab> BACKROOMS_ITEM_GROUP_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "item_group"));
	public static final CreativeModeTab BACKROOMS_ITEM_GROUP = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(ModItems.GRAY_ALMOND_WATER))
			.title(Component.translatable("itemGroup.boxys_backrooms"))
			.build();

	public static void RegisterModCreativeTabs() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BACKROOMS_ITEM_GROUP_KEY, BACKROOMS_ITEM_GROUP);

		CreativeModeTabEvents.modifyOutputEvent(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY).register(itemGroup -> {
			// Level 0

			itemGroup.accept(ModBlocks.LEVEL0_WALLPAPER);
			itemGroup.accept(ModBlocks.LEVEL0_CARPET);
			itemGroup.accept(ModBlocks.LEVEL0_CEILING_TILE);
			itemGroup.accept(ModBlocks.LEVEL0_CEILING_LIGHT);

			// Level 1

			itemGroup.accept(ModBlocks.LEVEL1_CEILING_AQUILA);
			itemGroup.accept(ModBlocks.LEVEL1_FLOOR_AQUILA);
			itemGroup.accept(ModBlocks.LEVEL1_PILLAR_AQUILA);
			itemGroup.accept(ModBlocks.LEVEL1_CRATE);
			itemGroup.accept(ModBlocks.GOTHIC_CONCRETE);
			itemGroup.accept(ModBlocks.PURE_GRASS);
			itemGroup.accept(ModBlocks.LEVEL0_CARPET_GLITCHED);
			itemGroup.accept(ModBlocks.LEVEL1_CEILING_LIGHT);

			itemGroup.accept(ModBlocks.ERRORSLATE);
			itemGroup.accept(ModBlocks.OCEAN_TRANSPORTER);

			itemGroup.accept(ModBlocks.AGED_CONCRETE);
			itemGroup.accept(ModBlocks.BLACK_TRAMPOLINE);
			itemGroup.accept(ModBlocks.FUN_FLOOR);
			itemGroup.accept(ModBlocks.FUN_GREEN);
			itemGroup.accept(ModBlocks.FUN_PINK);
			itemGroup.accept(ModBlocks.FUN_PURPLE);
			itemGroup.accept(ModBlocks.FUN_YELLOW);
			itemGroup.accept(ModBlocks.INFERIOR_CARPET);
			itemGroup.accept(ModBlocks.INFERIOR_CEILING_TILE);
			itemGroup.accept(ModBlocks.INFERIOR_WALLPAPER);

			itemGroup.accept(ModBlocks.PREMIUM_CARPET);
			itemGroup.accept(ModBlocks.PREMIUM_CEILING_TILE);
			itemGroup.accept(ModBlocks.PREMIUM_WALLPAPER);

			itemGroup.accept(ModBlocks.PROMISED_CARPET);
			itemGroup.accept(ModBlocks.PROMISED_CEILING_LIGHT);
			itemGroup.accept(ModBlocks.PROMISED_CEILING_TILE);
			itemGroup.accept(ModBlocks.PROMISED_GATE);
			itemGroup.accept(ModBlocks.PROMISED_WALLPAPER);

			itemGroup.accept(ModBlocks.POWER_OUTLET_BLOCK);
			itemGroup.accept(ModBlocks.LEVEL3_CEILING_LIGHT);

			itemGroup.accept(ModItems.GRAY_ALMOND_WATER);
			itemGroup.accept(ModItems.GREEN_ALMOND_WATER);
			itemGroup.accept(ModItems.NEON_WATER);
			itemGroup.accept(ModItems.RED_ALMOND_WATER);
			itemGroup.accept(ModItems.ROYAL_RATION);
			itemGroup.accept(ModItems.FIRESALT_SHARD);
			itemGroup.accept(ModItems.SHADOW_DUST);
			itemGroup.accept(ModItems.EMPTY_ALMOND_WATER);
			itemGroup.accept(ModItems.SMILER_REPELLANT);
			itemGroup.accept(ModItems.FIRESTEEL_ALLOY);
			itemGroup.accept(ModItems.LIQUID_PAIN);
			itemGroup.accept(ModItems.FIRESTEEL_SWORD);

			itemGroup.accept(ModItems.SMILER_SPAWN_EGG);
			itemGroup.accept(ModItems.WRETCH_SPAWN_EGG);
			itemGroup.accept(ModItems.SKINSTEALER_SPAWN_EGG);
			itemGroup.accept(ModItems.PARTYGOER_SPAWN_EGG);
			itemGroup.accept(ModItems.PARTYPOOPER_SPAWN_EGG);
			itemGroup.accept(ModItems.NEIGHBORHOOD_WATCH_SPAWN_EGG);

			itemGroup.accept(ModBlocks.BLENDER);
			itemGroup.accept(ModBlocks.WATER_FOUNTAIN);

			itemGroup.accept(ModBlocks.LEVEL2_PIPE);
			itemGroup.accept(ModBlocks.LEVEL2_FIRE_EXIT);
			itemGroup.accept(ModBlocks.LEVEL2_DOOR);

			itemGroup.accept(ModBlocks.ELEVATOR);
			itemGroup.accept(ModBlocks.LEVEL4_CARPET);
			itemGroup.accept(ModBlocks.LEVEL5_ENTRY_TABLE);

			itemGroup.accept(ModBlocks.LEVEL5_CARPET);
			itemGroup.accept(ModBlocks.LEVEL6_ENTRY);

			itemGroup.accept(ModItems.GRAY_KEY);

			itemGroup.accept(ModBlocks.LEVEL11_CONCRETE);

			itemGroup.accept(ModBlocks.FALSE_WHEAT);
		});
	}
}
