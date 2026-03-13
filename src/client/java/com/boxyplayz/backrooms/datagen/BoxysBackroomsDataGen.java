package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.datagen.lang.BoxyBackroomsEnglishLangProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BoxysBackroomsDataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		BoxysBackrooms.LOGGER.info("Data Generation running!");

		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(BoxyBackroomsModelProvider::new);
		pack.addProvider(BoxyBackroomsEnglishLangProvider::new);

		pack.addProvider(BoxyBackroomsRecipeProvider::new);
		pack.addProvider(BoxyBackroomsAdvancementProvider::new);

		pack.addProvider(BoxysBackroomsItemTagProvider::new);
		pack.addProvider(BoxysBackroomsBlockTagProvider::new);
		pack.addProvider(BoxysBackroomsDamageTypeTagProvider::new);

		pack.addProvider(BoxyBackroomsChestLootTableProvider::new);
		pack.addProvider(BoxyBackroomsBlockLootTableProvider::new);
		pack.addProvider(BoxyBackroomsEntityLootTableProvider::new);
	}

}
