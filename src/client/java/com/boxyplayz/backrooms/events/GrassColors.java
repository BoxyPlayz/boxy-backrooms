package com.boxyplayz.backrooms.events;

import java.util.List;

import com.boxyplayz.backrooms.block.ModBlocks;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

public class GrassColors {
	public static void RegisterGrassColors() {

		BlockColorRegistry.register(List.of(
				new BlockTintSource() {
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
				}), ModBlocks.PURE_GRASS);
	}
}
