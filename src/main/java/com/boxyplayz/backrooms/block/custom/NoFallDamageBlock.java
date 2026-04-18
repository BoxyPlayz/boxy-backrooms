package com.boxyplayz.backrooms.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NoFallDamageBlock extends Block {

	public NoFallDamageBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void fallOn(final Level level, final BlockState state, final BlockPos pos, final Entity entity,
			final double fallDistance) {
	}

}
