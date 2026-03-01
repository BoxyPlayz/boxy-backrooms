package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BoxysBackroomsBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public BoxysBackroomsBlockTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
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
		.setReplace(false);
	}

}
