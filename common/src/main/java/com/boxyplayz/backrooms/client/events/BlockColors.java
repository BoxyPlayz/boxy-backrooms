package com.boxyplayz.backrooms.client.events;

import com.boxyplayz.backrooms.common.block.ModBlocks;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

public class BlockColors {
	public static void Register() {
		ColorHandlerRegistry.registerBlockColors(new BlockTintSource() {
			public int color(BlockState state) {
				return GrassColor.get(0.5D, 1.0D);
			};

			@Override
			public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
				if (level != null && pos != null) {
					return BiomeColors.getAverageGrassColor(level, pos);
				}
				return GrassColor.get(0.5D, 1.0D);
			}
		}, ModBlocks.PURE_GRASS);
	}
}
