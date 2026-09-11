package com.boxyplayz.backrooms.entity.living.SkinStealer;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
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
		return this.getAttachedOrElse(SKINSTEALER_PASSIVE_TIMER, 0) > 0;
	}

	public static final AttachmentType<Integer> SKINSTEALER_PASSIVE_TIMER = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "skinstealer_passive_timer"),
			builder -> builder
					.initializer(() -> 0) // The default value of the Attachment, if one has not been set.
					.persistent(Codec.INT) // Dictates how this Attachment's data should be saved and loaded.
					.syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));

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
		if (this.getAttachedOrElse(SKINSTEALER_PASSIVE_TIMER, 0) > 0) {
			this.modifyAttached(SKINSTEALER_PASSIVE_TIMER, (currentValue) -> (currentValue - 1));
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
					if (this.getAttachedOrElse(SKINSTEALER_PASSIVE_TIMER, 0) > 0) {
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
		int timer = this.getAttachedOrElse(SKINSTEALER_PASSIVE_TIMER, 0);
		int damageDealt = (int) Math.floor(damage);
		if (timer > damageDealt) {
			this.setAttached(SKINSTEALER_PASSIVE_TIMER, timer - damageDealt);
		} else if (timer > 0) {
			this.setAttached(SKINSTEALER_PASSIVE_TIMER, 0);
		}
		return super.hurtServer(level, source, damage);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean didHurtResult = super.doHurtTarget(level, target);

		if (target instanceof Player player) {
			if (player.isDeadOrDying()) {
				this.setAttached(SKINSTEALER_PASSIVE_TIMER, 20 * 60);
			}
		}

		return didHurtResult;
	}
}
