package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.datagen.BoxyBackroomsAdvancementProvider;
import com.boxyplayz.backrooms.datagen.BoxyBackroomsBlockLootTableProvider;
import com.boxyplayz.backrooms.datagen.BoxyBackroomsModelProvider;
import com.boxyplayz.backrooms.datagen.BoxyBackroomsRecipeProvider;
import com.boxyplayz.backrooms.datagen.BoxysBackroomsBlockTagProvider;
import com.boxyplayz.backrooms.datagen.BoxysBackroomsItemTagProvider;
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

		pack.addProvider(BoxyBackroomsBlockLootTableProvider::new);
	}

}
