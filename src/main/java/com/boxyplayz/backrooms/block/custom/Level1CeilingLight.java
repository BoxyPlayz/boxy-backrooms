package com.boxyplayz.backrooms.block.custom;

import com.boxyplayz.backrooms.utils.Misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class Level1CeilingLight extends Block {
	public static final BooleanProperty DARK = BooleanProperty.create("dark");

	public Level1CeilingLight(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(DARK, false));
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		level.scheduleTick(pos, this, UPDATE_ALL);
		super.onPlace(state, level, pos, oldState, movedByPiston);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(DARK);
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		boolean dark = Misc.getBlackout();

		if (state.getValue(DARK) != dark) {
			level.setBlock(pos, state.setValue(DARK, dark), UPDATE_ALL);
		}

		level.scheduleTick(pos, this, 20);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		boolean dark = Misc.getBlackout();

		if (state.getValue(DARK) != dark) {
			level.setBlock(pos, state.setValue(DARK, dark), UPDATE_ALL);
		}
	}

}
