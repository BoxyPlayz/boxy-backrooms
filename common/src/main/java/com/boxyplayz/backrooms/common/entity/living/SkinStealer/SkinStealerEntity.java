package com.boxyplayz.backrooms.common.entity.living.SkinStealer;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SkinStealerEntity extends PathfinderMob {
	public static final EntityDataAccessor<Integer> PEACE_TIMER = SynchedEntityData.defineId(SkinStealerEntity.class,
			EntityDataSerializers.INT);
	int peaceTimer = 0;

	@Override
	protected void defineSynchedData(Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(PEACE_TIMER, 0);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		this.peaceTimer = input.getIntOr("peace_timer", 0);
		this.updatePassive();
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.putInt("peace_timer", peaceTimer);
	}

	public boolean isPassive() {
		return this.getEntityData().get(PEACE_TIMER) > 0;
	}

	private void updatePassive() {
		this.getEntityData().set(PEACE_TIMER, peaceTimer);
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
		if (this.peaceTimer > 0) {
			this.peaceTimer -= 1;
			this.updatePassive();
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
					if (this.peaceTimer > 0) {
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
		int damageDealt = (int) Math.floor(damage);
		if (peaceTimer > damageDealt) {
			peaceTimer -= damageDealt;
		} else if (peaceTimer > 0) {
			peaceTimer = 0;
		}
		this.updatePassive();
		return super.hurtServer(level, source, damage);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean didHurtResult = super.doHurtTarget(level, target);

		if (target instanceof Player player) {
			if (player.isDeadOrDying()) {
				this.peaceTimer = 20 * 15;
				this.updatePassive();
			}
		}

		return didHurtResult;
	}
}
