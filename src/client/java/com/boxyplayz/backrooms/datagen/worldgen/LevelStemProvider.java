package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.ModBiomes;
import com.boxyplayz.backrooms.ModBiomes.AbyssBiomes;
import com.boxyplayz.backrooms.ModBiomes.Level0Biomes;
import com.boxyplayz.backrooms.ModBiomes.Level1Biomes;
import com.boxyplayz.backrooms.ModBiomes.LevelFunBiomes;
import com.boxyplayz.backrooms.world.ModDimensions;
import com.boxyplayz.backrooms.world.generators.AbyssChunkGen;
import com.boxyplayz.backrooms.world.generators.BlueChannelChunkGen;
import com.boxyplayz.backrooms.world.generators.Level0ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level0_2ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level10ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level11ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level1ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level2ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level3ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level4ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level5ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level6ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level7ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level94ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level9ChunkGen;
import com.boxyplayz.backrooms.world.generators.LevelFunChunkGen;
import com.boxyplayz.backrooms.world.generators.Level_Negative_0_2ChunkGen;
import com.boxyplayz.backrooms.world.generators.PitfallsChunkGen;
import com.boxyplayz.backrooms.world.generators.PromisedLandChunkGen;
import com.boxyplayz.backrooms.world.generators.TheBrokenChunkGen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class LevelStemProvider extends FabricDynamicRegistryProvider {

	public LevelStemProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "levelstemroom";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.LEVEL_STEM));
	}

	public static void bootstrap(BootstrapContext<LevelStem> context) {
		HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

		context.register(ModDimensions.LEVEL0.stem, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL0.type),
				new Level0ChunkGen(biomes.getOrThrow(Level0Biomes.NORMAL_BIOME),
						biomes.getOrThrow(Level0Biomes.COLUMNS_BIOME), biomes.getOrThrow(Level0Biomes.BLACKOUT_BIOME),
						biomes.getOrThrow(Level0Biomes.PITFALLS_BIOME))));

		context.register(ModDimensions.BLUE_CHANNEL.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.BLUE_CHANNEL.type),
						new BlueChannelChunkGen(biomes.getOrThrow(ModBiomes.BLUE_CHANNEL_BIOME))));

		context.register(ModDimensions.LEVEL_NEGATIVE_0_2.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL_NEGATIVE_0_2.type),
						new Level_Negative_0_2ChunkGen(biomes.getOrThrow(Biomes.THE_VOID))));

		context.register(ModDimensions.LEVEL0_2.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL0_2.type),
						new Level0_2ChunkGen(biomes.getOrThrow(Biomes.THE_VOID))));

		context.register(ModDimensions.LEVEL1.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL1.type),
						new Level1ChunkGen(
								biomes.getOrThrow(Level1Biomes.AQUILA_BIOME),
								biomes.getOrThrow(Level1Biomes.GARDEN_BIOME),
								biomes.getOrThrow(Level1Biomes.FABLED_BIOME),
								biomes.getOrThrow(Level1Biomes.OUROBOROS_BIOME),
								biomes.getOrThrow(Level1Biomes.GOTHIC_BIOME),
								biomes.getOrThrow(Level1Biomes.GILDED_BIOME))));

		context.register(ModDimensions.LEVEL2.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL2.type),
						new Level2ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL2_BIOME))));

		context.register(ModDimensions.LEVEL3.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL3.type),
						new Level3ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL3_BIOME))));

		context.register(ModDimensions.LEVEL4.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL4.type),
						new Level4ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL4_BIOME))));

		context.register(ModDimensions.LEVEL5.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL5.type),
						new Level5ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL5_BIOME))));

		context.register(ModDimensions.LEVEL6.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL6.type),
						new Level6ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL6_BIOME))));

		context.register(ModDimensions.LEVEL7.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL7.type),
						new Level7ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL7_OCEAN_BIOME))));

		context.register(ModDimensions.LEVEL9.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL9.type),
						new Level9ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL9_BIOME))));

		context.register(ModDimensions.LEVEL10.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL10.type),
						new Level10ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL10_BIOME))));

		context.register(ModDimensions.LEVEL11.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL11.type),
						new Level11ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL11_BIOME))));

		context.register(ModDimensions.LEVEL94.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL94.type),
						new Level94ChunkGen(biomes.getOrThrow(ModBiomes.LEVEL94_BIOME))));

		context.register(ModDimensions.LEVEL_FUN.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.LEVEL_FUN.type),
						new LevelFunChunkGen(
								biomes.getOrThrow(LevelFunBiomes.HALLWAYS_BIOME),
								biomes.getOrThrow(LevelFunBiomes.PLAYROOMS_BIOME),
								biomes.getOrThrow(LevelFunBiomes.TRAMPOLINE_PARK_BIOME),
								biomes.getOrThrow(LevelFunBiomes.PARTY_ROOMS_BIOME))));

		context.register(ModDimensions.PITFALLS.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.PITFALLS.type),
						new PitfallsChunkGen(biomes.getOrThrow(ModBiomes.PITFALLS_BIOME))));

		context.register(ModDimensions.PROMISED_LAND.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.PROMISED_LAND.type),
						new PromisedLandChunkGen(biomes.getOrThrow(ModBiomes.PROMISED_BIOME))));

		context.register(ModDimensions.THE_ABYSS.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.THE_ABYSS.type),
						new AbyssChunkGen(
								biomes.getOrThrow(AbyssBiomes.ABYSS_BIOME),
								biomes.getOrThrow(AbyssBiomes.ABYSS_COLD_BIOME),
								biomes.getOrThrow(AbyssBiomes.ABYSS_HOT_BIOME),
								biomes.getOrThrow(AbyssBiomes.ABYSS_END_BIOME))));

		context.register(ModDimensions.THE_BROKEN.stem,
				new LevelStem(dimensionTypes.getOrThrow(ModDimensions.THE_BROKEN.type),
						new TheBrokenChunkGen(
								biomes.getOrThrow(ModBiomes.BROKEN_BIOME))));
	}

}
