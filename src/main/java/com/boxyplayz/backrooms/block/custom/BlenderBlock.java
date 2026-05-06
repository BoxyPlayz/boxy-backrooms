package com.boxyplayz.backrooms.block.custom;

import org.jspecify.annotations.Nullable;

import com.boxyplayz.backrooms.menu.BlenderMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlenderBlock extends HalfTransparentBlock implements MenuProvider {

	public BlenderBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		player.openMenu(this);
		return InteractionResult.SUCCESS;
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new BlenderMenu(containerId, inventory);
	}

	@Override
	public Component getDisplayName() {
		return getName();
	}

}
