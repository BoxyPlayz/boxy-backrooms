package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.dimension.ModDimensions;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class BoxyBackroomsAdvancementProvider extends FabricAdvancementProvider {
	public BoxyBackroomsAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
		AdvancementHolder oneWayCavern = Advancement.Builder.advancement()
		.display(
				ModBlocks.ERRORSLATE.asItem(), // The display icon
				Component.translatable("text.boxys_backrooms.oneway_title"), // The title
				Component.translatable("text.boxys_backrooms.oneway_desc"), // The description
				Identifier.withDefaultNamespace("textures/block/errorslate_side.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
				AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
				true, // Show the toast when completing it
				true, // Announce it to chat
				false // Hide it in the advancement tab until it's achieved
		)
		.addCriterion("level_eight_cavern", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL8))
		.save(consumer, BoxysBackrooms.MOD_ID + ":one_way_cavern");
	}

}
