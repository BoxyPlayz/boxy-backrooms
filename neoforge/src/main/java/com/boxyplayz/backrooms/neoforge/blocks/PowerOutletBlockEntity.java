package com.boxyplayz.backrooms.neoforge.blocks;

import com.boxyplayz.backrooms.neoforge.BoxysBackroomsNeoForge;
import com.boxyplayz.backrooms.neoforge.energy.InfiniteEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class PowerOutletBlockEntity extends BlockEntity {
	private final EnergyHandler energy = new InfiniteEnergyStorage();

	public PowerOutletBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(BoxysBackroomsNeoForge.POWER_OUTLET_BLOCK_ENTITY.get(), worldPosition, blockState);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, PowerOutletBlockEntity entity) {
		if (level.isClientSide()) {
			return;
		}
		for (Direction dir : Direction.values()) {
			BlockPos nextPos = pos.relative(dir);
			EnergyHandler target = level.getCapability(Capabilities.Energy.BLOCK, nextPos, dir.getOpposite());
			if (target == null) {
				continue;
			}
			try (Transaction transaction = Transaction.openRoot()) {
				int inserted = target.insert(2, transaction);

				if (inserted > 0) {
					transaction.commit();
				}
			}
		}
	}

	public EnergyHandler getEnergy() {
		return energy;
	}
}
