package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

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
		translationBuilder.add("itemGroup.boxys_backrooms", "Boxy's Backrooms");

		// Food
		translationBuilder.add("item.boxys_backrooms.gray_almond_water", "Almond Water");
		translationBuilder.add("item.boxys_backrooms.green_almond_water", "Almond Water");
		translationBuilder.add("item.boxys_backrooms.red_almond_water", "Almond Water");
		translationBuilder.add("item.boxys_backrooms.royal_ration", "Royal Ration");

		// Other Items
		translationBuilder.add("item.boxys_backrooms.firesalt_shard", "Firesalt Shard");

		// Noclippable Blocks
		translationBuilder.add("block.boxys_backrooms.errorslate", "Errorslate");
		translationBuilder.add("block.boxys_backrooms.ocean_transporter", "Aquatic Transporter");
		translationBuilder.add("block.boxys_backrooms.level0_wallpaper", "Wallpaper");

		// Regular Blocks
		translationBuilder.add("block.boxys_backrooms.level0_carpet", "Carpet");
		translationBuilder.add("block.boxys_backrooms.level0_ceiling_tile", "Tiles");

		// Advancements
		translationBuilder.add("text.boxys_backrooms.oneway_title", "It's a cave?");
		translationBuilder.add("text.boxys_backrooms.oneway_desc", "There are no halls here.");
		translationBuilder.add("text.boxys_backrooms.backrooms_title", "The Backrooms");
		translationBuilder.add("text.boxys_backrooms.backrooms_desc", "Enter the endless hallways of the Backrooms");
		translationBuilder.add("text.boxys_backrooms.ocean_title", "Endless Ocean");
		translationBuilder.add("text.boxys_backrooms.ocean_desc", "There's really no end.");
	}
}