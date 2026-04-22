package com.boxyplayz.backrooms.entity.custom.Partygoer;

import com.boxyplayz.backrooms.block.ModBlocks;

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

public class PartygoerEntity extends PathfinderMob {

	public static boolean CheckSpawnRules(final EntityType<PartygoerEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return level.getBlockState(pos.below()).is(ModBlocks.FUN_FLOOR)
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& level.getBlockState(pos.above(2)).isAir();
	}

	public PartygoerEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5D, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
				this, Player.class, false));
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 32.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 16.0D)
				.add(Attributes.JUMP_STRENGTH, 2D)
				.add(Attributes.FOLLOW_RANGE, 96.0D);
	}
}
