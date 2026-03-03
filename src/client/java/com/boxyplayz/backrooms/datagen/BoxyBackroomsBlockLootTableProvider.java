package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup.Provider;

public class BoxyBackroomsBlockLootTableProvider extends FabricBlockLootTableProvider {

	public BoxyBackroomsBlockLootTableProvider(FabricDataOutput dataOutput,
			CompletableFuture<Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		dropSelf(ModBlocks.LEVEL0_CARPET);
		dropSelf(ModBlocks.LEVEL0_CEILING_TILE);
		dropSelf(ModBlocks.ERRORSLATE);
		dropSelf(ModBlocks.OCEAN_TRANSPORTER);
		dropSelf(ModBlocks.LEVEL0_WALLPAPER);
	}
	
}
