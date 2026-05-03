package com.boxyplayz.backrooms.entity.living.Wretch;

import com.boxyplayz.backrooms.effect.ModEffects;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class WretchEntity extends PathfinderMob {

	public static boolean CheckSpawnRules(final EntityType<WretchEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return !level.getBlockState(pos.below()).isAir()
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& pos.getY() < level.getMaxY();
	}

	public WretchEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
				this, Player.class, true));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.39D)
				.add(Attributes.ATTACK_DAMAGE, 16.0D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		if (target instanceof LivingEntity entity) {
			entity.addEffect(new MobEffectInstance(ModEffects.WRETCHED_CYCLE, 20 * 20));
		}
		return super.doHurtTarget(level, target);
	}
}
