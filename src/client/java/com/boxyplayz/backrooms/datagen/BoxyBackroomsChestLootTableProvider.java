package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.loot.ModLootTables;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class BoxyBackroomsChestLootTableProvider extends SimpleFabricLootTableProvider {

	public BoxyBackroomsChestLootTableProvider(FabricDataOutput dataOutput,
			CompletableFuture<Provider> registryLookup) {
		super(dataOutput, registryLookup, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> biConsumer) {
		biConsumer.accept(ModLootTables.LEVEL_0_MAZE_CHEST_LOOT, LootTable.lootTable().withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(4))
				.add(LootItem.lootTableItem(ModItems.GRAY_ALMOND_WATER).setWeight(100))
				.add(LootItem.lootTableItem(ModItems.GREEN_ALMOND_WATER).setWeight(80))
				.add(LootItem.lootTableItem(ModItems.RED_ALMOND_WATER).setWeight(50))
				.add(LootItem.lootTableItem(ModItems.ROYAL_RATION).setWeight(1))));
	}

}
