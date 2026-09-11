package com.boxyplayz.backrooms.entity.living.Partypooper;

import com.boxyplayz.backrooms.entity.living.Partygoer.PartygoerEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PartypooperEntity extends PathfinderMob {

	public static boolean CheckSpawnRules(final EntityType<PartypooperEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return !level.getBlockState(pos.below()).isAir()
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& pos.getY() < level.getMaxY();
	}

	public PartypooperEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this,
				Player.class).setAlertOthers());
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
				this, PartygoerEntity.class, true));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.4D)
				.add(Attributes.ATTACK_DAMAGE, 12.0D)
				.add(Attributes.FOLLOW_RANGE, 16.0D);
	}
}
