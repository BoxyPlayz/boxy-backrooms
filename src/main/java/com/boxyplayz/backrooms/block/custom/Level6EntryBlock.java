package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Level6EntryBlock extends HalfTransparentBlock {

	public Level6EntryBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		if (level.isClientSide())
			return InteractionResult.FAIL;
		if (!(player instanceof ServerPlayer serverPlayer))
			return InteractionResult.FAIL;

		if (!(level.dimension() == ModDimensions.LEVEL5_DIMENSION))
			return InteractionResult.FAIL;

		ServerLevel target = serverPlayer.level().getServer().getLevel(ModDimensions.LEVEL6_DIMENSION);
		if (target == null)
			return InteractionResult.FAIL;

		int x = serverPlayer.blockPosition().getX();
		int z = serverPlayer.blockPosition().getZ();
		int y = 1;

		BlockPos center = new BlockPos(x, y, z);

		int searchSize = 32;

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

		serverPlayer.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), serverPlayer.getYRot(), serverPlayer.getXRot(),
				false);
		return super.useWithoutItem(state, level, pos, serverPlayer, hitResult);
	}

}
