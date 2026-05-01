package com.boxyplayz.backrooms.block.entity;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.block.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.InfiniteEnergyStorage;

public class PowerOutletBlockEntity extends BlockEntity {
	public final InfiniteEnergyStorage energyStorage = new InfiniteEnergyStorage();

	public PowerOutletBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(ModBlockEntities.POWER_OUTLET_BLOCK_ENTITY, worldPosition, blockState);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, PowerOutletBlockEntity entity) {
		if (level.isClientSide()) {
			return;
		}
		for (Direction dir : Direction.values()) {
			@Nullable
			EnergyStorage storage = EnergyStorage.SIDED.find(level, pos.relative(dir), dir.getOpposite());
			if (storage != null) {
				EnergyStorageUtil.move(entity.energyStorage, storage, 2L, null);
			}
		}
	}
}
