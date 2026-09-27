package com.boxyplayz.backrooms.common.block.custom;

import java.util.Set;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;

public class OverworldTransporter extends Block {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public OverworldTransporter(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(POWERED, false));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
			@Nullable Orientation orientation, boolean movedByPiston) {
		if (!level.isClientSide()) {
			if (state.getValue(POWERED) != level.hasNeighborSignal(pos)) {
				level.setBlock(pos, state.setValue(POWERED, level.hasNeighborSignal(pos)), UPDATE_ALL);
			}
		}
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
		if (!level.isClientSide()) {
			if (onState.getValue(POWERED)) {
				if (entity instanceof LivingEntity mob) {
					ServerLevel target = level.getServer().getLevel(Level.OVERWORLD);
					if (target == null)
						return;

					mob.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 10, 9));

					mob.teleportTo(target, mob.getX(), 200, mob.getZ(), Set.of(), mob.getYRot(),
							mob.getXRot(), false);
				}
			}
		}
	}

}
