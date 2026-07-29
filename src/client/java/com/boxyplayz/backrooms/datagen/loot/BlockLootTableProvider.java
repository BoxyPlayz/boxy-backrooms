package com.boxyplayz.backrooms.datagen.loot;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class BlockLootTableProvider extends FabricBlockLootSubProvider {

	public BlockLootTableProvider(FabricPackOutput dataOutput,
			CompletableFuture<Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		dropSelf(ModBlocks.LEVEL0_CARPET);
		dropSelf(ModBlocks.LEVEL0_CEILING_TILE);
		dropSelf(ModBlocks.ERRORSLATE);
		dropSelf(ModBlocks.OCEAN_TRANSPORTER);
		dropSelf(ModBlocks.LEVEL0_WALLPAPER);
		dropSelf(ModBlocks.LEVEL0_CARPET_GLITCHED);
		dropSelf(ModBlocks.LEVEL1_CEILING_AQUILA);
		dropSelf(ModBlocks.LEVEL1_FLOOR_AQUILA);
		dropSelf(ModBlocks.LEVEL1_PILLAR_AQUILA);
		dropSelf(ModBlocks.LEVEL0_CEILING_LIGHT);
		dropSelf(ModBlocks.LEVEL3_CEILING_LIGHT);
		dropSelf(ModBlocks.ELECTRICAL_BRICKS);
		add(ModBlocks.LEVEL1_CRATE, LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.LEVEL1_CRATE)))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.setRolls(UniformGenerator.between(2, 6))
						.add(LootItem.lootTableItem(ModItems.GRAY_ALMOND_WATER).setWeight(1000))
						.add(LootItem.lootTableItem(ModItems.GREEN_ALMOND_WATER).setWeight(800))
						.add(LootItem.lootTableItem(ModItems.RED_ALMOND_WATER).setWeight(500))
						.add(LootItem.lootTableItem(ModItems.ROYAL_RATION).setWeight(1))));
		add(ModBlocks.FUN_CRATE, LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.FUN_CRATE)))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.setRolls(UniformGenerator.between(3, 24))
						.add(LootItem.lootTableItem(Items.WIND_CHARGE).setWeight(12))
						.add(LootItem.lootTableItem(ModItems.LIQUID_PAIN).setWeight(3))
						.add(LootItem.lootTableItem(ModItems.EMPTY_ALMOND_WATER).setWeight(36))));
		dropSelf(ModBlocks.LEVEL1_WALL_GILD);
		dropSelf(ModBlocks.LEVEL1_CEILING_LIGHT);
		dropSelf(ModBlocks.GOTHIC_CONCRETE);
		dropSelf(ModBlocks.GARDEN_CONCRETE);
		dropSelf(ModBlocks.AGED_CONCRETE);
		dropSelf(ModBlocks.PREMIUM_CARPET);
		dropSelf(ModBlocks.PREMIUM_CEILING_TILE);
		dropSelf(ModBlocks.PREMIUM_WALLPAPER);
		dropSelf(ModBlocks.INFERIOR_CARPET);
		dropSelf(ModBlocks.INFERIOR_CEILING_TILE);
		dropSelf(ModBlocks.INFERIOR_WALLPAPER);

		dropSelf(ModBlocks.PROMISED_CARPET);
		dropSelf(ModBlocks.PROMISED_CEILING_LIGHT);
		dropSelf(ModBlocks.PROMISED_CEILING_TILE);
		dropSelf(ModBlocks.PROMISED_WALLPAPER);
		dropSelf(ModBlocks.POWER_OUTLET_BLOCK);

		dropOther(ModBlocks.PURE_GRASS, Blocks.GRASS_BLOCK);
		dropOther(ModBlocks.FUN_GREEN, Items.LIME_DYE);
		dropOther(ModBlocks.FUN_PINK, Items.PINK_DYE);
		dropOther(ModBlocks.FUN_YELLOW, Items.YELLOW_DYE);
		dropOther(ModBlocks.FUN_PURPLE, Items.PURPLE_DYE);
		dropOther(ModBlocks.LEVEL11_CONCRETE, Blocks.GRAY_CONCRETE);
		dropOther(ModBlocks.FUN_FLOOR, Items.RED_WOOL);
		add(ModBlocks.BLACK_TRAMPOLINE, LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.BLACK_TRAMPOLINE)))
				.withPool(
						LootPool.lootPool().when(this.doesNotHaveSilkTouch())
								.add(LootItem.lootTableItem(Blocks.SLIME_BLOCK))));
	}

}
