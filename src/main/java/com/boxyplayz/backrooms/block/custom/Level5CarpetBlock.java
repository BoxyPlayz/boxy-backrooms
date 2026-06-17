package com.boxyplayz.backrooms.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Level5CarpetBlock extends Block {

	public static final IntegerProperty TYPE = IntegerProperty.create("carpet_type", 0, 2);

	public Level5CarpetBlock(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(TYPE, 0));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(TYPE);
	}

}
