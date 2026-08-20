package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.datagen.clocks.WorldClockProvider;
import com.boxyplayz.backrooms.datagen.lang.EnglishLangProvider;
import com.boxyplayz.backrooms.datagen.loot.BlockLootTableProvider;
import com.boxyplayz.backrooms.datagen.loot.ChestLootTableProvider;
import com.boxyplayz.backrooms.datagen.loot.EntityLootTableProvider;
import com.boxyplayz.backrooms.datagen.recipe.RecipeDataProvider;
import com.boxyplayz.backrooms.datagen.tags.BiomeTagProvider;
import com.boxyplayz.backrooms.datagen.tags.BlockTagProvider;
import com.boxyplayz.backrooms.datagen.tags.ItemTagProvider;
import com.boxyplayz.backrooms.datagen.worldgen.BiomeDataProvider;
import com.boxyplayz.backrooms.datagen.worldgen.DimensionTypeProvider;
import com.boxyplayz.backrooms.datagen.worldgen.FeatureGenerator;
import com.boxyplayz.backrooms.datagen.worldgen.StructureProvider;
import com.boxyplayz.backrooms.datagen.tags.DamageTypeTagProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class BoxysBackroomsDataGenEntry implements DataGeneratorEntrypoint {

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
		pack.addProvider(BiomeTagProvider::new);

		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(EntityLootTableProvider::new);
		pack.addProvider(ChestLootTableProvider::new);

		pack.addProvider(DimensionTypeProvider::new);
		pack.addProvider(BiomeDataProvider::new);
		pack.addProvider(StructureProvider::new);
		pack.addProvider(FeatureGenerator::new);

		pack.addProvider(WorldClockProvider::new);

		pack.addProvider(DamageTypeProvider::new);

		pack.addProvider(EnchantmentsProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.WORLD_CLOCK, WorldClockProvider::bootstrap);
		registryBuilder.add(Registries.DIMENSION_TYPE, DimensionTypeProvider::bootstrap);
		registryBuilder.add(Registries.BIOME, BiomeDataProvider::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, DamageTypeProvider::bootstrap);
		registryBuilder.add(Registries.ENCHANTMENT, EnchantmentsProvider::bootstrap);
		registryBuilder.add(Registries.TEMPLATE_POOL, StructureProvider::templBoot);
		registryBuilder.add(Registries.STRUCTURE, StructureProvider::structBoot);
		registryBuilder.add(Registries.STRUCTURE_SET, StructureProvider::setBoot);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, FeatureGenerator::configureFeatures);
		registryBuilder.add(Registries.PLACED_FEATURE, FeatureGenerator::placeFeatures);
	}

}
