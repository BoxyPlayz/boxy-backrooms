package com.boxyplayz.backrooms.block;

import java.util.function.Function;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.custom.ErrorSlateBlock;
import com.boxyplayz.backrooms.block.custom.Level0Wallpaper;
import com.boxyplayz.backrooms.block.custom.OceanTransporter;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory,
			BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		Block block = blockFactory.apply(settings.setId(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or
		// `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block,
					new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name));
	}

	public static final Block ERRORSLATE = register(
			"errorslate",
			ErrorSlateBlock::new,
			BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE).strength(50f).destroyTime(20f),
			true);

	public static final Block OCEAN_TRANSPORTER = register(
			"ocean_transporter",
			OceanTransporter::new,
			BlockBehaviour.Properties.of().sound(SoundType.IRON).strength(50f).destroyTime(20f),
			true);

	public static final Block LEVEL0_WALLPAPER = register(
			"level0_wallpaper",
			Level0Wallpaper::new,
			BlockBehaviour.Properties.of().sound(SoundType.BAMBOO).strength(20f).destroyTime(10f),
			true);
	
	public static final Block LEVEL0_CARPET = register(
			"level0_carpet",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1000f).destroyTime(1000f),
			true);
	
	public static final Block LEVEL0_CEILING_TILE = register(
			"level0_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1000f).destroyTime(1000f),
			true);

	public static void RegisterModBlocks() {
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY).register(itemGroup -> {
			itemGroup.accept(ERRORSLATE.asItem());
			itemGroup.accept(OCEAN_TRANSPORTER.asItem());
			itemGroup.accept(LEVEL0_WALLPAPER.asItem());
			itemGroup.accept(LEVEL0_CARPET.asItem());
			itemGroup.accept(LEVEL0_CEILING_TILE.asItem());
		});
		;
	}
}