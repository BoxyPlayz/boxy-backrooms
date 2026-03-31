package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class BoxyBackroomsBlockLootTableProvider extends FabricBlockLootSubProvider {

	public BoxyBackroomsBlockLootTableProvider(FabricPackOutput dataOutput,
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
		add(ModBlocks.LEVEL1_CRATE, LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.LEVEL1_CRATE.asItem())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.setRolls(UniformGenerator.between(2, 6))
						.add(LootItem.lootTableItem(ModItems.GRAY_ALMOND_WATER).setWeight(100))
						.add(LootItem.lootTableItem(ModItems.GREEN_ALMOND_WATER).setWeight(80))
						.add(LootItem.lootTableItem(ModItems.RED_ALMOND_WATER).setWeight(50))
						.add(LootItem.lootTableItem(ModItems.ROYAL_RATION).setWeight(1))
						.add(LootItem.lootTableItem(ModItems.FIRESALT_SHARD).setWeight(5))
						.add(LootItem.lootTableItem(ModItems.SMILER_REPELLANT).setWeight(12))));
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

		dropOther(ModBlocks.PURE_GRASS, Blocks.GRASS_BLOCK.asItem());
	}

}
