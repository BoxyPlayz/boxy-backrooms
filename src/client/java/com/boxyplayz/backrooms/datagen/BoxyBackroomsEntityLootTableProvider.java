package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class BoxyBackroomsEntityLootTableProvider extends FabricEntityLootTableProvider {

	public BoxyBackroomsEntityLootTableProvider(FabricDataOutput dataOutput,
			CompletableFuture<Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		add(ModEntities.SMILER,
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F))
						.add(LootItem.lootTableItem(ModItems.SHADOW_DUST)
								.apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(1, 3))))));
	}

}
