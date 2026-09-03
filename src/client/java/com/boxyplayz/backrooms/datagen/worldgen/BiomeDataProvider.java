package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.structures.ModStructures;
import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.BedRule.Rule;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;

public class BiomeDataProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key,
			Biome type) {
		context.register(key, type);
	}

	public BiomeDataProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "BiomeRoomsorsomething";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.BIOME));
	}

	public static void bootstrap(BootstrapContext<Biome> context) {
		register(context, ModBiomes.PITFALLS_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build())
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 16106001)
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL94_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(20479)
						.foliageColorOverride(65290)
						.grassColorOverride(65290)
						.dryFoliageColorOverride(15098112)
						.build())
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 53759)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 53759)
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.BLUE_CHANNEL_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(255)
						.build())
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 255)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 255)
				.setAttribute(EnvironmentAttributes.NIGHT_VISION_COLOR, 255)
				.setAttribute(EnvironmentAttributes.SUNRISE_SUNSET_COLOR, 16754176)
				.setAttribute(EnvironmentAttributes.CLOUD_COLOR, 1728436)
				.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 1728436)
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL7_OCEAN_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(2047)
						.build())
				.hasPrecipitation(true)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL8_CAVESYSTEM_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4012102)
						.grassColorOverride(3229232)
						.foliageColorOverride(3229232)
						.dryFoliageColorOverride(6184036)
						.build())
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SURFACE_SLIME_SPAWN_CHANCE, 0f)
				.setAttribute(EnvironmentAttributes.BED_RULE,
						new BedRule(Rule.NEVER,
								Rule.NEVER,
								false,
								Optional.of(Component.translatable("text.boxys_backrooms.level8bed"))))
				.setAttribute(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
						context.lookup(Registries.CONFIGURED_CARVER))
						.addFeature(Decoration.UNDERGROUND_STRUCTURES, ModStructures.LEVEL9_ENTRY_HOUSE_PLACED_KEY)
						.build())
				.build());

		Biome level0 = new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4159204)
						.build())
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 16106001)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build();

		register(context, ModBiomes.Level0Biomes.NORMAL_BIOME, level0);

		register(context, ModBiomes.Level0Biomes.COLUMNS_BIOME, level0);

		register(context, ModBiomes.Level0Biomes.PITFALLS_BIOME, level0);

		register(context, ModBiomes.Level0Biomes.BLACKOUT_BIOME, level0);

		register(context, ModBiomes.Level1Biomes.AQUILA_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 14671839)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 14671839)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.Level1Biomes.GILDED_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 13355979)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 13355979)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.Level1Biomes.GOTHIC_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 3422528)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 3422528)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.Level1Biomes.OUROBOROS_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 9079434)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 9079434)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.Level1Biomes.GARDEN_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 58879)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 58879)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.Level1Biomes.FABLED_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(12287)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 7365192)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 7365192)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL6_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(0)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		Biome LevelFun = new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(255)
						.build())
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 16711680)
				.hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 16711680)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build();

		register(context, ModBiomes.LevelFunBiomes.HALLWAYS_BIOME, LevelFun);
		register(context, ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME, LevelFun);
		register(context, ModBiomes.LevelFunBiomes.PLAYROOMS_BIOME, LevelFun);
		register(context, ModBiomes.LevelFunBiomes.TRAMPOLINE_PARK_BIOME, LevelFun);

		register(context, ModBiomes.PROMISED_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(0)
						.build())
				.hasPrecipitation(true)
				.temperature(0.5f)
				.downfall(0.5f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL3_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(0)
						.build())
				.hasPrecipitation(false)
				.temperature(0.8f)
				.downfall(0.2f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.BROKEN_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(0)
						.build())
				.hasPrecipitation(false)
				.temperature(1f)
				.downfall(0.0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL2_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(false)
				.temperature(1f)
				.downfall(0.0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL4_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(false)
				.temperature(1f)
				.downfall(0.0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL5_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(false)
				.temperature(1f)
				.downfall(0.0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL11_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(true)
				.temperature(0.5f)
				.downfall(1f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL9_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(false)
				.temperature(0.3f)
				.downfall(0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.LEVEL10_BIOME, new Biome.BiomeBuilder()
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(4607)
						.build())
				.hasPrecipitation(true)
				.temperature(0.3f)
				.downfall(1f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.ABYSS_BIOME, new Biome.BiomeBuilder()
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 460551)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 460551)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(1315862)
						.build())
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.ABYSS_COLD_BIOME, new Biome.BiomeBuilder()
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 8649976)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 8649976)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(86878)
						.build())
				.hasPrecipitation(true)
				.temperature(0.1f)
				.downfall(0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.ABYSS_HOT_BIOME, new Biome.BiomeBuilder()
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 9833729)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 9833729)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(68702)
						.build())
				.hasPrecipitation(false)
				.temperature(0.9f)
				.downfall(0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());

		register(context, ModBiomes.ABYSS_END_BIOME, new Biome.BiomeBuilder()
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 1114134)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 1114134)
				.setAttribute(EnvironmentAttributes.STAR_ANGLE, 0.638f)
				.setAttribute(EnvironmentAttributes.STAR_BRIGHTNESS, 0.7f)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.waterColor(8883365)
						.build())
				.hasPrecipitation(false)
				.temperature(0.5f)
				.downfall(0f)
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.generationSettings(BiomeGenerationSettings.EMPTY)
				.build());
	}
}
