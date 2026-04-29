package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Worse carpet.
 */
public class InferiorCarpet extends Block {

	/**
	 * Not good enough.
	 * 
	 * @param properties Props
	 */
	public InferiorCarpet(Properties properties) {
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

		if (!(level.dimension() == ModDimensions.LEVEL0_2_DIMENSION
				|| level.dimension() == ModDimensions.BLUE_CHANNEL_DIMENSION))
			return;

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL_NEGATIVE_0_2_DIMENSION);
		if (target == null)
			return;

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 1;

		BlockPos center = new BlockPos(x, y, z);

		short searchSize = 10;

		searchLoop: for (short dx = (short) -searchSize; dx <= searchSize; dx++) {
			for (short dz = (short) -searchSize; dz <= searchSize; dz++) {
				BlockPos newPos = center.offset(dx, 0, dz);
				if (!target.getBlockState(newPos).isSuffocating(level, blockPos)) {
					x = newPos.getX();
					z = newPos.getZ();
					break searchLoop;
				}
			}
		}

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
	}

}
