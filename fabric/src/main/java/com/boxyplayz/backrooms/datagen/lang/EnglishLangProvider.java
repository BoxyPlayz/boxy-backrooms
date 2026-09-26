package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackroomsFabric;
import com.boxyplayz.backrooms.block.FabricBlocks;
import com.boxyplayz.backrooms.common.Misc;
import com.boxyplayz.backrooms.common.ModCreativeTabs;
import com.boxyplayz.backrooms.common.ModEnchantments;
import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.effect.ModEffects;
import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;

public class EnglishLangProvider extends FabricLanguageProvider {

	String ElevatorId = "elevator";

	public String getText(String id) {
		return "text." + BoxysBackroomsFabric.MOD_ID + "." + id;
	}

	public String getTooltip(Item item) {
		return item.getDescriptionId() + ".tooltip";
	}

	public String getDeathMsg(String id) {
		return "death.attack." + id;
	}

	public EnglishLangProvider(FabricPackOutput dataOutput,
			CompletableFuture<HolderLookup.Provider> registryLookup) {
		// Specifying en_us is optional, as it's the default language code
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {

		// Metadata
		translationBuilder.add(ModCreativeTabs.BACKROOMS_ITEM_GROUP_KEY, "Boxy's Backrooms");

		// Food
		translationBuilder.add(ModItems.GRAY_ALMOND_WATER.get(), "Almond Water");
		translationBuilder.add(ModItems.GREEN_ALMOND_WATER.get(), "Almond Water");
		translationBuilder.add(ModItems.RED_ALMOND_WATER.get(), "Almond Water");
		translationBuilder.add(ModItems.ROYAL_RATION.get(), "Royal Ration");
		translationBuilder.add(ModItems.NEON_WATER.get(), "Neon Water");
		translationBuilder.add(ModItems.EMPTY_ALMOND_WATER.get(), "Empty Water Bottle");

		// Egg
		translationBuilder.add(ModItems.SMILER_SPAWN_EGG.get(), "Smiler Spawn Egg");
		translationBuilder.add(ModItems.WRETCH_SPAWN_EGG.get(), "Wretched Spawn Egg");
		translationBuilder.add(ModItems.SKINSTEALER_SPAWN_EGG.get(), "Skinstealer Spawn Egg");
		translationBuilder.add(ModItems.PARTYGOER_SPAWN_EGG.get(), "Partygoer Spawn Egg");
		translationBuilder.add(ModItems.PARTYPOOPER_SPAWN_EGG.get(), "Partypooper Spawn Egg");
		translationBuilder.add(ModItems.NEIGHBORHOOD_WATCH_SPAWN_EGG.get(), "Neighborhood Watch Spawn Egg");

		// Other Items
		translationBuilder.add(ModItems.FIRESALT_SHARD.get(), "Firesalt Shard");
		translationBuilder.add(ModItems.SHADOW_DUST.get(), "Shadow Dust");
		translationBuilder.add(ModItems.SMILER_REPELLANT.get(), "Smiler Repellant");
		translationBuilder.add(ModItems.FIRESTEEL_ALLOY.get(), "Firesteel Alloy");
		translationBuilder.add(ModItems.FIRESTEEL_SWORD.get(), "Firesteel Sword");
		translationBuilder.add(ModItems.LIQUID_PAIN.get(), "Liquid Pain");
		translationBuilder.add(ModBlocks.ELEVATOR.get(), "Elevator");
		translationBuilder.add(ModItems.GRAY_KEY.get(), "Gray Key");

		// Noclippable Blocks
		translationBuilder.add(ModBlocks.ERRORSLATE.get(), "Errorslate");
		translationBuilder.add(ModBlocks.OCEAN_TRANSPORTER.get(), "Aquatic Transporter");
		translationBuilder.add(ModBlocks.LEVEL0_WALLPAPER.get(), "Wallpaper");
		translationBuilder.add(ModBlocks.LEVEL0_CARPET_GLITCHED.get(), "Carpet?");

		// Regular Blocks
		translationBuilder.add(ModBlocks.PURE_GRASS.get(), "Pure Grass");

		translationBuilder.add(ModBlocks.LEVEL0_CARPET.get(), "Carpet");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_LIGHT.get(), "Old Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_TILE.get(), "Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_CARPET.get(), "Premium Carpet");
		translationBuilder.add(ModBlocks.PREMIUM_CEILING_TILE.get(), "Premium Ceiling Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_WALLPAPER.get(), "Premium Wallpaper");
		translationBuilder.add(ModBlocks.INFERIOR_CARPET.get(), "Worn Carpet");
		translationBuilder.add(ModBlocks.INFERIOR_CEILING_TILE.get(), "Failed Ceiling Tiles");
		translationBuilder.add(ModBlocks.INFERIOR_WALLPAPER.get(), "Old Wallpaper");

		translationBuilder.add(ModBlocks.LEVEL1_CEILING_AQUILA.get(), "Aquila Sector Ceiling");
		translationBuilder.add(ModBlocks.LEVEL1_FLOOR_AQUILA.get(), "Aquila Sector Floor");
		translationBuilder.add(ModBlocks.LEVEL1_CEILING_LIGHT.get(), "Aquila Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL1_PILLAR_AQUILA.get(), "Aquila Pillar");
		translationBuilder.add(ModBlocks.LEVEL1_WALL_GILD.get(), "Gilded Sector Wall");
		translationBuilder.add(ModBlocks.GOTHIC_CONCRETE.get(), "Gothic Concrete");
		translationBuilder.add(ModBlocks.GARDEN_CONCRETE.get(), "Garden Concrete");
		translationBuilder.add(ModBlocks.AGED_CONCRETE.get(), "Aged Concrete");

		translationBuilder.add(ModBlocks.FUN_GREEN.get(), "Fun Green Wall");
		translationBuilder.add(ModBlocks.FUN_PINK.get(), "Fun Pink Wall");
		translationBuilder.add(ModBlocks.FUN_PURPLE.get(), "Fun Purple Wall");
		translationBuilder.add(ModBlocks.FUN_YELLOW.get(), "Fun Yellow Wall");
		translationBuilder.add(ModBlocks.FUN_FLOOR.get(), "Fun Floor");
		translationBuilder.add(ModBlocks.BLACK_TRAMPOLINE.get(), "Black Trampoline");

		translationBuilder.add(ModBlocks.PROMISED_CEILING_LIGHT.get(), "Promised Ceiling Light");
		translationBuilder.add(ModBlocks.PROMISED_CARPET.get(), "Promised Carpet");
		translationBuilder.add(ModBlocks.PROMISED_CEILING_TILE.get(), "Promised Ceiling Tile");
		translationBuilder.add(ModBlocks.PROMISED_WALLPAPER.get(), "Promised Wallpaper");
		translationBuilder.add(ModBlocks.PROMISED_GATE.get(), "Promised Gateway");

		translationBuilder.add(ModBlocks.PURE_BLUE.get(), "Pure Blue");

		translationBuilder.add(FabricBlocks.POWER_OUTLET_BLOCK, "Power Outlet");
		translationBuilder.add(ModBlocks.LEVEL3_CEILING_LIGHT.get(), "Level 3 Ceiling Light");
		translationBuilder.add(ModBlocks.ELECTRICAL_BRICKS.get(), "Electrical Bricks");

		translationBuilder.add(ModBlocks.BLENDER.get(), "Blender");
		translationBuilder.add(ModBlocks.WATER_FOUNTAIN.get(), "Water Fountain");

		translationBuilder.add(ModBlocks.LEVEL2_PIPE.get(), "Level 2 Pipe");
		translationBuilder.add(ModBlocks.LEVEL2_DOOR.get(), "Level 2 Door");
		translationBuilder.add(ModBlocks.LEVEL2_FIRE_EXIT.get(), "Level 2 Fire Exit");

		translationBuilder.add(ModBlocks.LEVEL4_CARPET.get(), "Level 4 Carpet");
		translationBuilder.add(ModBlocks.PURE_WHITE_GLOW.get(), "Pure White Glow");
		translationBuilder.add(ModBlocks.LEVEL5_ENTRY_TABLE.get(), "Level 5 Entry Table");

		translationBuilder.add(ModBlocks.LEVEL5_CARPET.get(), "Level 5 Carpet");
		translationBuilder.add(ModBlocks.LEVEL6_ENTRY.get(), "Level 6 Entry Point");

		translationBuilder.add(ModBlocks.LEVEL11_CONCRETE.get(), "Level 11 Concrete");

		translationBuilder.add(ModBlocks.FALSE_WHEAT.get(), "False Wheat");

		translationBuilder.add(ModBlocks.STEP_VISIBLE.get(), "Semi Visible Step");

		translationBuilder.add(ModBlocks.LEVEL9_ENTRY.get(), "Level 9 Entry House");

		// Loot Blocks
		translationBuilder.add(ModBlocks.LEVEL1_CRATE.get(), "Crate");
		translationBuilder.add(ModBlocks.FUN_CRATE.get(), "Fun Crate =)");

		// Advancements
		translationBuilder.add(getText("oneway_title"), "It's a cave?");
		translationBuilder.add(getText("oneway_desc"), "There are no halls here.");

		translationBuilder.add(getText("backrooms_title"), "The Backrooms");
		translationBuilder.add(getText("backrooms_desc"), "Enter the endless hallways of the Backrooms");

		translationBuilder.add(getText("ocean_title"), "Endless Ocean");
		translationBuilder.add(getText("ocean_desc"), "There's really no end.");

		translationBuilder.add(getText("level_94_title"), "Rolling Hills");
		translationBuilder.add(getText("level_94_desc"), "Peaceful..?");

		translationBuilder.add(getText("wrongway_title"), "Wrong Direction");
		translationBuilder.add(getText("wrongway_desc"), "Take the wrong way.");

		translationBuilder.add(getText("remodelling_title"), "Remodelled Mess");
		translationBuilder.add(getText("remodelling_desc"), "This can't be intentional.");

		translationBuilder.add(getText("negativezone_title"), "Subzero");
		translationBuilder.add(getText("negativezone_desc"), "Find the negative levels.");

		translationBuilder.add(getText("fountain_title"), "Replenished Water");
		translationBuilder.add(getText("fountain_desc"), "Use a water fountain");

		translationBuilder.add(getText("darkness_title"), "Pitch Black");
		translationBuilder.add(getText("darkness_desc"), "You can find no light on level 6.");

		translationBuilder.add(getText("fun_title"), "Level Fun");
		translationBuilder.add(getText("fun_desc"), "=)");

		translationBuilder.add(getText("habitable_title"), "Habitable Zone");
		translationBuilder.add(getText("habitable_desc"), "Finally, food!");

		translationBuilder.add(getText("pipe_dream_title"), "Pipe Dreams");
		translationBuilder.add(getText("pipe_dream_desc"), "This is stinky.");

		translationBuilder.add(getText("elec_title"), "Electrical Station");
		translationBuilder.add(getText("elec_desc"), "I HAVE WIFI HERE!!!!");

		translationBuilder.add(getText("garden_hab_title"), "The Garden");
		translationBuilder.add(getText("garden_hab_desc"), "I have a bad feeling about this...");

		translationBuilder.add(getText("office_title"), "The Abandoned Offices");
		translationBuilder.add(getText("office_desc"), "Temporary Safe Place");

		translationBuilder.add(getText("hotel_title"), "The Hotel");
		translationBuilder.add(getText("hotel_desc"), "Fancy!");

		translationBuilder.add(getText("broken_place_title"), "NULL");
		translationBuilder.add(getText("broken_place_desc"),
				"this, is bad.");

		translationBuilder.add(getText("abyss_title"), "The Abyss");
		translationBuilder.add(getText("abyss_desc"), "Your goal wasn't the rooms, was it?");

		translationBuilder.add(getText("neighbors_title"), "You're neighbors!");
		translationBuilder.add(getText("neighbors_desc"), "Just dash away from them CALMLY.");

		translationBuilder.add(getText("huit_title"), "A french eight");
		translationBuilder.add(getText("huit_desc"), "Did you get the joke?");

		translationBuilder.add(getText("big_city_title"), "Infinite City");
		translationBuilder.add(getText("big_city_desc"),
				"There's lots of resources, maybe you could become a big shot here?");

		// Tags
		translationBuilder.add(ModTags.NOCLIPPABLES, "Noclippable Blocks");
		translationBuilder.add(ModTags.ALMOND_WATERS, "Almond Waters");
		translationBuilder.add(ModTags.LIGHT_ITEMS, "Light Blocks");
		translationBuilder.add(ModTags.FIRESTEEL_REPAIR_ITEMS, "Firesteel Repair Items");
		translationBuilder.add(ModTags.FIRE_ATTACKS, "Fire Attacks");
		translationBuilder.add(ModTags.ANOMALY_DAMAGE, "Anomalous Attacks");
		translationBuilder.add(ModTags.FUN_BLOCKS, "Fun Blocks");
		translationBuilder.add(ModTags.DASH_ENABLED, "Dash Enabled");
		translationBuilder.add(ModTags.LARGE_JUMP, "Large Jump");

		// Tooltips
		translationBuilder.add(getTooltip(ModItems.GRAY_ALMOND_WATER.get()), "Tastes like almonds.");
		translationBuilder.add(getTooltip(ModItems.GREEN_ALMOND_WATER.get()), "Tastes sweet!");
		translationBuilder.add(getTooltip(ModItems.RED_ALMOND_WATER.get()), "Tastes bitter.");
		translationBuilder.add(getTooltip(ModItems.NEON_WATER.get()), "Tastes like power.");
		translationBuilder.add(getTooltip(ModItems.FIRESTEEL_SWORD.get()), "Fire, Magic, and Steel.");
		translationBuilder.add(getTooltip(ModItems.FIRESTEEL_ALLOY.get()), "Fire and Steel.");
		translationBuilder.add(getTooltip(ModItems.GRAY_KEY.get()), "You feel a shift in your world.");

		// Entities
		translationBuilder.add(ModEntities.SMILER.get(), "Smiler");
		translationBuilder.add(ModEntities.SKINSTEALER.get(), "Skin Stealer");
		translationBuilder.add(ModEntities.WRETCH.get(), "Wretch");
		translationBuilder.add(ModEntities.PARTYGOER.get(), "Partygoer");
		translationBuilder.add(ModEntities.PARTYPOOPER.get(), "Partypooper");
		translationBuilder.add(ModEntities.BALLOON.get(), "Party Balloon");
		translationBuilder.add(ModEntities.NEIGHBORHOOD_WATCH.get(), "The Neighborhood Watch");

		// Effects
		translationBuilder.add(ModEffects.GARDENERS_PAIN.value(), "Gardener's Pain");
		translationBuilder.add(ModEffects.WRETCHED_CYCLE.value(), "The Wretched Cycle");

		// Enchantments
		translationBuilder.addEnchantment(ModEnchantments.ANOMALOUS_PROTECTION, "Anomalous Protection");
		translationBuilder.add(
				Util.makeDescriptionId("enchantment", ModEnchantments.ANOMALOUS_PROTECTION.identifier()) + ".desc",
				"Defense from attacks that do not belong");

		// Damage Type
		translationBuilder.add(getDeathMsg("anomaly_damage"), "You died.");

		// Elevators
		translationBuilder.add(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL1.name()), "One");
		translationBuilder.add(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL2.name()), "Two");
		translationBuilder.add(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL3.name()), "Three");
		translationBuilder.add(Misc.getElevatorLangId(Misc.ElevatorDestination.LEVEL4.name()), "Four");
		translationBuilder.add(Misc.getElevatorLangId(Misc.ElevatorDestination.SHADE_GRAY.name()), "TH3 SH4DY GR4Y");

		// Other
		translationBuilder.add(getText("level8bed"), "You may not rest here. THEY are nearby.");
		translationBuilder.add("generator.boxys_backrooms.city", "City Life");

		translationBuilder.add("key.category.boxys_backrooms.backrooms_keybinds", "Boxy's Backrooms");
		translationBuilder.add("key.boxys_backrooms.dash", "Leap");
	}
}