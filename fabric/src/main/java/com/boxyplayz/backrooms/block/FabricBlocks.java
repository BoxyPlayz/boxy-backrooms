package com.boxyplayz.backrooms.block;

import java.util.function.Function;

import com.boxyplayz.backrooms.BoxysBackroomsFabric;
import com.boxyplayz.backrooms.block.custom.PowerOutletBlock;
import com.boxyplayz.backrooms.block.entity.PowerOutletBlockEntity;
import com.boxyplayz.backrooms.common.ModCreativeTabs;
import com.boxyplayz.backrooms.common.block.ModBlocks;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class FabricBlocks {
	public static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> blockFactory,
			BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = ModBlocks.keyOfBlock(name);
		// Create the block instance
		T block = blockFactory.apply(settings.setId(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or
		// `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = ModBlocks.keyOfItem(name);

			BlockItem blockItem = new BlockItem(block,
					new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static <T extends BlockEntity> BlockEntityType<T> register(
			String name,
			FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
			Block... blocks) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackroomsFabric.MOD_ID, name);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id,
				FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}

	public static final BlockEntityType<PowerOutletBlockEntity> POWER_OUTLET_BLOCK_ENTITY = register("power_outlet",
			PowerOutletBlockEntity::new, FabricBlocks.POWER_OUTLET_BLOCK);

	public static final PowerOutletBlock POWER_OUTLET_BLOCK = register(
			"power_outlet",
			PowerOutletBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(2.4F).sound(SoundType.IRON)
					.lightLevel((state) -> 1)
					.isRedstoneConductor(Blocks::never),
			true);

	public static void RegisterFabricSidedBlocks() {
		CreativeModeTabEvents.modifyOutputEvent(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY).register((output) -> {
			output.accept(POWER_OUTLET_BLOCK);
		});
	}

}
