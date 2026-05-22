package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

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
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CEILING_LIGHT);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CEILING_TILE);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CARPET);
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_WALLPAPER);
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL3_CEILING_LIGHT);
		blockStateModelGenerator.createTrivialCube(ModBlocks.ELECTRICAL_BRICKS);
		blockStateModelGenerator.createTrivialBlock(ModBlocks.POWER_OUTLET_BLOCK,
				TexturedModel.createDefault((final Block block) -> {
					return new TextureMapping()
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.POWER_OUTLET_BLOCK))
							.put(TextureSlot.END, TextureMapping.getBlockTexture(ModBlocks.ELECTRICAL_BRICKS));
				},
						ModelTemplates.CUBE_COLUMN));
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL2_PIPE);

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
		itemModelGenerator.generateFlatItem(ModItems.NEON_WATER, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SMILER_SPAWN_EGG, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.WRETCH_SPAWN_EGG, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SKINSTEALER_SPAWN_EGG, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.PARTYGOER_SPAWN_EGG, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.PARTYPOOPER_SPAWN_EGG, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModBlocks.ELEVATOR.asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.LIQUID_PAIN, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.itemModelOutput.accept(ModBlocks.PROMISED_GATE.asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/promised_gate")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.BLENDER.asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/blender")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL2_FIRE_EXIT.asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/level2_fire_exit")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL2_DOOR.asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/level2_door")));
	}
}
