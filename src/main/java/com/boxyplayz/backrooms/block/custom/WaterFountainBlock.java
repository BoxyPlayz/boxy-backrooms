package com.boxyplayz.backrooms.block.custom;

import com.boxyplayz.backrooms.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class WaterFountainBlock extends Block {
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	public WaterFountainBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (itemStack.is(ModItems.EMPTY_ALMOND_WATER)) {
			if (player.getInventory().getFreeSlot() > -1
					|| (player.getInventory().countItem(ModItems.GRAY_ALMOND_WATER) < 64
							&& player.getInventory().countItem(ModItems.GRAY_ALMOND_WATER) > 0)) {
				itemStack.shrink(1);
				player.getInventory().add(new ItemStack(ModItems.GRAY_ALMOND_WATER));
			}
		}
		return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
	}

}
