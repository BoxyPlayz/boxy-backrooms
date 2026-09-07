package com.boxyplayz.backrooms.datagen.worldgen;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.clock.ModWorldClocks;
import com.boxyplayz.backrooms.datagen.DataUtils;
import com.boxyplayz.backrooms.utils.DimensionTypeBuilder;
import com.boxyplayz.backrooms.world.ModDimensions;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.DimensionType.Skybox;
import net.minecraft.world.timeline.Timelines;

public class DimensionTypeProvider extends FabricDynamicRegistryProvider {

	private static void register(BootstrapContext<DimensionType> context, ResourceKey<DimensionType> key,
			DimensionType type) {
		context.register(key, type);
	}

	public DimensionTypeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "DimensionTypeRooms";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
	}

	public static void bootstrap(BootstrapContext<DimensionType> context) {
		register(context, ModDimensions.PITFALLS.type, new DimensionTypeBuilder()
				.setHeight(80)
				.setFixedTime(true)
				.build());

		register(context, ModDimensions.LEVEL0.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setFixedTime(true)
				.setMinY(-16)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL0_2.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setFixedTime(true)
				.setMinY(-16)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL_NEGATIVE_0_2.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setFixedTime(true)
				.setMinY(-16)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL1.type, new DimensionTypeBuilder()
				.setHeight(64)
				.setFixedTime(true)
				.setAmbientLight(0.3f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL7.type, new DimensionTypeBuilder()
				.setHeight(304)
				.setMinY(-64)
				.setFixedTime(false)
				.setAmbientLight(0.2f)
				.setSkylight(true)
				.setCeiling(false)
				.build());

		register(context, ModDimensions.LEVEL94.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(false)
				.setAmbientLight(0)
				.setSkylight(true)
				.setCeiling(false)
				.setClock(
						Optional.of(
								DataUtils.getHolder(context, Registries.WORLD_CLOCK, ModWorldClocks.LEVEL_94_CLOCK)))
				.build());

		register(context, ModDimensions.THE_BROKEN.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0)
				.setSkylight(true)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL8.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.BLUE_CHANNEL.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0.3f)
				.setSkylight(true)
				.setCeiling(false)
				.setInfiniburn(BlockTags.ICE)
				.build());

		register(context, ModDimensions.LEVEL6.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL_FUN.type, new DimensionTypeBuilder()
				.setHeight(128)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.PROMISED_LAND.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(false)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL3.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL5.type, new DimensionTypeBuilder()
				.setHeight(128)
				.setMinY(-16)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL2.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL4.type, new DimensionTypeBuilder()
				.setHeight(32)
				.setMinY(-16)
				.setSkybox(Skybox.NONE)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(false)
				.setCeiling(true)
				.build());

		register(context, ModDimensions.LEVEL11.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(false)
				.setAmbientLight(0f)
				.setSkylight(true)
				.setCeiling(false)
				.setClock(context.lookup(Registries.WORLD_CLOCK).get(WorldClocks.OVERWORLD)
						.map(holder -> (Holder<WorldClock>) holder))
				.setEnvAttributes(
						EnvironmentAttributeMap.builder()
								.set(EnvironmentAttributes.FOG_COLOR, 12638463)
								.set(EnvironmentAttributes.SKY_COLOR, 7907327)
								.set(EnvironmentAttributes.BED_RULE,
										new BedRule(BedRule.Rule.ALWAYS, BedRule.Rule.ALWAYS, false, Optional.empty()))
								.build())
				.setTimelines(HolderSet.direct(
						DataUtils.getHolder(context, Registries.TIMELINE, Timelines.MOON),
						DataUtils.getHolder(context, Registries.TIMELINE, Timelines.OVERWORLD_DAY)))
				.build());

		register(context, ModDimensions.LEVEL9.type, new DimensionTypeBuilder()
				.setHeight(128)
				.setMinY(-16)
				.setEnvAttributes(
						EnvironmentAttributeMap.builder()
								.set(EnvironmentAttributes.SKY_LIGHT_LEVEL, 0.26666668f)
								.set(EnvironmentAttributes.SKY_COLOR, 0)
								.set(EnvironmentAttributes.BED_RULE,
										new BedRule(BedRule.Rule.ALWAYS, BedRule.Rule.ALWAYS, false, Optional.empty()))
								.set(EnvironmentAttributes.MOON_ANGLE, 0.638f)
								.set(EnvironmentAttributes.SKY_LIGHT_COLOR, 8026879)
								.set(EnvironmentAttributes.STAR_ANGLE, 180f)
								.build())
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(true)
				.setCeiling(false)
				.build());

		register(context, ModDimensions.LEVEL10.type, new DimensionTypeBuilder()
				.setHeight(128)
				.setMinY(-16)
				.setEnvAttributes(
						EnvironmentAttributeMap.builder()
								.set(EnvironmentAttributes.SKY_LIGHT_LEVEL, 1f)
								.set(EnvironmentAttributes.SKY_COLOR, 387068)
								.set(EnvironmentAttributes.BED_RULE,
										new BedRule(BedRule.Rule.ALWAYS, BedRule.Rule.ALWAYS, false, Optional.empty()))
								.set(EnvironmentAttributes.SKY_LIGHT_COLOR, 16777215)
								.set(EnvironmentAttributes.SKY_LIGHT_FACTOR, 1f)
								.set(EnvironmentAttributes.SUN_ANGLE, 360f)
								.set(EnvironmentAttributes.FOG_COLOR, 13721868)
								.build())
				.setSkybox(Skybox.OVERWORLD)
				.setFixedTime(true)
				.setAmbientLight(0f)
				.setSkylight(true)
				.setCeiling(false)
				.build());

		register(context, ModDimensions.THE_ABYSS.type, new DimensionTypeBuilder()
				.setHeight(256)
				.setMinY(-16)
				.setSkybox(Skybox.OVERWORLD)
				.setEnvAttributes(
						EnvironmentAttributeMap.builder()
								.set(EnvironmentAttributes.SKY_LIGHT_LEVEL, 0.7f)
								.set(EnvironmentAttributes.SUN_ANGLE, 0.5f)
								.build())
				.setFixedTime(false)
				.setAmbientLight(0.5f)
				.setSkylight(true)
				.setCeiling(false)
				.build());

	}
}
