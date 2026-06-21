package com.boxyplayz.backrooms.utils;

import java.util.List;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

/**
 * Miscellaneous methods
 */
public class Misc {
	/**
	 * Checks whether a player can get the Wretched Cycle in the dimension
	 * 
	 * @param level The dimension to check
	 * @return Whether the level is in the wretched levels
	 */
	public static boolean isWretchableBackrooms(Level level) {
		List<ResourceKey<Level>> wretchedLevels = List.of(
				ModDimensions.LEVEL0_2_DIMENSION,
				ModDimensions.LEVEL1_DIMENSION,
				ModDimensions.LEVEL2_DIMENSION,
				ModDimensions.LEVEL3_DIMENSION,
				ModDimensions.LEVEL_NEGATIVE_0_2_DIMENSION,
				ModDimensions.PITFALLS_DIMENSION,
				ModDimensions.LEVEL7_DIMENSION,
				ModDimensions.LEVEL8_DIMENSION,
				ModDimensions.LEVEL94_DIMENSION);

		for (ResourceKey<Level> key : wretchedLevels) {
			if (level.dimension() == key) {
				return true;
			}
		}
		return false;
	}

	public static enum ElevatorDestination {
		EMPTY,
		LEVEL1,
		LEVEL2,
		LEVEL3,
		LEVEL4,
		SHADE_GRAY
	}

	public static String getElevatorLangId(String id) {
		return "elevator." + id;
	}
}
