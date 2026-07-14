package com.boxyplayz.backrooms.datagen.lang;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.enchantments.ModEnchantments;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;
import com.boxyplayz.backrooms.utils.Misc;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;

public class EnglishLangProvider extends FabricLanguageProvider {

	String ElevatorId = "elevator";

	public String getText(String id) {
		return "text." + BoxysBackrooms.MOD_ID + "." + id;
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
		translationBuilder.add(ModItems.GRAY_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.GREEN_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.RED_ALMOND_WATER, "Almond Water");
		translationBuilder.add(ModItems.ROYAL_RATION, "Royal Ration");
		translationBuilder.add(ModItems.NEON_WATER, "Neon Water");
		translationBuilder.add(ModItems.EMPTY_ALMOND_WATER, "Empty Water Bottle");

		// Egg
		translationBuilder.add(ModItems.SMILER_SPAWN_EGG, "Smiler Spawn Egg");
		translationBuilder.add(ModItems.WRETCH_SPAWN_EGG, "Wretched Spawn Egg");
		translationBuilder.add(ModItems.SKINSTEALER_SPAWN_EGG, "Skinstealer Spawn Egg");
		translationBuilder.add(ModItems.PARTYGOER_SPAWN_EGG, "Partygoer Spawn Egg");
		translationBuilder.add(ModItems.PARTYPOOPER_SPAWN_EGG, "Partypooper Spawn Egg");

		// Other Items
		translationBuilder.add(ModItems.FIRESALT_SHARD, "Firesalt Shard");
		translationBuilder.add(ModItems.SHADOW_DUST, "Shadow Dust");
		translationBuilder.add(ModItems.SMILER_REPELLANT, "Smiler Repellant");
		translationBuilder.add(ModItems.FIRESTEEL_ALLOY, "Firesteel Alloy");
		translationBuilder.add(ModItems.FIRESTEEL_SWORD, "Firesteel Sword");
		translationBuilder.add(ModItems.LIQUID_PAIN, "Liquid Pain");
		translationBuilder.add(ModBlocks.ELEVATOR, "Elevator");
		translationBuilder.add(ModItems.GRAY_KEY, "Gray Key");

		// Noclippable Blocks
		translationBuilder.add(ModBlocks.ERRORSLATE, "Errorslate");
		translationBuilder.add(ModBlocks.OCEAN_TRANSPORTER, "Aquatic Transporter");
		translationBuilder.add(ModBlocks.LEVEL0_WALLPAPER, "Wallpaper");
		translationBuilder.add(ModBlocks.LEVEL0_CARPET_GLITCHED, "Carpet?");

		// Regular Blocks
		translationBuilder.add(ModBlocks.PURE_GRASS, "Pure Grass");

		translationBuilder.add(ModBlocks.LEVEL0_CARPET, "Carpet");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_LIGHT, "Old Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL0_CEILING_TILE, "Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_CARPET, "Premium Carpet");
		translationBuilder.add(ModBlocks.PREMIUM_CEILING_TILE, "Premium Ceiling Tiles");
		translationBuilder.add(ModBlocks.PREMIUM_WALLPAPER, "Premium Wallpaper");
		translationBuilder.add(ModBlocks.INFERIOR_CARPET, "Worn Carpet");
		translationBuilder.add(ModBlocks.INFERIOR_CEILING_TILE, "Failed Ceiling Tiles");
		translationBuilder.add(ModBlocks.INFERIOR_WALLPAPER, "Old Wallpaper");

		translationBuilder.add(ModBlocks.LEVEL1_CEILING_AQUILA, "Aquila Sector Ceiling");
		translationBuilder.add(ModBlocks.LEVEL1_FLOOR_AQUILA, "Aquila Sector Floor");
		translationBuilder.add(ModBlocks.LEVEL1_CEILING_LIGHT, "Aquila Ceiling Light");
		translationBuilder.add(ModBlocks.LEVEL1_PILLAR_AQUILA, "Aquila Pillar");
		translationBuilder.add(ModBlocks.LEVEL1_WALL_GILD, "Gilded Sector Wall");
		translationBuilder.add(ModBlocks.GOTHIC_CONCRETE, "Gothic Concrete");
		translationBuilder.add(ModBlocks.GARDEN_CONCRETE, "Garden Concrete");
		translationBuilder.add(ModBlocks.AGED_CONCRETE, "Aged Concrete");

		translationBuilder.add(ModBlocks.FUN_GREEN, "Fun Green Wall");
		translationBuilder.add(ModBlocks.FUN_PINK, "Fun Pink Wall");
		translationBuilder.add(ModBlocks.FUN_PURPLE, "Fun Purple Wall");
		translationBuilder.add(ModBlocks.FUN_YELLOW, "Fun Yellow Wall");
		translationBuilder.add(ModBlocks.FUN_FLOOR, "Fun Floor");
		translationBuilder.add(ModBlocks.BLACK_TRAMPOLINE, "Black Trampoline");

		translationBuilder.add(ModBlocks.PROMISED_CEILING_LIGHT, "Promised Ceiling Light");
		translationBuilder.add(ModBlocks.PROMISED_CARPET, "Promised Carpet");
		translationBuilder.add(ModBlocks.PROMISED_CEILING_TILE, "Promised Ceiling Tile");
		translationBuilder.add(ModBlocks.PROMISED_WALLPAPER, "Promised Wallpaper");
		translationBuilder.add(ModBlocks.PROMISED_GATE, "Promised Gateway");

		translationBuilder.add(ModBlocks.PURE_BLUE, "Pure Blue");

		translationBuilder.add(ModBlocks.POWER_OUTLET_BLOCK, "Power Outlet");
		translationBuilder.add(ModBlocks.LEVEL3_CEILING_LIGHT, "Level 3 Ceiling Light");
		translationBuilder.add(ModBlocks.ELECTRICAL_BRICKS, "Electrical Bricks");

		translationBuilder.add(ModBlocks.BLENDER, "Blender");
		translationBuilder.add(ModBlocks.WATER_FOUNTAIN, "Water Fountain");

		translationBuilder.add(ModBlocks.LEVEL2_PIPE, "Level 2 Pipe");
		translationBuilder.add(ModBlocks.LEVEL2_DOOR, "Level 2 Door");
		translationBuilder.add(ModBlocks.LEVEL2_FIRE_EXIT, "Level 2 Fire Exit");

		translationBuilder.add(ModBlocks.LEVEL4_CARPET, "Level 4 Carpet");
		translationBuilder.add(ModBlocks.PURE_WHITE_GLOW, "Pure White Glow");
		translationBuilder.add(ModBlocks.LEVEL5_ENTRY_TABLE, "Level 5 Entry Table");

		translationBuilder.add(ModBlocks.LEVEL5_CARPET, "Level 5 Carpet");
		translationBuilder.add(ModBlocks.LEVEL6_ENTRY, "Level 6 Entry Point");

		// Loot Blocks
		translationBuilder.add(ModBlocks.LEVEL1_CRATE, "Crate");
		translationBuilder.add(ModBlocks.FUN_CRATE, "Fun Crate =)");

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

		translationBuilder.add(getText("no_title"), "TURN BACK NOW");
		translationBuilder.add(getText("no_desc"), "Read 'TO ENTER THE ROOMS:' on the modrinth page.");

		// Tags
		translationBuilder.add(ModTags.NOCLIPPABLES, "Noclippable Blocks");
		translationBuilder.add(ModTags.ALMOND_WATERS, "Almond Waters");
		translationBuilder.add(ModTags.LIGHT_ITEMS, "Light Blocks");
		translationBuilder.add(ModTags.FIRESTEEL_REPAIR_ITEMS, "Firesteel Repair Items");

		// Tooltips
		translationBuilder.add(getTooltip(ModItems.GRAY_ALMOND_WATER), "Tastes like almonds.");
		translationBuilder.add(getTooltip(ModItems.GREEN_ALMOND_WATER), "Tastes sweet!");
		translationBuilder.add(getTooltip(ModItems.RED_ALMOND_WATER), "Tastes bitter.");
		translationBuilder.add(getTooltip(ModItems.FIRESTEEL_SWORD), "Fire, Magic, and Steel.");
		translationBuilder.add(getTooltip(ModItems.FIRESTEEL_ALLOY), "Fire and Steel.");
		translationBuilder.add(getTooltip(ModItems.GRAY_KEY), "Useless, for now.");

		// Entities
		translationBuilder.add(ModEntities.SMILER, "Smiler");
		translationBuilder.add(ModEntities.SKINSTEALER, "Skin Stealer");
		translationBuilder.add(ModEntities.WRETCH, "Wretch");
		translationBuilder.add(ModEntities.PARTYGOER, "Partygoer");
		translationBuilder.add(ModEntities.PARTYPOOPER, "Partypooper");
		translationBuilder.add(ModEntities.BALLOON, "Party Balloon");

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
	}
}