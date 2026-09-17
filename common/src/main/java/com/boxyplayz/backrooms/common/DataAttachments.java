package com.boxyplayz.backrooms.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
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
}
