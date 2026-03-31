package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

public class BoxyBackroomsEnglishLangProvider extends FabricLanguageProvider {
	public BoxyBackroomsEnglishLangProvider(FabricPackOutput dataOutput,
			CompletableFuture<HolderLookup.Provider> registryLookup) {
		// Specifying en_us is optional, as it's the default language code
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
		// Metadata
		translationBuilder.add(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY, "Boxy's Backrooms");

		// Food
		translationBuilder.add(ModItems.GRAY_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.GREEN_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.RED_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.ROYAL_RATION, "Royal Ration");

		// Other Items
		translationBuilder.add(ModItems.FIRESALT_SHARD, "Firesalt Shard");
		translationBuilder.add(ModItems.SHADOW_DUST, "Shadow Dust");
		translationBuilder.add(ModItems.SMILER_REPELLANT, "Smiler Repellant");

		// Noclippable Blocks
		translationBuilder.add(ModBlocks.ERRORSLATE, "Errorslate");
		translationBuilder.add(ModBlocks.OCEAN_TRANSPORTER, "Aquatic Transporter");
		translationBuilder.add(ModBlocks.LEVEL0_WALLPAPER, "Wallpaper");
		translationBuilder.add(ModBlocks.LEVEL0_CARPET_GLITCHED, "Carpet?");

		// Regular Blocks
		translationBuilder.add(ModBlocks.PURE_GRASS, "Pure Grass");

		translationBuilder.add(ModBlocks.LEVEL0_CARPET, "Carpet");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_LIGHT, "Old Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_TILE, "Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_CARPET, "Premium Carpet");
		translationBuilder.add(ModBlocks.PREMIUM_CEILING_TILE, "Premium Ceiling Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_WALLPAPER, "Premium Wallpaper");
		translationBuilder.add(ModBlocks.INFERIOR_CARPET, "Worn Carpet");
		translationBuilder.add(ModBlocks.INFERIOR_CEILING_TILE, "Failed Ceiling Tiles");
		translationBuilder.add(ModBlocks.INFERIOR_WALLPAPER, "Old Wallpaper");

		translationBuilder.add(ModBlocks.LEVEL1_CEILING_AQUILA, "Aquila Sector Ceiling");
		translationBuilder.add(ModBlocks.LEVEL1_FLOOR_AQUILA, "Aquila Sector Floor");
		translationBuilder.add(ModBlocks.LEVEL1_CEILING_LIGHT, "Aquila Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL1_PILLAR_AQUILA, "Aquila Pillar");
		translationBuilder.add(ModBlocks.LEVEL1_WALL_GILD, "Gilded Sector Wall");
		translationBuilder.add(ModBlocks.GOTHIC_CONCRETE, "Gothic Concrete");
		translationBuilder.add(ModBlocks.GARDEN_CONCRETE, "Garden Concrete");
		translationBuilder.add(ModBlocks.AGED_CONCRETE, "Aged Concrete");

		translationBuilder.add(ModBlocks.PURE_BLUE, "Pure Blue");

		// Loot Blocks
		translationBuilder.add(ModBlocks.LEVEL1_CRATE, "Crate");

		// Advancements
		translationBuilder.add("text.boxys_backrooms.oneway_title", "It's a cave?");
		translationBuilder.add("text.boxys_backrooms.oneway_desc", "There are no halls here.");

		translationBuilder.add("text.boxys_backrooms.backrooms_title", "The Backrooms");
		translationBuilder.add("text.boxys_backrooms.backrooms_desc", "Enter the endless hallways of the Backrooms");

		translationBuilder.add("text.boxys_backrooms.ocean_title", "Endless Ocean");
		translationBuilder.add("text.boxys_backrooms.ocean_desc", "There's really no end.");

		translationBuilder.add("text.boxys_backrooms.94_title", "Rolling Hills");
		translationBuilder.add("text.boxys_backrooms.94_desc", "Peaceful..?");

		translationBuilder.add("text.boxys_backrooms.wrongway_title", "Wrong Direction");
		translationBuilder.add("text.boxys_backrooms.wrongway_desc", "Take the wrong way.");

		// Tags
		translationBuilder.add(ModTags.NOCLIPPABLES, "Noclippable Blocks");
		translationBuilder.add(ModTags.ALMOND_WATERS, "Almond Waters");
		translationBuilder.add(ModTags.LIGHT_BLOCKS, "Light Blocks");

		// Tooltips
		translationBuilder.add("item.gray_almond_water.tooltip", "Tastes like almonds.");
		translationBuilder.add("item.green_almond_water.tooltip", "Tastes sweet!");
		translationBuilder.add("item.red_almond_water.tooltip", "Tastes bitter.");

		// Entities
		translationBuilder.add(ModEntities.SMILER, "Smiler");
		translationBuilder.add(ModEntities.SKINSTEALER, "Skin Stealer");

		// Effects
		translationBuilder.add("effect.boxys_backrooms.gardeners_pain", "Gardener's Pain");
	}
}