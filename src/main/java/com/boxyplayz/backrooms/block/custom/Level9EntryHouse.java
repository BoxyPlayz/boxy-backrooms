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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Level9EntryHouse extends Block {

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.create(0.2, 0.0, 0.2, 0.8, 0.7, 0.8);
	}

	public Level9EntryHouse(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		if (level.isClientSide())
			return super.useWithoutItem(state, level, pos, player, hitResult);
		if (player.isShiftKeyDown())
			return super.useWithoutItem(state, level, pos, player, hitResult);

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL9_DIMENSION);
		if (target == null)
			return super.useWithoutItem(state, level, pos, player, hitResult);

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 49;

		x = Math.floorDiv(x, 24) * 24 + 1;
		z = Math.floorDiv(z, 48) * 48 + 1;

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
		return InteractionResult.SUCCESS;
	}

}
