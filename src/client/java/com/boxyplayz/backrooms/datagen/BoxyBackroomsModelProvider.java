package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class BoxyBackroomsModelProvider extends FabricModelProvider {

	public BoxyBackroomsModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		blockStateModelGenerator.createTrivialBlock(ModBlocks.ERRORSLATE, TexturedModel.COLUMN);
		blockStateModelGenerator.createTrivialCube(ModBlocks.OCEAN_TRANSPORTER);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_WALLPAPER);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CARPET);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CEILING_TILE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CEILING_LIGHT);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CARPET_GLITCHED);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CEILING_AQUILA);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_FLOOR_AQUILA);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_PILLAR_AQUILA);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CRATE);
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.generateFlatItem(ModItems.GREEN_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.RED_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.GRAY_ALMOND_WATER, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.ROYAL_RATION, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.FIRESALT_SHARD, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SHADOW_DUST, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SMILER_REPELLANT, ModelTemplates.FLAT_ITEM);
	}

}
