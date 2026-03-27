package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class BoxyBackroomsAdvancementProvider extends FabricAdvancementProvider {
	public BoxyBackroomsAdvancementProvider(FabricPackOutput output,
			CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@SuppressWarnings("unused")
	@Override
	public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {

		AdvancementHolder theBackrooms = Advancement.Builder.advancement()
				.display(
						ModBlocks.LEVEL0_WALLPAPER.asItem(), // The display icon
						Component.translatable("text.boxys_backrooms.backrooms_title"), // The title
						Component.translatable("text.boxys_backrooms.backrooms_desc"), // The description
						Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/level0_wallpaper"), // Background
						// image
						// for
						// the
						// tab
						// in
						// the
						// advancements
						// page,
						// if
						// this
						// is a
						// root
						// advancement
						// (has
						// no
						// parent)
						AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
						true, // Show the toast when completing it
						false, // Announce it to chat
						true // Hide it in the advancement tab until it's achieved
				)
				.addCriterion("level_one_maze",
						ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL0_DIMENSION))
				.save(consumer, BoxysBackrooms.MOD_ID + ":the_backrooms");

		AdvancementHolder neverEndingOcean = Advancement.Builder.advancement()
				.display(
						Items.WATER_BUCKET, // The display icon
						Component.translatable("text.boxys_backrooms.ocean_title"), // The title
						Component.translatable("text.boxys_backrooms.ocean_desc"), // The description
						null, // Background image for the tab in the advancements page, if this is a root
								// advancement (has no parent)
						AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
						true, // Show the toast when completing it
						false, // Announce it to chat
						true // Hide it in the advancement tab until it's achieved
				)
				.addCriterion("level_7_ocean",
						ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL7_DIMENSION))
				.parent(theBackrooms)
				.save(consumer, BoxysBackrooms.MOD_ID + ":endless_ocean");

		AdvancementHolder oneWayCavern = Advancement.Builder.advancement()
				.display(
						ModBlocks.ERRORSLATE.asItem(), // The display icon
						Component.translatable("text.boxys_backrooms.oneway_title"), // The title
						Component.translatable("text.boxys_backrooms.oneway_desc"), // The description
						null, // Background image for the tab in the advancements page, if this is a root
								// advancement (has no parent)
						AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
						true, // Show the toast when completing it
						false, // Announce it to chat
						true // Hide it in the advancement tab until it's achieved
				)
				.addCriterion("level_eight_cavern",
						ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL8_DIMENSION))
				.parent(neverEndingOcean)
				.save(consumer, BoxysBackrooms.MOD_ID + ":one_way_cavern");

		AdvancementHolder ninetyFour = Advancement.Builder.advancement()
				.display(
						Blocks.GRASS_BLOCK.asItem(), // The display icon
						Component.translatable("text.boxys_backrooms.94_title"), // The title
						Component.translatable("text.boxys_backrooms.94_desc"), // The description
						null, // Background image for the tab in the advancements page, if this is a root
								// advancement (has no parent)
						AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
						true, // Show the toast when completing it
						false, // Announce it to chat
						true // Hide it in the advancement tab until it's achieved
				)
				.addCriterion("level_94",
						ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL94_DIMENSION))
				.parent(theBackrooms)
				.save(consumer, BoxysBackrooms.MOD_ID + ":ninety_four");

	}

}
