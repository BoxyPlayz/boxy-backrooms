package com.boxyplayz.backrooms.block.custom;

import org.jspecify.annotations.Nullable;

import com.boxyplayz.backrooms.damagetypes.ModDamageTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PainBlock extends Block {

	public PainBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
			@Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
		player.hurtServer(level.getServer().getLevel(level.dimension()),
				new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
						.getOrThrow(ModDamageTypes.ANOMALY_DAMAGE)),
				8f);
		super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
	}
}
