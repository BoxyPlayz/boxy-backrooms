package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModelProvider extends FabricModelProvider {

	public ModelProvider(FabricPackOutput output) {
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
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_WALL_GILD);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PURE_GRASS);
		blockStateModelGenerator.createTrivialCube(ModBlocks.GOTHIC_CONCRETE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.GARDEN_CONCRETE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.AGED_CONCRETE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_CARPET);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_CEILING_TILE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_WALLPAPER);
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_CARPET);
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_CEILING_TILE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_WALLPAPER);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PURE_BLUE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CEILING_LIGHT);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_GREEN);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_PINK);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_PURPLE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_YELLOW);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_FLOOR);
		blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_TRAMPOLINE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_CRATE);
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
		itemModelGenerator.generateFlatItem(ModItems.FIRESTEEL_ALLOY, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.FIRESTEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
	}

}
