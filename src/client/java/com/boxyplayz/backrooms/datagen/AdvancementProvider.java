package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class AdvancementProvider extends FabricAdvancementProvider {
	public Advancement.Builder generateAdvancement(ItemLike icon, String id, @Nullable Identifier background,
			Criterion<?> criterion) {
		return Advancement.Builder.advancement()
				.display(icon,
						Component.translatable("text.boxys_backrooms." + id + "_title"),
						Component.translatable("text.boxys_backrooms." + id + "_desc"),
						background,
						AdvancementType.TASK,
						true,
						false,
						true)
				.addCriterion(id, criterion);
	}

	public AdvancementProvider(FabricPackOutput output,
			CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@SuppressWarnings("unused")
	@Override
	public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {

		AdvancementHolder broken_place = generateAdvancement(Blocks.BLACK_CONCRETE, "broken_place",
				Identifier.withDefaultNamespace("block/black_concrete"),
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.BROKEN_DIMENSION))
				.save(consumer, BoxysBackrooms.MOD_ID + ":broken_place");

		AdvancementHolder theBackrooms = generateAdvancement(ModBlocks.LEVEL0_WALLPAPER, "backrooms",
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "block/level0_wallpaper"),
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL0_DIMENSION))
				.save(consumer, BoxysBackrooms.MOD_ID + ":the_backrooms");

		AdvancementHolder habitable = generateAdvancement(ModBlocks.GOTHIC_CONCRETE, "habitable", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL1_DIMENSION))
				.parent(theBackrooms).save(consumer, BoxysBackrooms.MOD_ID + ":habitable");

		AdvancementHolder gardenHab = generateAdvancement(Blocks.ROSE_BUSH, "garden_hab", null,
				PlayerTrigger.TriggerInstance
						.located(LocationPredicate.Builder
								.inBiome(wrapperLookup.getOrThrow(ModBiomes.Level1Biomes.GARDEN_BIOME))))
				.parent(habitable)
				.save(consumer, BoxysBackrooms.MOD_ID + ":garden_hab");

		AdvancementHolder pipeDream = generateAdvancement(ModBlocks.LEVEL2_PIPE, "pipe_dream", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL2_DIMENSION))
				.parent(habitable)
				.save(consumer, BoxysBackrooms.MOD_ID + ":pipe_dreams");

		AdvancementHolder elecStation = generateAdvancement(ModBlocks.ELECTRICAL_BRICKS, "elec", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL3_DIMENSION))
				.parent(pipeDream)
				.save(consumer, BoxysBackrooms.MOD_ID + ":elec_station");

		AdvancementHolder offices = generateAdvancement(ModBlocks.ELEVATOR, "office", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL4_DIMENSION))
				.parent(elecStation)
				.save(consumer, BoxysBackrooms.MOD_ID + ":office");

		AdvancementHolder hotel = generateAdvancement(ModBlocks.LEVEL5_ENTRY_TABLE, "hotel", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL5_DIMENSION))
				.parent(offices)
				.save(consumer, BoxysBackrooms.MOD_ID + ":hotel");

		AdvancementHolder darkness = generateAdvancement(Blocks.BLACK_CONCRETE, "darkness", null,
				ChangeDimensionTrigger.TriggerInstance
						.changedDimensionTo(ModDimensions.LEVEL6_DIMENSION))
				.parent(hotel)
				.save(consumer, BoxysBackrooms.MOD_ID + ":darkness");

		AdvancementHolder neverEndingOcean = generateAdvancement(Items.WATER_BUCKET, "ocean", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL7_DIMENSION))
				.parent(darkness)
				.save(consumer, BoxysBackrooms.MOD_ID + ":endless_ocean");

		AdvancementHolder oneWayCavern = generateAdvancement(ModBlocks.ERRORSLATE, "oneway", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL8_DIMENSION))
				.parent(neverEndingOcean)
				.save(consumer, BoxysBackrooms.MOD_ID + ":one_way_cavern");

		AdvancementHolder wrongWay = generateAdvancement(Blocks.OAK_LOG, "wrongway", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.PITFALLS_DIMENSION))
				.parent(theBackrooms)
				.save(consumer, BoxysBackrooms.MOD_ID + ":wrongway_pitfalls");

		AdvancementHolder ninetyFour = generateAdvancement(Blocks.GRASS_BLOCK, "level_94", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL94_DIMENSION))
				.parent(wrongWay)
				.save(consumer, BoxysBackrooms.MOD_ID + ":ninety_four");

		AdvancementHolder remodelling = generateAdvancement(ModBlocks.PREMIUM_CARPET, "remodelling", null,
				ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.LEVEL0_2_DIMENSION))
				.parent(theBackrooms)
				.save(consumer, BoxysBackrooms.MOD_ID + ":remodelling");

		AdvancementHolder negativeZone = generateAdvancement(ModBlocks.INFERIOR_WALLPAPER, "negativezone", null,
				ChangeDimensionTrigger.TriggerInstance
						.changedDimensionTo(ModDimensions.LEVEL_NEGATIVE_0_2_DIMENSION))
				.parent(remodelling)
				.save(consumer, BoxysBackrooms.MOD_ID + ":negativezone");

		AdvancementHolder funTime = generateAdvancement(ModBlocks.FUN_FLOOR, "fun", null,
				ChangeDimensionTrigger.TriggerInstance
						.changedDimensionTo(ModDimensions.LEVEL_FUN_DIMENSION))
				.parent(theBackrooms)
				.save(consumer, BoxysBackrooms.MOD_ID + ":fun");

		AdvancementHolder abyss = generateAdvancement(Blocks.OBSIDIAN, "abyss", null,
				ChangeDimensionTrigger.TriggerInstance
						.changedDimensionTo(ModDimensions.ABYSS_DIMENSION))
				.parent(broken_place)
				.save(consumer, BoxysBackrooms.MOD_ID + ":abyss");

	}

}
