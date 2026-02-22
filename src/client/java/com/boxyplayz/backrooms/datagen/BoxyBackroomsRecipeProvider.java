package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

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
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
				shaped(RecipeCategory.MISC, ModBlocks.OCEAN_TRANSPORTER.asItem())
				.pattern("XYX")
				.pattern("ABA")
				.pattern("XZX")
				.define('X', Items.AMETHYST_SHARD)
				.define('Y', Items.WATER_BUCKET)
				.define('A', Items.PISTON)
				.define('B', Items.FLINT_AND_STEEL)
				.define('Z', Items.CRYING_OBSIDIAN)
				.group("multi_bench")
				.unlockedBy(getHasName(Items.CRYING_OBSIDIAN), has(Items.CRYING_OBSIDIAN))
				.save(output);
			}
		};
	}

}
