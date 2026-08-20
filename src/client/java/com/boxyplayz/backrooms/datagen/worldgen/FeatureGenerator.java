package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.structures.ModStructures;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class FeatureGenerator extends FabricDynamicRegistryProvider {

	public FeatureGenerator(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "features";
	}

	@Override
	public void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
		entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
	}

	public static void configureFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		TreeConfiguration level9Entry = new TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.LEVEL9_ENTRY),
				new StraightTrunkPlacer(1, 0, 0),

				BlockStateProvider.simple(Blocks.AIR.defaultBlockState()),
				new BlobFoliagePlacer(ConstantInt.ZERO, ConstantInt.ZERO, 0),

				new TwoLayersFeatureSize(0, 0, 0))
				.belowTrunkProvider(BlockStateProvider.simple(Blocks.AIR))
				.build();

		context.register(ModStructures.LEVEL9_ENTRY_HOUSE_CONFIGURED_KEY,
				new ConfiguredFeature<>(Feature.TREE, level9Entry));
	}

	public static void placeFeatures(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		List<PlacementModifier> entryHouseModifiers = List.of(
				RarityFilter.onAverageOnceEvery(60), // spawns once every 10 chunks on average
				BiomeFilter.biome(),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_8_8);

		context.register(
				ModStructures.LEVEL9_ENTRY_HOUSE_PLACED_KEY,
				new PlacedFeature(
						configuredFeatures.getOrThrow(ModStructures.LEVEL9_ENTRY_HOUSE_CONFIGURED_KEY),
						entryHouseModifiers));

	}

}
