package com.boxyplayz.backrooms;

import org.jspecify.annotations.NonNull;

import com.boxyplayz.backrooms.datagen.BoxyBackroomsModelProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BoxysBackroomsDataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
 
        pack.addProvider(BoxyBackroomsModelProvider::new);

	}

}
