package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.datagen.lang.EnglishLangProvider;
import com.boxyplayz.backrooms.datagen.loot.BlockLootTableProvider;
import com.boxyplayz.backrooms.datagen.loot.EntityLootTableProvider;
import com.boxyplayz.backrooms.datagen.tags.BlockTagProvider;
import com.boxyplayz.backrooms.datagen.tags.ItemTagProvider;
import com.boxyplayz.backrooms.datagen.worldgen.BiomeDataProvider;
import com.boxyplayz.backrooms.datagen.worldgen.DimensionTypeProvider;
import com.boxyplayz.backrooms.datagen.tags.DamageTypeTagProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class BoxysBackroomsDataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		BoxysBackrooms.LOGGER.debug("Data Generation running!");

		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(EnglishLangProvider::new);

		pack.addProvider(RecipeDataProvider::new);
		pack.addProvider(AdvancementProvider::new);

		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(DamageTypeTagProvider::new);

		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(EntityLootTableProvider::new);

		pack.addProvider(DimensionTypeProvider::new);
		pack.addProvider(BiomeDataProvider::new);

		pack.addProvider(WorldClockProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.DIMENSION_TYPE, DimensionTypeProvider::bootstrap);
		registryBuilder.add(Registries.WORLD_CLOCK, WorldClockProvider::bootstrap);
		registryBuilder.add(Registries.BIOME, BiomeDataProvider::bootstrap);
	}

}
