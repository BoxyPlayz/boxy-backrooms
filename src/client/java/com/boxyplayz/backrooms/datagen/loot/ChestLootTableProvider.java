package com.boxyplayz.backrooms.datagen.loot;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.loot.ModLootTables;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ChestLootTableProvider extends SimpleFabricLootTableSubProvider {

	public ChestLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {
		output.accept(ModLootTables.LEVEL_11_ROOF_CHEST_LOOT, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0f))
						.add(LootItem.lootTableItem(ModItems.GRAY_KEY))));
	}

}
