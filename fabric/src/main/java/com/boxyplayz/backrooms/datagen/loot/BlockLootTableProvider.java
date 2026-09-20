package com.boxyplayz.backrooms.datagen.loot;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.FabricBlocks;
import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.item.ModItems;

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
		dropSelf(ModBlocks.ERRORSLATE.get());
		dropSelf(ModBlocks.OCEAN_TRANSPORTER.get());
		dropSelf(ModBlocks.LEVEL0_CARPET.get());
		dropSelf(ModBlocks.LEVEL0_CEILING_TILE.get());
		dropSelf(ModBlocks.LEVEL0_WALLPAPER.get());
		add(ModBlocks.LEVEL0_CEILING_LIGHT.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(this.hasSilkTouch())
						.add(LootItem.lootTableItem(ModBlocks.LEVEL0_CEILING_LIGHT.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.add(LootItem.lootTableItem(Blocks.REDSTONE_LAMP))));
		add(ModBlocks.LEVEL3_CEILING_LIGHT.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(this.hasSilkTouch())
						.add(LootItem.lootTableItem(ModBlocks.LEVEL3_CEILING_LIGHT.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.add(LootItem.lootTableItem(Blocks.REDSTONE_LAMP))));
		add(ModBlocks.LEVEL1_CEILING_LIGHT.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(this.hasSilkTouch())
						.add(LootItem.lootTableItem(ModBlocks.LEVEL1_CEILING_LIGHT.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.add(LootItem.lootTableItem(Blocks.REDSTONE_LAMP))));
		dropOther(ModBlocks.LEVEL0_CARPET_GLITCHED.get(), ModBlocks.LEVEL0_CARPET.get());

		add(ModBlocks.ELECTRICAL_BRICKS.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(this.hasSilkTouch())
						.add(LootItem.lootTableItem(ModBlocks.ELECTRICAL_BRICKS.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.add(LootItem.lootTableItem(Blocks.BRICKS))));

		dropSelf(ModBlocks.BLENDER.get());

		add(ModBlocks.LEVEL1_CRATE.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.LEVEL1_CRATE.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.setRolls(UniformGenerator.between(2, 6))
						.add(LootItem.lootTableItem(ModItems.GRAY_ALMOND_WATER.get()).setWeight(1000))
						.add(LootItem.lootTableItem(ModItems.GREEN_ALMOND_WATER.get()).setWeight(800))
						.add(LootItem.lootTableItem(ModItems.RED_ALMOND_WATER.get()).setWeight(500))
						.add(LootItem.lootTableItem(ModItems.ROYAL_RATION.get()).setWeight(1))));
		add(ModBlocks.FUN_CRATE.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.FUN_CRATE.get())))
				.withPool(LootPool.lootPool().when(this.doesNotHaveSilkTouch())
						.setRolls(UniformGenerator.between(3, 24))
						.add(LootItem.lootTableItem(Items.WIND_CHARGE).setWeight(12))
						.add(LootItem.lootTableItem(ModItems.LIQUID_PAIN.get()).setWeight(3))
						.add(LootItem.lootTableItem(ModItems.EMPTY_ALMOND_WATER.get()).setWeight(36))));
		dropSelf(ModBlocks.LEVEL1_CEILING_AQUILA.get());
		dropSelf(ModBlocks.LEVEL1_WALL_GILD.get());
		dropSelf(ModBlocks.LEVEL1_FLOOR_AQUILA.get());
		dropSelf(ModBlocks.LEVEL1_PILLAR_AQUILA.get());
		dropSelf(ModBlocks.LEVEL1_CEILING_LIGHT.get());
		dropSelf(ModBlocks.GOTHIC_CONCRETE.get());
		dropSelf(ModBlocks.GARDEN_CONCRETE.get());
		dropSelf(ModBlocks.AGED_CONCRETE.get());
		dropSelf(ModBlocks.PREMIUM_CARPET.get());
		dropSelf(ModBlocks.PREMIUM_CEILING_TILE.get());
		dropSelf(ModBlocks.PREMIUM_WALLPAPER.get());
		dropSelf(ModBlocks.INFERIOR_CARPET.get());
		dropSelf(ModBlocks.INFERIOR_CEILING_TILE.get());
		dropSelf(ModBlocks.INFERIOR_WALLPAPER.get());

		dropSelf(ModBlocks.PROMISED_CARPET.get());
		dropSelf(ModBlocks.PROMISED_CEILING_LIGHT.get());
		dropSelf(ModBlocks.PROMISED_CEILING_TILE.get());
		dropSelf(ModBlocks.PROMISED_WALLPAPER.get());
		dropSelf(FabricBlocks.POWER_OUTLET_BLOCK);

		dropSelf(ModBlocks.FUN_FLOOR.get());

		dropOther(ModBlocks.PURE_GRASS.get(), Blocks.GRASS_BLOCK);
		dropOther(ModBlocks.FUN_GREEN.get(), Items.LIME_DYE);
		dropOther(ModBlocks.FUN_PINK.get(), Items.PINK_DYE);
		dropOther(ModBlocks.FUN_YELLOW.get(), Items.YELLOW_DYE);
		dropOther(ModBlocks.FUN_PURPLE.get(), Items.PURPLE_DYE);
		dropOther(ModBlocks.LEVEL11_CONCRETE.get(), Blocks.GRAY_CONCRETE);
		dropOther(ModBlocks.FUN_FLOOR.get(), Items.RED_WOOL);
		add(ModBlocks.BLACK_TRAMPOLINE.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().when(
						this.hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.BLACK_TRAMPOLINE.get())))
				.withPool(
						LootPool.lootPool().when(this.doesNotHaveSilkTouch())
								.add(LootItem.lootTableItem(Blocks.SLIME_BLOCK))));

		add(ModBlocks.LEVEL2_PIPE.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 17))
						.add(LootItem.lootTableItem(Items.IRON_INGOT))
						.add(LootItem.lootTableItem(Items.COPPER_INGOT))));

		dropOther(ModBlocks.LEVEL2_FIRE_EXIT.get(), Blocks.IRON_DOOR);
		dropOther(ModBlocks.LEVEL2_DOOR.get(), Blocks.IRON_DOOR);
		dropOther(ModBlocks.ELEVATOR.get(), Blocks.IRON_BLOCK);
		dropOther(ModBlocks.LEVEL4_CARPET.get(), Items.LIGHT_BLUE_DYE);
		dropOther(ModBlocks.PURE_WHITE_GLOW.get(), Items.ENDER_PEARL);
		dropOther(ModBlocks.WATER_FOUNTAIN.get(), Items.IRON_BARS);
		dropOther(ModBlocks.LEVEL5_CARPET.get(), Items.CHORUS_FRUIT);
		dropOther(ModBlocks.LEVEL6_ENTRY.get(), Items.ECHO_SHARD);
		dropOther(ModBlocks.LEVEL9_ENTRY.get(), Blocks.OAK_PLANKS);
		dropOther(ModBlocks.FALSE_WHEAT.get(), Items.WHEAT);
		dropOther(ModBlocks.STEP_VISIBLE.get(), Blocks.BARRIER);
	}

}
