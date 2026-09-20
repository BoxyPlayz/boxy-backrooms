package com.boxyplayz.backrooms.datagen.tags;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.block.ModBlocks;
import com.boxyplayz.backrooms.common.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		valueLookupBuilder(ModTags.NOCLIPPABLES)
				.add(ModBlocks.ERRORSLATE.get().asItem())
				.add(ModBlocks.LEVEL0_WALLPAPER.get().asItem())
				.add(ModBlocks.LEVEL0_CARPET.get().asItem())
				.add(ModBlocks.PREMIUM_CARPET.get().asItem())
				.add(ModBlocks.INFERIOR_CARPET.get().asItem())
				.add(ModBlocks.LEVEL0_CARPET_GLITCHED.get().asItem())
				.add(ModBlocks.OCEAN_TRANSPORTER.get().asItem());

		valueLookupBuilder(ModTags.ALMOND_WATERS)
				.add(ModItems.GRAY_ALMOND_WATER.get())
				.add(ModItems.GREEN_ALMOND_WATER.get())
				.add(ModItems.RED_ALMOND_WATER.get())
				.setReplace(false);

		valueLookupBuilder(ModTags.FIRESTEEL_REPAIR_ITEMS)
				.add(ModItems.FIRESALT_SHARD.get())
				.add(ModItems.FIRESTEEL_ALLOY.get())
				.add(Items.GOLD_INGOT)
				.add(Items.IRON_INGOT);

		valueLookupBuilder(ModTags.LIGHT_ITEMS)
				.add(Blocks.TORCH.asItem())
				.add(Blocks.LANTERN.asItem(), Blocks.SOUL_LANTERN.asItem())
				.addAll(Blocks.COPPER_LANTERN.asList().stream().map(block -> {
					return block.asItem();
				}))
				.add(Blocks.SOUL_TORCH.asItem())
				.add(Blocks.COPPER_TORCH.asItem())
				.setReplace(false);

		valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
				.add(ModItems.FIRESTEEL_SWORD.get());
		valueLookupBuilder(ItemTags.SWORDS)
				.add(ModItems.FIRESTEEL_SWORD.get());
		valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
				.add(ModItems.FIRESTEEL_SWORD.get());
	}

}
