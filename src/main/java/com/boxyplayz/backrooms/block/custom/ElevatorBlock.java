package com.boxyplayz.backrooms.block.custom;

import org.jspecify.annotations.Nullable;

import com.boxyplayz.backrooms.menu.ElevatorMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ElevatorBlock extends Block implements MenuProvider {

	public static final BooleanProperty TOP = BooleanProperty.create("top");

	public ElevatorBlock(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(TOP, false));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(TOP);
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new ElevatorMenu(containerId, inventory);
	}

	@Override
	public Component getDisplayName() {
		return getName();
	}

}
