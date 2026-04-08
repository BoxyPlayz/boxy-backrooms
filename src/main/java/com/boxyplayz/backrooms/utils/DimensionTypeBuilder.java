package com.boxyplayz.backrooms.utils;

import java.util.Optional;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.DimensionType.MonsterSettings;
import net.minecraft.world.level.dimension.DimensionType.Skybox;
import net.minecraft.world.timeline.Timeline;

public class DimensionTypeBuilder {
	private boolean hasFixedTime = false;
	private boolean hasSkyLight = true;
	private boolean hasCeiling = false;
	private double coordinateScale = 1;
	private int minY = 0;
	private int height = 32;
	private TagKey<Block> infiniburn = BlockTags.INFINIBURN_OVERWORLD;
	private float ambientLight = 0;
	private MonsterSettings monsterSettings = new MonsterSettings(new ConstantInt(0), 0);
	private Skybox skybox = Skybox.NONE;
	private CardinalLighting.Type cardinalLightType = CardinalLighting.Type.DEFAULT;
	private EnvironmentAttributeMap attributes = EnvironmentAttributeMap.EMPTY;
	private HolderSet<Timeline> timelines = HolderSet.direct();
	private Optional<Holder<WorldClock>> defaultClock = Optional.empty();

	public DimensionType build() {
		return new DimensionType(
				this.hasFixedTime,
				this.hasSkyLight,
				this.hasCeiling,
				false,
				this.coordinateScale,
				this.minY,
				this.height,
				this.height,
				this.infiniburn,
				this.ambientLight,
				this.monsterSettings,
				this.skybox,
				this.cardinalLightType,
				this.attributes,
				this.timelines,
				this.defaultClock);
	}

	public DimensionTypeBuilder setMinY(int minY) {
		this.minY = minY;
		return this;
	}

	public DimensionTypeBuilder setHeight(int maxY) {
		if (maxY <= 0)
			throw new IllegalArgumentException("WHAT DO YOU THINK YOU'RE DOIN IN MY SWAMP?!");
		this.height = maxY;
		return this;
	}

	public DimensionTypeBuilder setFixedTime(boolean fixedTime) {
		this.hasFixedTime = fixedTime;
		return this;
	}

	public DimensionTypeBuilder setSkylight(boolean skyLightEnabled) {
		this.hasSkyLight = skyLightEnabled;
		return this;
	}

	public DimensionTypeBuilder setInfiniburn(TagKey<Block> blockTag) {
		this.infiniburn = blockTag;
		return this;
	}

	public DimensionTypeBuilder setCeiling(boolean ceiling) {
		this.hasCeiling = ceiling;
		return this;
	}

	public DimensionTypeBuilder setCoordinateScale(double scale) {
		this.coordinateScale = scale;
		return this;
	}

	public DimensionTypeBuilder setAmbientLight(float light) {
		this.ambientLight = light;
		return this;
	}

	public DimensionTypeBuilder setMonsterSettings(MonsterSettings settings) {
		this.monsterSettings = settings;
		return this;
	}

	public DimensionTypeBuilder setSkybox(Skybox skybox) {
		this.skybox = skybox;
		return this;
	}

	public DimensionTypeBuilder setCardinalLightType(CardinalLighting.Type type) {
		this.cardinalLightType = type;
		return this;
	}

	public DimensionTypeBuilder setEnvAttributes(EnvironmentAttributeMap map) {
		this.attributes = map;
		return this;
	}

	public DimensionTypeBuilder setTimelines(HolderSet<Timeline> timelines) {
		this.timelines = timelines;
		return this;
	}

	public DimensionTypeBuilder setClock(Optional<Holder<WorldClock>> clock) {
		this.defaultClock = clock;
		return this;
	}
}