package com.boxyplayz.backrooms.entity.living.NeighborhoodWatch;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public class NeighborhoodWatchEntity extends PathfinderMob {

	public NeighborhoodWatchEntity(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);
	}

	public static boolean CheckSpawnRules(final EntityType<NeighborhoodWatchEntity> type,
			final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return !level.getBlockState(pos.below()).isAir()
				&& level.getBlockState(pos).isAir()
				&& random.nextInt(20) == 3
				&& level.getBlockState(pos.above()).isAir()
				&& pos.getY() < level.getMaxY()
				&& level.getBrightness(LightLayer.BLOCK, pos) < 9;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 120.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ATTACK_DAMAGE, 12.0D)
				.add(Attributes.FOLLOW_RANGE, 140.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true));
		this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
	}

}
