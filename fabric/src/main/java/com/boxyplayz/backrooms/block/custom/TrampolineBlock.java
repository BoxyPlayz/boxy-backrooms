package com.boxyplayz.backrooms.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TrampolineBlock extends NoFallDamageBlock {

	protected void bounceUp(final Entity entity) {
		Vec3 movement = entity.getDeltaMovement();
		if (Math.abs(movement.y) < 0.2) {
			movement = new Vec3(movement.x, 0, movement.z);
		}
		entity.setDeltaMovement(movement.x, -movement.y * 1.3, movement.z);
	}

	public TrampolineBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(final Level level, final BlockPos pos, final BlockState onState, final Entity entity) {
		double absDeltaY = Math.abs(entity.getDeltaMovement().y);
		if (absDeltaY > 0.1) {
			double scale = 0.6 + absDeltaY * 0.4;
			if (scale < 1) {
				scale = 1;
			}
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(scale, 1.0, scale));
		}

		super.stepOn(level, pos, onState, entity);
	}

	@Override
	public void updateEntityMovementAfterFallOn(final BlockGetter level, final Entity entity) {
		this.bounceUp(entity);
	}

}
