package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PureBlue extends Block {

	public PureBlue(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {

		if (level.isClientSide())
			return;

		if (!(level.dimension() == ModDimensions.BROKEN_DIMENSION))
			return;

		ServerLevel target = entity.level().getServer().getLevel(ModDimensions.BLUE_CHANNEL_DIMENSION);
		if (target == null)
			return;

		int x = entity.blockPosition().getX();
		int z = entity.blockPosition().getZ();
		int y = 200;

		entity.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), entity.getYRot(), entity.getXRot(), false);
	}

}
