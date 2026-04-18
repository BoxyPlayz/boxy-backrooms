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

			itemGroup.accept(ModBlocks.LEVEL0_WALLPAPER.asItem());
			itemGroup.accept(ModBlocks.LEVEL0_CARPET.asItem());
			itemGroup.accept(ModBlocks.LEVEL0_CEILING_TILE.asItem());
			itemGroup.accept(ModBlocks.LEVEL0_CEILING_LIGHT.asItem());

			// Level 1

			itemGroup.accept(ModBlocks.LEVEL1_CEILING_AQUILA.asItem());
			itemGroup.accept(ModBlocks.LEVEL1_FLOOR_AQUILA.asItem());
			itemGroup.accept(ModBlocks.LEVEL1_PILLAR_AQUILA.asItem());
			itemGroup.accept(ModBlocks.LEVEL1_CRATE.asItem());
			itemGroup.accept(ModBlocks.GOTHIC_CONCRETE.asItem());
			itemGroup.accept(ModBlocks.PURE_GRASS.asItem());
			itemGroup.accept(ModBlocks.LEVEL0_CARPET_GLITCHED.asItem());
			itemGroup.accept(ModBlocks.LEVEL1_CEILING_LIGHT.asItem());

			itemGroup.accept(ModBlocks.ERRORSLATE.asItem());
			itemGroup.accept(ModBlocks.OCEAN_TRANSPORTER.asItem());

			itemGroup.accept(ModBlocks.AGED_CONCRETE.asItem());
			itemGroup.accept(ModBlocks.BLACK_TRAMPOLINE.asItem());
			itemGroup.accept(ModBlocks.FUN_FLOOR.asItem());
			itemGroup.accept(ModBlocks.FUN_GREEN.asItem());
			itemGroup.accept(ModBlocks.FUN_PINK.asItem());
			itemGroup.accept(ModBlocks.FUN_PURPLE.asItem());
			itemGroup.accept(ModBlocks.FUN_YELLOW.asItem());
			itemGroup.accept(ModBlocks.INFERIOR_CARPET.asItem());
			itemGroup.accept(ModBlocks.INFERIOR_CEILING_TILE.asItem());
			itemGroup.accept(ModBlocks.INFERIOR_WALLPAPER.asItem());

			itemGroup.accept(ModBlocks.PREMIUM_CARPET.asItem());
			itemGroup.accept(ModBlocks.PREMIUM_CEILING_TILE.asItem());
			itemGroup.accept(ModBlocks.PREMIUM_WALLPAPER.asItem());

			itemGroup.accept(ModItems.GRAY_ALMOND_WATER);
			itemGroup.accept(ModItems.GREEN_ALMOND_WATER);
			itemGroup.accept(ModItems.RED_ALMOND_WATER);
			itemGroup.accept(ModItems.ROYAL_RATION);
			itemGroup.accept(ModItems.FIRESALT_SHARD);
			itemGroup.accept(ModItems.SHADOW_DUST);
			itemGroup.accept(ModItems.SMILER_REPELLANT);
			itemGroup.accept(ModItems.FIRESTEEL_ALLOY);
			itemGroup.accept(ModItems.FIRESTEEL_SWORD);
		});
	}

}
