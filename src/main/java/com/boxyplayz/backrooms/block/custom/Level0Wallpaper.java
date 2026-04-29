package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayer.RespawnConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData.RespawnData;

/**
 * Wallpaper Block that goes to level 0 from overworld
 */
public class Level0Wallpaper extends Block {

	/**
	 * Creates new Block
	 * 
	 * @param properties Block Properties
	 */
	public Level0Wallpaper(Properties properties) {
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

		if (!(level.dimension() == Level.OVERWORLD))
			return;

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL0_DIMENSION);
		if (target == null)
			return;

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 1;

		BlockPos center = new BlockPos(x, y, z);

		int searchSize = 10;

		searchLoop: for (int dx = -searchSize; dx <= searchSize; dx++) {
			for (int dz = -searchSize; dz <= searchSize; dz++) {
				BlockPos newPos = center.offset(dx, 0, dz);
				if (target.getBlockState(newPos).isAir()) {
					x = newPos.getX();
					z = newPos.getZ();
					break searchLoop;
				}
			}
		}

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);

		RespawnConfig respawnConfig = new RespawnConfig(
				new RespawnData(GlobalPos.of(ModDimensions.LEVEL0_DIMENSION, new BlockPos(x, y, z)), 0, 0),
				true);

		player.setRespawnPosition(respawnConfig, false);
	}

}
