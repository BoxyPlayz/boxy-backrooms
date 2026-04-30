package com.boxyplayz.backrooms.block.entity;

import com.boxyplayz.backrooms.block.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.reborn.energy.api.base.InfiniteEnergyStorage;

public class PowerOutletBlockEntity extends BlockEntity {
	public final InfiniteEnergyStorage energyStorage = new InfiniteEnergyStorage();

	public PowerOutletBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(ModBlockEntities.POWER_OUTLET_BLOCK_ENTITY, worldPosition, blockState);
	}

	public void tick() {

	}

}
