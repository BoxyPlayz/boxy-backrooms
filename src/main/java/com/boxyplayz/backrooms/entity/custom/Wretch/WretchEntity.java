package com.boxyplayz.backrooms.entity.custom.Wretch;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class WretchEntity extends PathfinderMob {

	public static boolean CheckWretchSpawnRules(final EntityType<WretchEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return level.getBlockState(pos.below()).is(ModBlocks.LEVEL0_CARPET)
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& (level.getBiome(pos).is(ModBiomes.Level0Biomes.BLACKOUT_BIOME))
				&& level.getBrightness(LightLayer.BLOCK, pos) < 5;
	}

	public WretchEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
				this, Player.class, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.39D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}
}
