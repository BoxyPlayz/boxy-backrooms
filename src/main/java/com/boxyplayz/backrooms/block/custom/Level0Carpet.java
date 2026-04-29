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
 * Entry to Level 0 from 0.2 or Blue Channel
 */
public class Level0Carpet extends Block {

	/**
	 * New Block
	 * 
	 * @param properties Block Properties
	 */
	public Level0Carpet(Properties properties) {
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

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL0_DIMENSION);
		if (target == null)
			return;

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 1;

		BlockPos center = new BlockPos(x, y, z);

		byte searchSize = 10;

		searchLoop: for (byte dx = (byte) -searchSize; dx <= searchSize; dx++) {
			for (byte dz = (byte) -searchSize; dz <= searchSize; dz++) {
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
