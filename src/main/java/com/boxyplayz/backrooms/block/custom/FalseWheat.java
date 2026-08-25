package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FalseWheat extends Block {

	public FalseWheat(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
			InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (level.isClientSide())
			return;

		ServerLevel target = entity.level().getServer().getLevel(ModDimensions.LEVEL10_DIMENSION);
		if (target == null)
			return;

		if (!entity.is(EntityType.PLAYER))
			return;

		int x = entity.blockPosition().getX();
		int z = entity.blockPosition().getZ();
		int y = 18;

		entity.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), entity.getYRot(), entity.getXRot(), false);
		return;
	}

}
