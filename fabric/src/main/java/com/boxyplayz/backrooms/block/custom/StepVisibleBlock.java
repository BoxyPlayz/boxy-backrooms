package com.boxyplayz.backrooms.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class StepVisibleBlock extends Block {

	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

	public StepVisibleBlock(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVATED);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
		level.setBlock(pos, onState.setValue(ACTIVATED, true), UPDATE_ALL, UPDATE_ALL);
		level.scheduleTick(pos, this, 60);
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.setBlock(pos, state.setValue(ACTIVATED, false), UPDATE_ALL, UPDATE_ALL);
	}

}
