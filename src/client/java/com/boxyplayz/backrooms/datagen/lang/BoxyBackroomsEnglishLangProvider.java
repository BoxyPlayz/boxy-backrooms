package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

public class BoxyBackroomsEnglishLangProvider extends FabricLanguageProvider {
	public BoxyBackroomsEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		// Specifying en_us is optional, as it's the default language code
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
		translationBuilder.add("itemGroup.boxys_backrooms", "Boxy's Backrooms");
		translationBuilder.add("item.boxys_backrooms.gray_almond_water", "Almond Water");
		translationBuilder.add("item.boxys_backrooms.green_almond_water", "Almond Water");
		translationBuilder.add("item.boxys_backrooms.red_almond_water", "Almond Water");
		translationBuilder.add("block.boxys_backrooms.errorslate", "Errorslate");
		translationBuilder.add("block.boxys_backrooms.ocean_transporter", "Oceanic Transporter");
		translationBuilder.add("text.boxys_backrooms.oneway_title", "One Way Cavern");
		translationBuilder.add("text.boxys_backrooms.oneway_desc", "Enter the endless caverns of level 8");
	}
}