package com.boxyplayz.backrooms.common.block;

import java.util.List;
import java.util.function.Function;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.ModCreativeTabs;
import com.boxyplayz.backrooms.common.block.custom.BlenderBlock;
import com.boxyplayz.backrooms.common.block.custom.ConditionlessDimensionTeleportBlock;
import com.boxyplayz.backrooms.common.block.custom.ElevatorBlock;
import com.boxyplayz.backrooms.common.block.custom.ErrorSlateBlock;
import com.boxyplayz.backrooms.common.block.custom.FalseWheat;
import com.boxyplayz.backrooms.common.block.custom.InferiorCarpet;
import com.boxyplayz.backrooms.common.block.custom.Level0Carpet;
import com.boxyplayz.backrooms.common.block.custom.Level0GlitchedCarpet;
import com.boxyplayz.backrooms.common.block.custom.Level0Wallpaper;
import com.boxyplayz.backrooms.common.block.custom.Level11Concrete;
import com.boxyplayz.backrooms.common.block.custom.Level2Door;
import com.boxyplayz.backrooms.common.block.custom.Level2FireExitBlock;
import com.boxyplayz.backrooms.common.block.custom.Level5CarpetBlock;
import com.boxyplayz.backrooms.common.block.custom.Level5EntryTable;
import com.boxyplayz.backrooms.common.block.custom.Level6EntryBlock;
import com.boxyplayz.backrooms.common.block.custom.Level9EntryHouse;
import com.boxyplayz.backrooms.common.block.custom.NoFallDamageBlock;
import com.boxyplayz.backrooms.common.block.custom.OceanTransporter;
import com.boxyplayz.backrooms.common.block.custom.OverworldTransporter;
import com.boxyplayz.backrooms.common.block.custom.PainOnBreakBlock;
import com.boxyplayz.backrooms.common.block.custom.PremiumCarpet;
import com.boxyplayz.backrooms.common.block.custom.PromisedGate;
import com.boxyplayz.backrooms.common.block.custom.PureBlue;
import com.boxyplayz.backrooms.common.block.custom.StepVisibleBlock;
import com.boxyplayz.backrooms.common.block.custom.TrampolineBlock;
import com.boxyplayz.backrooms.common.block.custom.WaterFountainBlock;
import com.boxyplayz.backrooms.common.world.ModDimensions;

import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

	public static <T extends Block> DeferredSupplier<T> register(String name,
			Function<BlockBehaviour.Properties, T> blockFactory,
			BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		DeferredSupplier<T> block = BoxysBackroomsCommon.BLOCKS.register(name,
				() -> blockFactory.apply(settings.setId(blockKey)));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or
		// `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = keyOfItem(name);

			BoxysBackroomsCommon.ITEMS.register(name,
					() -> new BlockItem(block.get(),
							new Item.Properties().setId(itemKey).arch$tab(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY)
									.useBlockDescriptionPrefix()));
		}

		return block;
	}

	public static <T extends Block> DeferredSupplier<T> registerTeleport(String name,
			Function<BlockBehaviour.Properties, T> blockFactory,
			ConditionlessDimensionTeleportBlock.CustomProps settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		DeferredSupplier<T> block = BoxysBackroomsCommon.BLOCKS.register(name,
				() -> blockFactory.apply(settings.build().setId(blockKey)));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or
		// `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = keyOfItem(name);

			BoxysBackroomsCommon.ITEMS.register(name,
					() -> new BlockItem(block.get(),
							new Item.Properties().setId(itemKey).arch$tab(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY)
									.useBlockDescriptionPrefix()));
		}

		return block;
	}

	public static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, name));
	}

	public static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, name));
	}

	public static final DeferredSupplier<ErrorSlateBlock> ERRORSLATE = register(
			"errorslate",
			ErrorSlateBlock::new,
			BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE).strength(50f)
					.mapColor(MapColor.DEEPSLATE),
			true);

	public static final DeferredSupplier<OceanTransporter> OCEAN_TRANSPORTER = register(
			"ocean_transporter",
			OceanTransporter::new,
			BlockBehaviour.Properties.of().sound(SoundType.IRON).strength(50f)
					.mapColor(MapColor.WATER),
			true);

	public static final DeferredSupplier<Level0Wallpaper> LEVEL0_WALLPAPER = register(
			"level0_wallpaper",
			Level0Wallpaper::new,
			BlockBehaviour.Properties.of().sound(SoundType.BAMBOO).strength(6f, 8f)
					.mapColor(MapColor.TERRACOTTA_YELLOW),
			true);

	public static final DeferredSupplier<Level0Carpet> LEVEL0_CARPET = register(
			"level0_carpet",
			Level0Carpet::new,
			BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(5f, 7f).mapColor(MapColor.COLOR_YELLOW),
			true);

	public static final DeferredSupplier<Block> LEVEL0_CEILING_TILE = register(
			"level0_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(70f, 120f)
					.mapColor(MapColor.TERRACOTTA_YELLOW),
			true);

	public static final DeferredSupplier<Block> LEVEL0_CEILING_LIGHT = register(
			"level0_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(30f, 15f).mapColor(MapColor.TERRACOTTA_WHITE)
					.lightLevel(state -> 14),
			true);

	public static final DeferredSupplier<Block> LEVEL1_CEILING_LIGHT = register(
			"level1_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(8f).mapColor(MapColor.TERRACOTTA_WHITE)
					.lightLevel(state -> {
						return 15;
					}).randomTicks(),
			true);

	public static final DeferredSupplier<Level0GlitchedCarpet> LEVEL0_CARPET_GLITCHED = register(
			"level0_carpet_glitched",
			Level0GlitchedCarpet::new,
			BlockBehaviour.Properties.of().sound(SoundType.TUFF).strength(1000f).mapColor(MapColor.COLOR_BLACK),
			true);

	public static final DeferredSupplier<Block> LEVEL1_CEILING_AQUILA = register(
			"level1_ceiling_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).mapColor(MapColor.COLOR_LIGHT_GRAY),
			true);

	public static final DeferredSupplier<Block> GOTHIC_CONCRETE = register(
			"gothic_concrete",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(25f).mapColor(MapColor.COLOR_GRAY),
			true);

	public static final DeferredSupplier<Block> LEVEL1_WALL_GILD = register(
			"level1_wall_gild",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(10f)
					.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY),
			true);

	public static final DeferredSupplier<Block> LEVEL1_FLOOR_AQUILA = register(
			"level1_floor_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(15f)
					.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY),
			true);

	public static final DeferredSupplier<Block> LEVEL1_PILLAR_AQUILA = register(
			"level1_pillar_aquila",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(15f)
					.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY),
			true);

	public static final DeferredSupplier<Block> LEVEL1_CRATE = register(
			"level1_crate",
			Block::new,
			BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f).mapColor(MapColor.COLOR_BROWN),
			true);

	public static final DeferredSupplier<Block> PURE_GRASS = register(
			"pure_grass",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).strength(0.6F).sound(SoundType.GRASS),
			true);

	public static final DeferredSupplier<Block> GARDEN_CONCRETE = register(
			"garden_concrete",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.6F)
					.sound(SoundType.MOSS),
			true);

	public static final DeferredSupplier<Block> AGED_CONCRETE = register(
			"aged_concrete",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final DeferredSupplier<PremiumCarpet> PREMIUM_CARPET = register(
			"premium_carpet",
			PremiumCarpet::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.6F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<Block> PREMIUM_WALLPAPER = register(
			"premium_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.BAMBOO),
			true);

	public static final DeferredSupplier<Block> PREMIUM_CEILING_TILE = register(
			"premium_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final DeferredSupplier<InferiorCarpet> INFERIOR_CARPET = registerTeleport(
			"inferior_carpet",
			InferiorCarpet::new,
			new ConditionlessDimensionTeleportBlock.CustomProps(BlockBehaviour.Properties.of()
					.mapColor(MapColor.TERRACOTTA_RED).strength(0.6F).sound(SoundType.STONE))
					.starts(List.of(ModDimensions.LEVEL0_2.level, ModDimensions.BLUE_CHANNEL.level))
					.target(ModDimensions.LEVEL_NEGATIVE_0_2),
			true);

	public static final DeferredSupplier<Block> INFERIOR_WALLPAPER = register(
			"inferior_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final DeferredSupplier<Block> INFERIOR_CEILING_TILE = register(
			"inferior_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final DeferredSupplier<Block> PURE_BLUE = register(
			"pure_blue",
			PureBlue::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(-1.0F, 3600000.0F)
					.sound(SoundType.AMETHYST),
			true);

	public static final DeferredSupplier<NoFallDamageBlock> FUN_PINK = register(
			"fun_pink",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(999F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<NoFallDamageBlock> FUN_GREEN = register(
			"fun_green",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(999F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<NoFallDamageBlock> FUN_PURPLE = register(
			"fun_purple",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).strength(999F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<NoFallDamageBlock> FUN_YELLOW = register(
			"fun_yellow",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(999F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<NoFallDamageBlock> FUN_FLOOR = register(
			"fun_floor",
			NoFallDamageBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(999F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<TrampolineBlock> BLACK_TRAMPOLINE = register(
			"black_trampoline",
			TrampolineBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4F).sound(SoundType.WOOL),
			true);

	public static final DeferredSupplier<PainOnBreakBlock> FUN_CRATE = register(
			"fun_crate",
			PainOnBreakBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1F).sound(SoundType.WOOD),
			true);

	public static final DeferredSupplier<Block> PROMISED_CEILING_LIGHT = register(
			"promised_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1F).sound(SoundType.COPPER)
					.lightLevel(state -> 15),
			true);

	public static final DeferredSupplier<Block> PROMISED_CEILING_TILE = register(
			"promised_ceiling_tile",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1F)
					.sound(SoundType.GILDED_BLACKSTONE),
			true);

	public static final DeferredSupplier<Block> PROMISED_CARPET = register(
			"promised_carpet",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1F)
					.sound(SoundType.GILDED_BLACKSTONE),
			true);

	public static final DeferredSupplier<Block> PROMISED_WALLPAPER = register(
			"promised_wallpaper",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.6F).sound(SoundType.STONE),
			true);

	public static final DeferredSupplier<PromisedGate> PROMISED_GATE = register(
			"promised_gate",
			PromisedGate::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.6F).sound(SoundType.AMETHYST)
					.noOcclusion(),
			true);

	public static final DeferredSupplier<Block> LEVEL3_CEILING_LIGHT = register(
			"level3_ceiling_light",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.IRON)
					.lightLevel((state) -> 9),
			true);

	public static final DeferredSupplier<Block> ELECTRICAL_BRICKS = register(
			"electrical_bricks",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.NETHER_BRICKS),
			true);

	public static final DeferredSupplier<BlenderBlock> BLENDER = register(
			"blender",
			BlenderBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).strength(2.0F).sound(SoundType.GLASS)
					.noOcclusion(),
			true);

	public static final DeferredSupplier<Block> LEVEL2_PIPE = register(
			"level2_pipe",
			Block::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.METAL),
			true);

	public static final DeferredSupplier<Level2FireExitBlock> LEVEL2_FIRE_EXIT = register(
			"level2_fire_exit",
			Level2FireExitBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(3.0F).sound(SoundType.METAL)
					.noOcclusion(),
			true);

	public static final DeferredSupplier<Level2Door> LEVEL2_DOOR = register(
			"level2_door",
			Level2Door::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.STONE)
					.noOcclusion(),
			true);

	public static final DeferredSupplier<ElevatorBlock> ELEVATOR = register(
			"elevator",
			ElevatorBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.0F).sound(SoundType.METAL)
					.noOcclusion(),
			true);

	public static final DeferredSupplier<Block> LEVEL4_CARPET = register("level4_carpet", Block::new,
			BlockBehaviour.Properties.of().strength(20f).sound(SoundType.WOOL).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE),
			true);

	public static final DeferredSupplier<Block> PURE_WHITE_GLOW = register("pure_white_glow", Block::new,
			BlockBehaviour.Properties.of().strength(70f).sound(SoundType.GILDED_BLACKSTONE).lightLevel((state) -> 15)
					.mapColor(MapColor.TERRACOTTA_WHITE),
			true);

	public static final DeferredSupplier<WaterFountainBlock> WATER_FOUNTAIN = register("water_fountain",
			WaterFountainBlock::new,
			BlockBehaviour.Properties.of().strength(9999f).sound(SoundType.METAL).noOcclusion()
					.mapColor(MapColor.RAW_IRON),
			true);

	public static final DeferredSupplier<Level5CarpetBlock> LEVEL5_CARPET = register("level5_carpet",
			Level5CarpetBlock::new,
			BlockBehaviour.Properties.of().strength(70f).sound(SoundType.WOOL).mapColor(MapColor.CLAY),
			true);

	public static final DeferredSupplier<Level6EntryBlock> LEVEL6_ENTRY = register("level6_entry",
			Level6EntryBlock::new,
			BlockBehaviour.Properties.of().noOcclusion().isRedstoneConductor((state, level, pos) -> false)
					.mapColor(MapColor.TERRACOTTA_BLACK),
			true);

	public static final DeferredSupplier<Level5EntryTable> LEVEL5_ENTRY_TABLE = register("level5_entry_table",
			Level5EntryTable::new,
			BlockBehaviour.Properties.of().noOcclusion().isRedstoneConductor((state, level, pos) -> false)
					.mapColor(MapColor.COLOR_BROWN),
			true);

	public static final DeferredSupplier<Level11Concrete> LEVEL11_CONCRETE = register("level11_concrete",
			Level11Concrete::new,
			BlockBehaviour.Properties.of().strength(20).mapColor(MapColor.COLOR_GRAY), true);

	public static final DeferredSupplier<Level9EntryHouse> LEVEL9_ENTRY = register("level9_entry_house",
			Level9EntryHouse::new,
			BlockBehaviour.Properties.of().strength(40).noOcclusion().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY), true);

	public static final DeferredSupplier<FalseWheat> FALSE_WHEAT = register("false_wheat", FalseWheat::new,
			BlockBehaviour.Properties.of().strength(1).noOcclusion().noCollision().mapColor(MapColor.TERRACOTTA_ORANGE),
			true);

	public static final DeferredSupplier<StepVisibleBlock> STEP_VISIBLE = register("step_visible",
			StepVisibleBlock::new,
			BlockBehaviour.Properties.of().strength(-1, 3600000).noOcclusion().mapColor(MapColor.COLOR_BLACK), true);

	public static final DeferredSupplier<OverworldTransporter> OVERWORLD_TRANSPORTER = register("overworld_transporter",
			OverworldTransporter::new,
			BlockBehaviour.Properties.of().strength(80, 12).mapColor(MapColor.COLOR_MAGENTA), true);

	public static final DeferredSupplier<Block> NULL_BLOCK = register("null",
			Block::new,
			BlockBehaviour.Properties.of().strength(1).mapColor(MapColor.COLOR_BLACK), true);

	public static void RegisterModBlocks() {
		BoxysBackroomsCommon.BLOCKS.register();
	}
}