package com.boxyplayz.backrooms.datagen.tags;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public BlockTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(ModBlocks.OCEAN_TRANSPORTER)
				.add(ModBlocks.ERRORSLATE)
				.setReplace(false);

		valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
				.add(ModBlocks.LEVEL0_WALLPAPER)
				.add(ModBlocks.LEVEL1_CRATE)
				.setReplace(false);

		valueLookupBuilder(BlockTags.GRASS_BLOCKS)
				.add(ModBlocks.PURE_GRASS)
				.setReplace(false);

		valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(ModBlocks.PURE_GRASS)
				.setReplace(false);

		valueLookupBuilder(ModTags.FUN_BLOCKS)
				.add(ModBlocks.FUN_GREEN)
				.add(ModBlocks.FUN_PINK)
				.add(ModBlocks.FUN_PURPLE)
				.add(ModBlocks.FUN_YELLOW)
				.setReplace(false);
	}

}
