package com.boxyplayz.backrooms;

import com.boxyplayz.backrooms.datagen.BoxyBackroomsModelProvider;
import com.boxyplayz.backrooms.datagen.lang.BoxyBackroomsEnglishLangProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BoxysBackroomsDataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
 
        pack.addProvider(BoxyBackroomsModelProvider::new);
		pack.addProvider(BoxyBackroomsEnglishLangProvider::new);
	}

}
