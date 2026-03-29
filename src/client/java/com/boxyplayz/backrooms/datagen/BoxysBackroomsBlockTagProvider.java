package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BoxysBackroomsBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public BoxysBackroomsBlockTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
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
	}

}
