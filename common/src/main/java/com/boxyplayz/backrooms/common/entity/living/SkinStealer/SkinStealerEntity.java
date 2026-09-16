package com.boxyplayz.backrooms.common.entity.living.SkinStealer;

import com.boxyplayz.backrooms.common.DataAttachments;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SkinStealerEntity extends PathfinderMob {

	public boolean isPassive() {
		return DataAttachments.getPeaceful(this) > 0;
	}

	public static boolean CheckSpawnRules(final EntityType<SkinStealerEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return !level.getBlockState(pos.below()).isAir()
				&& random.nextInt(40) == 4
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& pos.getY() < level.getMaxY();
	}

	public SkinStealerEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void tick() {
		if (DataAttachments.getPeaceful(this) > 0) {
			if (DataAttachments.getPeaceful(this) == 1) {
				DataAttachments.setPassive(this, false);
			}
			DataAttachments.setPeaceful(this, DataAttachments.getPeaceful(this) - 1);
		}
		super.tick();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
				this, Player.class, true, (LivingEntity target, ServerLevel level) -> {
					if (DataAttachments.getPeaceful(this) > 0) {
						return false;
					}
					return true;
				}));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.28D)
				.add(Attributes.ATTACK_DAMAGE, 12.0D)
				.add(Attributes.FOLLOW_RANGE, 80.0D);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		int timer = DataAttachments.getPeaceful(this);
		int damageDealt = (int) Math.floor(damage);
		if (timer > damageDealt) {
			DataAttachments.setPeaceful(this, timer - damageDealt);
		} else if (timer > 0) {
			DataAttachments.setPeaceful(this, 0);
			DataAttachments.setPassive(this, false);
		}
		return super.hurtServer(level, source, damage);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean didHurtResult = super.doHurtTarget(level, target);

		if (target instanceof Player player) {
			if (player.isDeadOrDying()) {
				DataAttachments.setPeaceful(this, 20 * 15);
				DataAttachments.setPassive(this, true);
			}
		}

		return didHurtResult;
	}
}
