package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class BoxyBackroomsRecipeProvider extends FabricRecipeProvider {

	public BoxyBackroomsRecipeProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
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
				shapeless(RecipeCategory.MISC, ModBlocks.LEVEL0_WALLPAPER)
						.requires(Items.YELLOW_CONCRETE)
						.requires(Items.AMETHYST_SHARD, 4)
						.requires(Items.ENDER_PEARL)
						.unlockedBy(getHasName(Items.YELLOW_CONCRETE), has(Items.YELLOW_CONCRETE))
						.save(output);

				SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.AMETHYST_SHARD), RecipeCategory.COMBAT,
						ModItems.FIRESALT_SHARD, 0.1f, 100)
						.unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
						.save(output);
				shapeless(RecipeCategory.MISC, ModItems.SMILER_REPELLANT)
						.requires(ModItems.SHADOW_DUST)
						.requires(BoxysBackroomsItemTagProvider.AlmondWaters)
						.unlockedBy(getHasName(ModItems.SHADOW_DUST), has(ModItems.SHADOW_DUST))
						.save(output);
			}
		};
	}

}
