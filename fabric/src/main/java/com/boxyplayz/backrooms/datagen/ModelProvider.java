package com.boxyplayz.backrooms.datagen;

import com.boxyplayz.backrooms.BoxysBackroomsFabric;
import com.boxyplayz.backrooms.block.FabricBlocks;
import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.item.ModItems;

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
		blockStateModelGenerator.createTrivialBlock(ModBlocks.ERRORSLATE.get(), TexturedModel.COLUMN);
		blockStateModelGenerator.createTrivialCube(ModBlocks.OCEAN_TRANSPORTER.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_WALLPAPER.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CARPET.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CEILING_TILE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CEILING_LIGHT.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL0_CARPET_GLITCHED.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CEILING_AQUILA.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_FLOOR_AQUILA.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_PILLAR_AQUILA.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CRATE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_WALL_GILD.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PURE_GRASS.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.GOTHIC_CONCRETE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.GARDEN_CONCRETE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.AGED_CONCRETE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_CARPET.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_CEILING_TILE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PREMIUM_WALLPAPER.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_CARPET.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_CEILING_TILE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.INFERIOR_WALLPAPER.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PURE_BLUE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL1_CEILING_LIGHT.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_GREEN.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_PINK.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_PURPLE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_YELLOW.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_FLOOR.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_TRAMPOLINE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.FUN_CRATE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CEILING_LIGHT.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CEILING_TILE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_CARPET.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PROMISED_WALLPAPER.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL3_CEILING_LIGHT.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.ELECTRICAL_BRICKS.get());
		blockStateModelGenerator.createTrivialBlock(FabricBlocks.POWER_OUTLET_BLOCK,
				TexturedModel.createDefault((final Block block) -> {
					return new TextureMapping()
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(FabricBlocks.POWER_OUTLET_BLOCK))
							.put(TextureSlot.END, TextureMapping.getBlockTexture(ModBlocks.ELECTRICAL_BRICKS.get()));
				},
						ModelTemplates.CUBE_COLUMN));
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL2_PIPE.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL4_CARPET.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.PURE_WHITE_GLOW.get());
		blockStateModelGenerator.createTrivialCube(ModBlocks.LEVEL6_ENTRY.get());
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.generateFlatItem(ModItems.GREEN_ALMOND_WATER.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.RED_ALMOND_WATER.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.GRAY_ALMOND_WATER.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.ROYAL_RATION.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.FIRESALT_SHARD.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SHADOW_DUST.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SMILER_REPELLANT.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.FIRESTEEL_ALLOY.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.FIRESTEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.NEON_WATER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SMILER_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.WRETCH_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.SKINSTEALER_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.PARTYGOER_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.PARTYPOOPER_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModBlocks.ELEVATOR.get().asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.LIQUID_PAIN.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.GRAY_KEY.get().asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.EMPTY_ALMOND_WATER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerator.itemModelOutput.accept(ModBlocks.PROMISED_GATE.get().asItem(),
				ItemModelUtils
						.plainModel(
								Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "block/promised_gate")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.BLENDER.get().asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "block/blender")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL2_FIRE_EXIT.get().asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID,
								"block/level2_fire_exit")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL2_DOOR.get().asItem(),
				ItemModelUtils
						.plainModel(Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "block/level2_door")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.WATER_FOUNTAIN.get().asItem(),
				ItemModelUtils
						.plainModel(
								Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "block/water_fountain")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL5_CARPET.get().asItem(),
				ItemModelUtils
						.plainModel(
								Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, "block/level5_carpet")));
		itemModelGenerator.itemModelOutput.accept(ModBlocks.LEVEL5_ENTRY_TABLE.get().asItem(),
				ItemModelUtils
						.plainModel(
								Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID,
										"block/level5_entry_table")));
		itemModelGenerator.generateFlatItem(ModItems.NEIGHBORHOOD_WATCH_SPAWN_EGG.get(),
				ModelTemplates.FLAT_HANDHELD_ITEM);
	}
}
