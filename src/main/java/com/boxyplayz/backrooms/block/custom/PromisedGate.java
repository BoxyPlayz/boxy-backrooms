package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PromisedGate extends Block {

	public PromisedGate(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.create(new AABB(new Vec3(0, 0, 0), new Vec3(1, 0.1, 1)));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {

		if (level.isClientSide())
			return super.useWithoutItem(state, level, pos, player, hitResult);
		if (player.isShiftKeyDown())
			return super.useWithoutItem(state, level, pos, player, hitResult);

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.PROMISED_LAND_DIMENSION);
		if (target == null)
			return super.useWithoutItem(state, level, pos, player, hitResult);

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 1;

		BlockPos center = new BlockPos(x, y, z);

		byte searchSize = 32;

		searchLoop: for (byte dx = (byte) -searchSize; dx <= searchSize; dx++) {
			for (byte dz = (byte) -searchSize; dz <= searchSize; dz++) {
				BlockPos newPos = center.offset(dx, 0, dz);
				if (target.getBlockState(newPos).isAir()) {
					if (!target.getBlockState(pos.below()).isAir()) {
						if (target.getBlockState(pos.above()).isAir()) {
							x = newPos.getX();
							z = newPos.getZ();
							break searchLoop;
						}
					}
				}
			}
		}

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
		return InteractionResult.SUCCESS;
	}

}
