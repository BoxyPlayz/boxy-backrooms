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

/**
 * Custom builder for Dimension Types
 */
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

	/**
	 * Turns {@link DimensionTypeBuilder} into {@link DimensionType}
	 */
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

	/**
	 * Sets minimum Y value
	 * 
	 * @param minY Bottom of the world
	 */
	public DimensionTypeBuilder setMinY(int minY) {
		this.minY = minY;
		return this;
	}

	/**
	 * Sets height of the world; NOT THE MAXIMUM Y VALUE
	 * 
	 * @param maxY Height of the world
	 */
	public DimensionTypeBuilder setHeight(int maxY) {
		if (maxY <= 0)
			throw new IllegalArgumentException("WHAT DO YOU THINK YOU'RE DOIN IN MY SWAMP?!");
		this.height = maxY;
		return this;
	}

	/**
	 * Sets whether time moves as normal
	 * 
	 * @param fixedTime Whether or not to enable fixed time
	 */
	public DimensionTypeBuilder setFixedTime(boolean fixedTime) {
		this.hasFixedTime = fixedTime;
		return this;
	}

	/**
	 * Sets whether or not there should be skylight
	 * 
	 * @param skyLightEnabled Whether or not there should be skylight
	 */

	public DimensionTypeBuilder setSkylight(boolean skyLightEnabled) {
		this.hasSkyLight = skyLightEnabled;
		return this;
	}

	/**
	 * Sets the tag which fire upon it will burn forever
	 */
	public DimensionTypeBuilder setInfiniburn(TagKey<Block> blockTag) {
		this.infiniburn = blockTag;
		return this;
	}

	/**
	 * Sets whether or not the dimension has a ceiling. Does not add a ceiling.
	 */
	public DimensionTypeBuilder setCeiling(boolean ceiling) {
		this.hasCeiling = ceiling;
		return this;
	}

	/**
	 * Sets the scale of the coordinates in relation to the overworlds
	 */
	public DimensionTypeBuilder setCoordinateScale(double scale) {
		this.coordinateScale = scale;
		return this;
	}

	/**
	 * Sets ambient light level
	 */
	public DimensionTypeBuilder setAmbientLight(float light) {
		this.ambientLight = light;
		return this;
	}

	/**
	 * Sets monster spawn settings
	 */
	public DimensionTypeBuilder setMonsterSettings(MonsterSettings settings) {
		this.monsterSettings = settings;
		return this;
	}

	/**
	 * Sets the skybox
	 */
	public DimensionTypeBuilder setSkybox(Skybox skybox) {
		this.skybox = skybox;
		return this;
	}

	/**
	 * Sets cardinal lighting
	 */
	public DimensionTypeBuilder setCardinalLightType(CardinalLighting.Type type) {
		this.cardinalLightType = type;
		return this;
	}

	/**
	 * Sets the enviornment attributes
	 */
	public DimensionTypeBuilder setEnvAttributes(EnvironmentAttributeMap map) {
		this.attributes = map;
		return this;
	}

	/**
	 * Sets the timelines of this dimension type
	 */
	public DimensionTypeBuilder setTimelines(HolderSet<Timeline> timelines) {
		this.timelines = timelines;
		return this;
	}

	/**
	 * Sets the default clock in this dimension type
	 */
	public DimensionTypeBuilder setClock(Optional<Holder<WorldClock>> clock) {
		this.defaultClock = clock;
		return this;
	}
}