package com.boxyplayz.backrooms.neoforge.energy;

import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class InfiniteEnergyStorage implements EnergyHandler {

	@Override
	public int extract(int amount, TransactionContext transaction) {
		return Math.min(amount, 2);
	}

	@Override
	public long getAmountAsLong() {
		return Long.MAX_VALUE;
	}

	@Override
	public long getCapacityAsLong() {
		return Long.MAX_VALUE;
	}

	@Override
	public int insert(int amount, TransactionContext transactionContext) {
		return 0;
	}

}
