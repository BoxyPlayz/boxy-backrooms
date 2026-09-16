package com.boxyplayz.backrooms.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class DataAttachments {
	@ExpectPlatform
	public static BlockPos getEntryPoint(Player player) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static void setEntryPoint(Player player, BlockPos pos) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static boolean hasEntryPoint(Player player) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static void removeEntryPoint(Player player) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static boolean getShadyGray(Player player) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static void setShadyGray(Player player, boolean enabled) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static boolean hasShadyGray(Player player) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static int getPeaceful(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static void setPeaceful(Entity entity, int timer) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static boolean isPassive(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@ExpectPlatform
	public static void setPassive(Entity entity, boolean passive) {
		throw new UnsupportedOperationException();
	}
}
