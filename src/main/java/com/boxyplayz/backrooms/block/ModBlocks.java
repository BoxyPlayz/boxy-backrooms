package com.boxyplayz.backrooms.block;

import java.util.function.Function;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.custom.BlenderBlock;
import com.boxyplayz.backrooms.block.custom.ElevatorBlock;
import com.boxyplayz.backrooms.block.custom.ErrorSlateBlock;
import com.boxyplayz.backrooms.block.custom.InferiorCarpet;
import com.boxyplayz.backrooms.block.custom.Level0Carpet;
import com.boxyplayz.backrooms.block.custom.Level0GlitchedCarpet;
import com.boxyplayz.backrooms.block.custom.Level0Wallpaper;
import com.boxyplayz.backrooms.block.custom.Level2Door;
import com.boxyplayz.backrooms.block.custom.Level2FireExitBlock;
import com.boxyplayz.backrooms.block.custom.Level5CarpetBlock;
import com.boxyplayz.backrooms.block.custom.NoFallDamageBlock;
import com.boxyplayz.backrooms.block.custom.OceanTransporter;
import com.boxyplayz.backrooms.block.custom.PainOnBreakBlock;
import com.boxyplayz.backrooms.block.custom.PowerOutletBlock;
import com.boxyplayz.backrooms.block.custom.PremiumCarpet;
import com.boxyplayz.backrooms.block.custom.PromisedGate;
import com.boxyplayz.backrooms.block.custom.TrampolineBlock;
import com.boxyplayz.backrooms.block.custom.WaterFountainBlock;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

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
			Level0Carpet::new,
			BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1000f).destroyTime(1000f),
			true);

	public static final Block LEVEL0_CEILING_TILE = register(
			"level0_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1000f).destroyTime(1000f),
			true);

	public static final Block LEVEL0_CEILING_LIGHT = register(
			"level0_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1000f).destroyTime(1000f)
					.lightLevel(state -> 14),
			true);

	public static final Block LEVEL1_CEILING_LIGHT = register(
			"level1_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1000f).destroyTime(1000f)
					.lightLevel(state -> {
						return 15;
					}).randomTicks(),
			true);

	public static final Block LEVEL0_CARPET_GLITCHED = register(
			"level0_carpet_glitched",
			Level0GlitchedCarpet::new,
			BlockBehaviour.Properties.of().sound(SoundType.TUFF).strength(1000f).destroyTime(1000f),
			true);

	public static final Block LEVEL1_CEILING_AQUILA = register(
			"level1_ceiling_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).destroyTime(15f),
			true);

	public static final Block GOTHIC_CONCRETE = register(
			"gothic_concrete",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).destroyTime(15f),
			true);

	public static final Block LEVEL1_WALL_GILD = register(
			"level1_wall_gild",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).destroyTime(15f),
			true);

	public static final Block LEVEL1_FLOOR_AQUILA = register(
			"level1_floor_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).destroyTime(15f),
			true);

	public static final Block LEVEL1_PILLAR_AQUILA = register(
			"level1_pillar_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).destroyTime(15f),
			true);

	public static final Block LEVEL1_CRATE = register(
			"level1_crate",
			Block::new,
			BlockBehaviour.Properties.of().destroyTime(1).sound(SoundType.WOOD).strength(1f).explosionResistance(20f),
			true);

	public static final Block PURE_GRASS = register(
			"pure_grass",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).strength(0.6F).sound(SoundType.GRASS),
			true);

	public static final Block GARDEN_CONCRETE = register(
			"garden_concrete",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.6F)
					.sound(SoundType.MOSS),
			true);

	public static final Block AGED_CONCRETE = register(
			"aged_concrete",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block PREMIUM_CARPET = register(
			"premium_carpet",
			PremiumCarpet::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block PREMIUM_WALLPAPER = register(
			"premium_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block PREMIUM_CEILING_TILE = register(
			"premium_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block INFERIOR_CARPET = register(
			"inferior_carpet",
			InferiorCarpet::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block INFERIOR_WALLPAPER = register(
			"inferior_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block INFERIOR_CEILING_TILE = register(
			"inferior_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block PURE_BLUE = register(
			"pure_blue",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(999F).sound(SoundType.AMETHYST),
			true);

	public static final Block FUN_PINK = register(
			"fun_pink",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(999F).sound(SoundType.WOOL),
			true);

	public static final Block FUN_GREEN = register(
			"fun_green",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(999F).sound(SoundType.WOOL),
			true);

	public static final Block FUN_PURPLE = register(
			"fun_purple",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).strength(999F).sound(SoundType.WOOL),
			true);

	public static final Block FUN_YELLOW = register(
			"fun_yellow",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(999F).sound(SoundType.WOOL),
			true);

	public static final Block FUN_FLOOR = register(
			"fun_floor",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(999F).sound(SoundType.WOOL),
			true);

	public static final Block BLACK_TRAMPOLINE = register(
			"black_trampoline",
			TrampolineBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4F).sound(SoundType.WOOL),
			true);

	public static final Block FUN_CRATE = register(
			"fun_crate",
			PainOnBreakBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1F).sound(SoundType.WOOD),
			true);

	public static final Block PROMISED_CEILING_LIGHT = register(
			"promised_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1F).sound(SoundType.COPPER)
					.lightLevel(state -> 15),
			true);

	public static final Block PROMISED_CEILING_TILE = register(
			"promised_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1F)
					.sound(SoundType.GILDED_BLACKSTONE),
			true);

	public static final Block PROMISED_CARPET = register(
			"promised_carpet",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1F)
					.sound(SoundType.GILDED_BLACKSTONE),
			true);

	public static final Block PROMISED_WALLPAPER = register(
			"promised_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final Block PROMISED_GATE = register(
			"promised_gate",
			PromisedGate::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.6F).sound(SoundType.AMETHYST)
					.noOcclusion(),
			true);

	public static final Block POWER_OUTLET_BLOCK = register(
			"power_outlet",
			PowerOutletBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(2.4F).sound(SoundType.IRON)
					.lightLevel((state) -> 1)
					.isRedstoneConductor(Blocks::never),
			true);

	public static final Block LEVEL3_CEILING_LIGHT = register(
			"level3_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.IRON)
					.lightLevel((state) -> 9),
			true);

	public static final Block ELECTRICAL_BRICKS = register(
			"electrical_bricks",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.NETHER_BRICKS),
			true);

	public static final Block BLENDER = register(
			"blender",
			BlenderBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.GLASS)
					.noOcclusion(),
			true);

	public static final Block LEVEL2_PIPE = register(
			"level2_pipe",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.METAL),
			true);

	public static final Block LEVEL2_FIRE_EXIT = register(
			"level2_fire_exit",
			Level2FireExitBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(3.0F).sound(SoundType.METAL)
					.noOcclusion(),
			true);

	public static final Block LEVEL2_DOOR = register(
			"level2_door",
			Level2Door::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.STONE)
					.noOcclusion(),
			true);

	public static final Block ELEVATOR = register(
			"elevator",
			ElevatorBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.METAL)
					.noOcclusion(),
			true);

	public static final Block LEVEL4_CARPET = register("level4_carpet", Block::new,
			BlockBehaviour.Properties.of().strength(20f).sound(SoundType.WOOL), true);

	public static final Block PURE_WHITE_GLOW = register("pure_white_glow", Block::new,
			BlockBehaviour.Properties.of().strength(70f).sound(SoundType.GILDED_BLACKSTONE).lightLevel((state) -> 15),
			true);

	public static final Block WATER_FOUNTAIN = register("water_fountain", WaterFountainBlock::new,
			BlockBehaviour.Properties.of().strength(9999f).sound(SoundType.METAL).noOcclusion(),
			true);

	public static final Block LEVEL5_CARPET = register("level5_carpet", Level5CarpetBlock::new,
			BlockBehaviour.Properties.of().strength(70f).sound(SoundType.WOOL),
			true);

	public static void RegisterModBlocks() {
	}
}