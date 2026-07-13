package com.boxyplayz.backrooms.datagen.loot;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.loot.ModLootTables;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ChestLootTableProvider extends SimpleFabricLootTableSubProvider {

	public ChestLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {
		output.accept(ModLootTables.LEVEL_11_ROOF_CHEST_LOOT, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0f))
						.add(LootItem.lootTableItem(ModItems.GRAY_KEY)))
				.withPool(LootPool.lootPool()
						.setRolls(UniformGenerator.between(4, 16))
						.add(LootItem.lootTableItem(Items.GOLD_INGOT)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 53))).setWeight(400))
						.add(LootItem.lootTableItem(ModBlocks.WATER_FOUNTAIN).setWeight(1))
						.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(120)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 23))))
						.add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(40)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))))
						.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(
								EnchantRandomlyFunction.randomEnchantment().allowingIncompatibleEnchantments())
								.setWeight(70))
						.add(LootItem.lootTableItem(Blocks.ANCIENT_DEBRIS.asItem()).setWeight(30)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 16))))

				));
	}

}
