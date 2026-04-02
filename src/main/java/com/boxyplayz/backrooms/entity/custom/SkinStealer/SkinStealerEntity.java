package com.boxyplayz.backrooms.entity.custom.SkinStealer;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SkinStealerEntity extends PathfinderMob {

	private static final EntityDataAccessor<String> PLAYER_KILLED = SynchedEntityData.defineId(SkinStealerEntity.class,
			EntityDataSerializers.STRING);

	private static final EntityDataAccessor<String> PLAYER_KILLED_UUID = SynchedEntityData.defineId(
			SkinStealerEntity.class,
			EntityDataSerializers.STRING);

	public SkinStealerEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new AvoidEntityGoal<Player>(this, Player.class, 4, 1.1, 1.2, player -> {
			return false;
		}));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
				this, Player.class, true));
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
	public boolean hurtServer(ServerLevel level, DamageSource damageSource, float amount) {
		return super.hurtServer(level, damageSource, amount);
	}

	@Override
	protected void defineSynchedData(Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(PLAYER_KILLED, "");
		entityData.define(PLAYER_KILLED_UUID, "");
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean didHurtResult = super.doHurtTarget(level, target);

		if (target instanceof Player player) {
			if (!player.isAlive()) {
				GameProfile profile = player.getGameProfile();
				setPlayerKilled(profile.name());
				setPlayerKilledUUID(profile.id());
			}
		}

		return didHurtResult;
	}

	public String getPlayerKilled() {
		return entityData.get(PLAYER_KILLED);
	}

	public UUID getPlayerKilledUUID() {
		return UUID.fromString(entityData.get(PLAYER_KILLED_UUID));
	}

	protected void setPlayerKilled(String playerName) {
		entityData.set(PLAYER_KILLED, playerName);
	}

	protected void setPlayerKilledUUID(UUID uuid) {
		entityData.set(PLAYER_KILLED_UUID, uuid.toString());
	}
}
