package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.datagen.BoxysBackroomsItemTagProvider;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

public class BoxyBackroomsEnglishLangProvider extends FabricLanguageProvider {
	public BoxyBackroomsEnglishLangProvider(FabricDataOutput dataOutput,
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
		translationBuilder.add(ModBlocks.LEVEL0_CARPET, "Carpet");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_TILE, "Tiles");

		// Advancements
		translationBuilder.add("text.boxys_backrooms.oneway_title", "It's a cave?");
		translationBuilder.add("text.boxys_backrooms.oneway_desc", "There are no halls here.");

		translationBuilder.add("text.boxys_backrooms.backrooms_title", "The Backrooms");
		translationBuilder.add("text.boxys_backrooms.backrooms_desc", "Enter the endless hallways of the Backrooms");

		translationBuilder.add("text.boxys_backrooms.ocean_title", "Endless Ocean");
		translationBuilder.add("text.boxys_backrooms.ocean_desc", "There's really no end.");

		translationBuilder.add("text.boxys_backrooms.94_title", "Rolling Hills");
		translationBuilder.add("text.boxys_backrooms.94_desc", "Peaceful..?");

		// Tags
		translationBuilder.add(BoxysBackroomsItemTagProvider.NoClippables, "Noclippable Blocks");
		translationBuilder.add(BoxysBackroomsItemTagProvider.AlmondWaters, "Almond Waters");

		// Tooltips
		translationBuilder.add("item.gray_almond_water.tooltip", "Tastes like almonds.");
		translationBuilder.add("item.green_almond_water.tooltip", "Tastes sweet!");
		translationBuilder.add("item.red_almond_water.tooltip", "Tastes bitter.");

		// Entities
		translationBuilder.add(ModEntities.SMILER, "Smiler");
	}
}