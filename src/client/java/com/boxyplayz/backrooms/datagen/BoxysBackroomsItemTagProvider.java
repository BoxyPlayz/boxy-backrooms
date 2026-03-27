package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

public class BoxysBackroomsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public BoxysBackroomsItemTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static final TagKey<Item> NoClippables = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "noclippable"));

	public static final TagKey<Item> AlmondWaters = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "almond_waters"));

	@Override
	protected void addTags(Provider wrapperLookup) {
		valueLookupBuilder(NoClippables)
				.add(ModBlocks.ERRORSLATE.asItem())
				.add(ModBlocks.LEVEL0_WALLPAPER.asItem())
				.add(ModBlocks.LEVEL0_CARPET_GLITCHED.asItem())
				.add(ModBlocks.OCEAN_TRANSPORTER.asItem());

		valueLookupBuilder(AlmondWaters)
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
