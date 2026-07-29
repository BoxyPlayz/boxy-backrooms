package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Level11Concrete extends Block {

	public Level11Concrete(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
		if (level.isClientSide())
			return;

		ServerLevel target = entity.level().getServer().getLevel(ModDimensions.LEVEL11_DIMENSION);
		if (target == null)
			return;

		if (!entity.is(EntityType.PLAYER))
			return;

		int x = entity.blockPosition().getX();
		int z = entity.blockPosition().getZ();
		int y = 6;

		x = Math.floorDiv(x, 24) * 24 + 1;
		z = Math.floorDiv(z, 48) * 48 + 1;

		entity.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), entity.getYRot(), entity.getXRot(), false);
		return;
	}

}
