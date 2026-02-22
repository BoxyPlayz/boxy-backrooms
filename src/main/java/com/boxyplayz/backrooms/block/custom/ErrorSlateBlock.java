package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ErrorSlateBlock extends Block {

	public ErrorSlateBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
		super.stepOn(level, blockPos, blockState, entity);
		if (level.isClientSide())
			return;
		if (!(entity instanceof ServerPlayer player))
			return;
		if (!player.isShiftKeyDown())
			return;

		if (level.dimension() == ModDimensions.LEVEL8)
			return;

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL8);
		if (target == null)
			return;

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 80;

		boolean foundNew = false;

		BlockPos center = new BlockPos(x, y, z);

		target.getChunk(center);

		for (int dx = -5; dx <= 5; dx++) {
			for (int dz = -5; dz <= 5; dz++) {
				for (int dy = -5; dy <= 5; dy++) {
					BlockPos newPos = center.offset(dx, dy, dz);
					if (target.getBlockState(newPos) == ModBlocks.ERRORSLATE.defaultBlockState()) {
						x = newPos.getX();
						y = newPos.getY() + 1;
						z = newPos.getZ();
						foundNew = true;
					}
				}
			}
		}

		if (!foundNew) {
			for (int dx = -1; dx <= 1; dx++) {
				for (int dz = -1; dz <= 1; dz++) {
					for (int dy = -2; dy <= 2; dy++) {
						BlockPos platformPos = center.offset(dx, dy, dz);
						if (dy == -2) {
							if (dx == 0 && dz == 0) {
								target.setBlockAndUpdate(platformPos, ModBlocks.ERRORSLATE.defaultBlockState());
							} else {
								target.setBlockAndUpdate(platformPos,
										net.minecraft.world.level.block.Blocks.STONE.defaultBlockState());
							}
						} else {
							target.setBlockAndUpdate(platformPos,
									net.minecraft.world.level.block.Blocks.AIR.defaultBlockState());

						}
					}
				}
			}
		}

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
	}

}
