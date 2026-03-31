package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.level.block.Blocks;

public class BoxysBackroomsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public BoxysBackroomsItemTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		valueLookupBuilder(ModTags.NOCLIPPABLES)
				.add(ModBlocks.ERRORSLATE.asItem())
				.add(ModBlocks.LEVEL0_WALLPAPER.asItem())
				.add(ModBlocks.LEVEL0_CARPET.asItem())
				.add(ModBlocks.PREMIUM_CARPET.asItem())
				.add(ModBlocks.INFERIOR_CARPET.asItem())
				.add(ModBlocks.LEVEL0_CARPET_GLITCHED.asItem())
				.add(ModBlocks.OCEAN_TRANSPORTER.asItem());

		valueLookupBuilder(ModTags.ALMOND_WATERS)
				.add(ModItems.GRAY_ALMOND_WATER)
				.add(ModItems.GREEN_ALMOND_WATER)
				.add(ModItems.RED_ALMOND_WATER);

		valueLookupBuilder(ModTags.LIGHT_BLOCKS)
				.add(Blocks.TORCH.asItem())
				.add(Blocks.LANTERN.asItem(), Blocks.SOUL_LANTERN.asItem())
				.addAll(Blocks.COPPER_LANTERN.asList().stream().map(block -> {
					return block.asItem();
				}))
				.add(Blocks.SOUL_TORCH.asItem())
				.add(Blocks.COPPER_TORCH.asItem())
				.setReplace(false);
	}

}
