package com.boxyplayz.backrooms.block.custom;

import org.jspecify.annotations.Nullable;

import com.boxyplayz.backrooms.block.entity.PowerOutletBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PowerOutletBlock extends BaseEntityBlock {

	public PowerOutletBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new PowerOutletBlockEntity(worldPosition, blockState);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(PowerOutletBlock::new);
	}

}
