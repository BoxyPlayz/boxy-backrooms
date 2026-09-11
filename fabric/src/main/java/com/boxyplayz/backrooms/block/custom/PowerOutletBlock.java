package com.boxyplayz.backrooms.block.custom;

import org.jspecify.annotations.Nullable;

import com.boxyplayz.backrooms.block.ModBlockEntities;
import com.boxyplayz.backrooms.block.entity.PowerOutletBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> type) {
		return createTickerHelper(type, ModBlockEntities.POWER_OUTLET_BLOCK_ENTITY, PowerOutletBlockEntity::tick);
	}

	@Override
	protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 15;
	}

	@Override
	protected boolean isSignalSource(BlockState state) {
		return true;
	}

}
