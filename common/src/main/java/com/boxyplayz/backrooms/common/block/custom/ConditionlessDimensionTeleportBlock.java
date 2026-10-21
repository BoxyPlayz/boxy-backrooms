package com.boxyplayz.backrooms.common.block.custom;

import java.util.List;
import java.util.Set;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.world.ModDimensions;
import com.boxyplayz.backrooms.common.world.ModDimensions.DimensionInstance;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ConditionlessDimensionTeleportBlock extends Block {

	private DimensionInstance target;
	private List<ResourceKey<Level>> starts;

	public static class CustomProps {
		private BlockBehaviour.Properties properties;

		public DimensionInstance target = ModDimensions.LEVEL0;
		public List<ResourceKey<Level>> starts = List.of();

		public CustomProps() {
			this.properties = BlockBehaviour.Properties.of();
		}

		public CustomProps(BlockBehaviour.Properties props) {
			this.properties = props;
		}

		public CustomProps setProps(BlockBehaviour.Properties props) {
			this.properties = props;
			return this;
		}

		public CustomProps starts(List<ResourceKey<Level>> starts) {
			this.starts = starts;
			return this;
		}

		public CustomProps target(DimensionInstance target) {
			this.target = target;
			return this;
		}

		public CustomProps setId(ResourceKey<Block> id) {
			this.properties = this.properties.setId(id);
			return this;
		}

		public BlockBehaviour.Properties build() {
			return properties;
		}
	}

	public ConditionlessDimensionTeleportBlock(CustomProps properties) {
		super(properties.build());
		this.target = properties.target;
		this.starts = properties.starts;
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
		if (level.isClientSide())
			return;
		if (!(entity instanceof ServerPlayer player))
			return;
		if (!player.isShiftKeyDown())
			return;
		if (!(starts.contains(level.dimension())))
			return;
		ServerLevel target = player.level().getServer().getLevel(this.target.level);
		if (target == null) {
			BoxysBackroomsCommon.LOGGER.error("The level %s does not exist.", this.target.toString());
			return;
		}

		int x = player.blockPosition().getX();
		int z = player.blockPosition().getZ();
		int y = this.target.props.teleportableY;

		BlockPos center = new BlockPos(x, y, z);

		short searchSize = 25;

		if (target.getBlockState(center).isSuffocating(level, pos)) {
			searchLoop: for (short dx = (short) -searchSize; dx <= searchSize; dx++) {
				for (short dz = (short) -searchSize; dz <= searchSize; dz++) {
					BlockPos newPos = center.offset(dx, 0, dz);
					if (!target.getBlockState(newPos).isSuffocating(level, newPos)) {
						x = newPos.getX();
						z = newPos.getZ();
						break searchLoop;
					}
				}
			}
		}

		player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
	}

}
