package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class OceanTransporter extends Block {

	public OceanTransporter(Properties properties) {
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

		if (level.dimension() == ModDimensions.LEVEL7_DIMENSION)
			return;

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL7_DIMENSION);
		if (target == null)
			return;

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 90;

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
	}

}
