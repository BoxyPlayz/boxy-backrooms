package com.boxyplayz.backrooms.common;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/**
 * Miscellaneous methods
 */
public class Misc {

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

	public static Vec3 toVec3(BlockPos pos) {
		return new Vec3(pos.getX(), pos.getY(), pos.getZ());
	}
}
