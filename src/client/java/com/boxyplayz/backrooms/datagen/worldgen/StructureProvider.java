package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.structures.ModStructures;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

public class StructureProvider extends FabricDynamicRegistryProvider {

	public StructureProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "strucures";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE_SET));
		entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE));
		entries.addAll(registries.lookupOrThrow(Registries.TEMPLATE_POOL));
	}

	public static void structBoot(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

		context.register(ModStructures.LEVEL7_ACCESS_STRUCTURE, new JigsawStructure(
				new StructureSettings.Builder(
						HolderSet.direct(biomes.getOrThrow(ModBiomes.LEVEL11_BIOME)))
						.generationStep(Decoration.SURFACE_STRUCTURES)
						.terrainAdapation(
								TerrainAdjustment.BEARD_THIN)
						.build(),
				context.lookup(Registries.TEMPLATE_POOL).getOrThrow(ModStructures.LEVEL7_ACCESS_TEMPLATE_POOL),
				1,
				ConstantHeight.of(VerticalAnchor.absolute(7)),
				false));

		context.register(ModStructures.LEVEL9_HOUSE_0_STRUCTURE, new JigsawStructure(
				new StructureSettings.Builder(
						HolderSet.direct(biomes.getOrThrow(ModBiomes.LEVEL9_BIOME)))
						.generationStep(Decoration.SURFACE_STRUCTURES)
						.terrainAdapation(
								TerrainAdjustment.BEARD_THIN)
						.build(),
				context.lookup(Registries.TEMPLATE_POOL).getOrThrow(ModStructures.LEVEL9_HOUSE_TEMPLATE_POOL),
				1,
				BiasedToBottomHeight.of(VerticalAnchor.aboveBottom(48), VerticalAnchor.belowTop(53), 15),
				false));

		context.register(ModStructures.LEVEL11_ENTRY_TOWER_STRUCTURE, new JigsawStructure(
				new StructureSettings.Builder(
						HolderSet.direct(biomes.getOrThrow(ModBiomes.LEVEL9_BIOME)))
						.generationStep(Decoration.SURFACE_STRUCTURES)
						.terrainAdapation(
								TerrainAdjustment.BEARD_THIN)
						.build(),
				context.lookup(Registries.TEMPLATE_POOL).getOrThrow(ModStructures.LEVEL11_TOWER_TEMPLATE_POOL),
				1,
				BiasedToBottomHeight.of(VerticalAnchor.aboveBottom(48), VerticalAnchor.belowTop(53), 15),
				false));

	}

	public static void templBoot(BootstrapContext<StructureTemplatePool> context) {
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
		Holder.Reference<StructureTemplatePool> emptyPool = pools.getOrThrow(Pools.EMPTY);

		context.register(ModStructures.LEVEL7_ACCESS_TEMPLATE_POOL, new StructureTemplatePool(emptyPool,
				ImmutableList.of(
						Pair.of(StructurePoolElement.single("boxys_backrooms:level7_access"), 1)),

				StructureTemplatePool.Projection.RIGID));

		context.register(ModStructures.LEVEL9_HOUSE_TEMPLATE_POOL, new StructureTemplatePool(emptyPool,
				ImmutableList.of(
						Pair.of(StructurePoolElement.single("boxys_backrooms:level9_house_0"), 1)),

				StructureTemplatePool.Projection.TERRAIN_MATCHING));

		context.register(ModStructures.LEVEL11_TOWER_TEMPLATE_POOL, new StructureTemplatePool(emptyPool,
				ImmutableList.of(
						Pair.of(StructurePoolElement.single("boxys_backrooms:level11_tower"), 1)),

				StructureTemplatePool.Projection.TERRAIN_MATCHING));
	}

	public static void setBoot(BootstrapContext<StructureSet> context) {
		context.register(ModStructures.LEVEL7_ACCESS_STRUCTURE_SET, new StructureSet(
				context.lookup(Registries.STRUCTURE).getOrThrow(ModStructures.LEVEL7_ACCESS_STRUCTURE),
				new RandomSpreadStructurePlacement(17, 7, RandomSpreadType.TRIANGULAR, 3)));

		context.register(ModStructures.LEVEL9_HOUSE_STRUCTURE_SET, new StructureSet(
				List.of(
						StructureSet.entry(
								context.lookup(Registries.STRUCTURE)
										.getOrThrow(ModStructures.LEVEL11_ENTRY_TOWER_STRUCTURE),
								1),
						StructureSet.entry(
								context.lookup(Registries.STRUCTURE)
										.getOrThrow(ModStructures.LEVEL9_HOUSE_0_STRUCTURE),
								400)),
				new RandomSpreadStructurePlacement(3, 1, RandomSpreadType.TRIANGULAR, 3)));
	}

}
