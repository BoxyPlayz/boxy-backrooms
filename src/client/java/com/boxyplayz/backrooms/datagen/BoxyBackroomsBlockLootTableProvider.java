package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup.Provider;

public class BoxyBackroomsBlockLootTableProvider extends FabricBlockLootSubProvider {

	public BoxyBackroomsBlockLootTableProvider(FabricPackOutput dataOutput,
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
		dropSelf(ModBlocks.LEVEL0_CARPET_GLITCHED);
		dropSelf(ModBlocks.LEVEL1_CEILING_AQUILA);
		dropSelf(ModBlocks.LEVEL1_FLOOR_AQUILA);
		dropSelf(ModBlocks.LEVEL1_PILLAR_AQUILA);
	}

}
