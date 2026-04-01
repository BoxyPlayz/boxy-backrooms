package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class BoxyBackroomsRecipeProvider extends FabricRecipeProvider {

	public BoxyBackroomsRecipeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "BoxyBackroomsRecipeProvider";
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
		return new RecipeProvider(registryLookup, output) {
			@Override
			public void buildRecipes() {
				@SuppressWarnings("unused")
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

				SmithingTransformRecipeBuilder
						.smithing(Ingredient.of(Items.MAGMA_CREAM),
								Ingredient.of(ModItems.FIRESALT_SHARD),
								Ingredient.of(Items.IRON_INGOT),
								RecipeCategory.MISC,
								ModItems.FIRESTEEL_ALLOY)
						.unlocks(getHasName(ModItems.FIRESALT_SHARD), has(ModItems.FIRESALT_SHARD))
						.save(output, getItemName(ModItems.FIRESTEEL_ALLOY) + "_smithing");

				SmithingTransformRecipeBuilder
						.smithing(Ingredient.of(Items.BLAZE_POWDER),
								Ingredient.of(Items.GOLDEN_SWORD),
								Ingredient.of(ModItems.FIRESTEEL_ALLOY),
								RecipeCategory.COMBAT,
								ModItems.FIRESTEEL_SWORD)
						.unlocks(getHasName(ModItems.FIRESTEEL_ALLOY), has(ModItems.FIRESTEEL_ALLOY))
						.save(output, getItemName(ModItems.FIRESTEEL_SWORD) + "_smithing");

				shapeless(RecipeCategory.MISC, ModBlocks.LEVEL0_WALLPAPER)
						.requires(Items.YELLOW_CONCRETE)
						.requires(Items.AMETHYST_SHARD, 4)
						.requires(Items.ENDER_PEARL)
						.unlockedBy(getHasName(Items.YELLOW_CONCRETE), has(Items.YELLOW_CONCRETE))
						.save(output);

				SimpleCookingRecipeBuilder
						.blasting(Ingredient.of(Items.AMETHYST_SHARD), RecipeCategory.COMBAT, CookingBookCategory.MISC,
								ModItems.FIRESALT_SHARD, 0.1f, 100)
						.unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
						.save(output);

				shapeless(RecipeCategory.MISC, ModItems.SMILER_REPELLANT)
						.requires(ModItems.SHADOW_DUST)
						.requires(ModTags.ALMOND_WATERS)
						.unlockedBy(getHasName(ModItems.SHADOW_DUST), has(ModItems.SHADOW_DUST))
						.save(output);

				shapeless(RecipeCategory.MISC, Blocks.BARREL.asItem(), 8)
						.requires(ModBlocks.LEVEL1_CRATE.asItem())
						.requires(Items.COPPER_NUGGET)
						.unlockedBy(getHasName(ModBlocks.LEVEL1_CRATE.asItem()), has(ModBlocks.LEVEL1_CRATE.asItem()))
						.save(output);

				shapeless(RecipeCategory.MISC, Blocks.CHEST.asItem(), 8)
						.requires(ModBlocks.LEVEL1_CRATE.asItem())
						.requires(Items.IRON_NUGGET)
						.unlockedBy(getHasName(ModBlocks.LEVEL1_CRATE.asItem()), has(ModBlocks.LEVEL1_CRATE.asItem()))
						.save(output);

				shapeless(RecipeCategory.DECORATIONS, ModBlocks.PURE_GRASS)
						.requires(Blocks.GRASS_BLOCK.asItem(), 4)
						.requires(Blocks.SHORT_GRASS.asItem(), 1)
						.requires(Items.BONE_MEAL, 4)
						.unlockedBy(getHasName(Blocks.GRASS_BLOCK.asItem()), has(Blocks.GRASS_BLOCK.asItem()))
						.save(output);

			}
		};
	}

}
