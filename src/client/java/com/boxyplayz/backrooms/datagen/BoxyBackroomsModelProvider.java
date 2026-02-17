package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class BoxyBackroomsModelProvider extends FabricModelProvider {
	public BoxyBackroomsModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.generateFlatItem(ModItems.GREEN_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.RED_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.GRAY_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
	}

}
