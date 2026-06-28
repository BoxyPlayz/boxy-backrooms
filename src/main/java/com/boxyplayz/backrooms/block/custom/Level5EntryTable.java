package com.boxyplayz.backrooms.block.custom;

import java.util.Set;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Level5EntryTable extends Block {

	public Level5EntryTable(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		if (level.isClientSide())
			return super.useWithoutItem(state, level, pos, player, hitResult);
		if (player.isShiftKeyDown())
			return super.useWithoutItem(state, level, pos, player, hitResult);

		ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL5_DIMENSION);
		if (target == null)
			return super.useWithoutItem(state, level, pos, player, hitResult);

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = 36;

		x = Math.floorDiv(x, 24) * 24 + 1;
		z = Math.floorDiv(z, 48) * 48 + 1;

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
		return InteractionResult.SUCCESS;
	}

}
