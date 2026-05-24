package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Level2Door extends Block {

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.create(new AABB(
				0, 0, 0.4,
				1, 2, 0.6));
	}

	public Level2Door(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
			InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (level.isClientSide())
			return;
		if (entity instanceof Player player) {
			if (player.isShiftKeyDown())
				return;
		}

		ServerLevel target = entity.level().getServer().getLevel(ModDimensions.LEVEL2_DIMENSION);
		if (target == null)
			return;

		int x = entity.blockPosition().getX();
		int z = entity.blockPosition().getZ();
		int y = 0;

		BlockPos center = new BlockPos(x, y, z);

		byte searchSize = 48;

		searchLoop: for (byte dx = (byte) -searchSize; dx <= searchSize; dx++) {
			for (byte dz = (byte) -searchSize; dz <= searchSize; dz++) {
				BlockPos newPos = center.offset(dx, 0, dz);
				if (target.getBlockState(newPos).isAir()) {
					if (target.getBlockState(newPos.above()).isAir()) {
						if (!target.getBlockState(newPos.below()).isAir()) {
							x = newPos.getX();
							z = newPos.getZ();
							break searchLoop;
						}
					}
				}
			}
		}
		entity.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), entity.getYRot(), entity.getXRot(), false);
	}
}
