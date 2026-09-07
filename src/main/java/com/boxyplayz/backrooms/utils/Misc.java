package com.boxyplayz.backrooms.utils;

import java.util.List;

import com.boxyplayz.backrooms.world.ModDimensions;

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
				ModDimensions.LEVEL0_2.level,
				ModDimensions.LEVEL1.level,
				ModDimensions.LEVEL2.level,
				ModDimensions.LEVEL3.level,
				ModDimensions.LEVEL_NEGATIVE_0_2.level,
				ModDimensions.PITFALLS.level,
				ModDimensions.LEVEL7.level,
				ModDimensions.LEVEL8.level,
				ModDimensions.LEVEL94.level);

		for (ResourceKey<Level> key : wretchedLevels) {
			if (level.dimension() == key) {
				return true;
			}
		}
		return false;
	}

	public static double normalizeValues(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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
