package com.boxyplayz.backrooms.entity.custom.Smiler;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.tags.ModTags;
import com.boxyplayz.backrooms.world.biome.ModBiomes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SmilerEntity extends PathfinderMob {

	public static boolean CheckSmilerSpawnRules(final EntityType<SmilerEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return level.getBlockState(pos.below()).is(ModBlocks.LEVEL0_CARPET)
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir()
				&& (level.getBiome(pos).is(ModBiomes.Level0Biomes.BLACKOUT_BIOME))
				&& level.getBrightness(LightLayer.BLOCK, pos) < 5;
	}

	public SmilerEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<Player>(this, Player.class, 10, 1.1, 1.2, player -> {
			ItemStack mainHand = player.getMainHandItem();
			ItemStack offHand = player.getOffhandItem();
			return mainHand.getItem() == ModItems.SMILER_REPELLANT.asItem() ||
					offHand.getItem() == ModItems.SMILER_REPELLANT.asItem();
		}));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true) {
			@Override
			protected void checkAndPerformAttack(LivingEntity target) {
				if (this.canPerformAttack(target)) {
					this.resetAttackCooldown();
					target.hurtServer(getServerLevel(target),
							new DamageSource(level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
									.getOrThrow(DamageTypes.MAGIC), null, this.mob),
							((float) this.mob.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
				}
				super.checkAndPerformAttack(target);
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
				this, Player.class, true, (LivingEntity target, ServerLevel level) -> {
					if (target instanceof Player player) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModTags.LIGHT_ITEMS)
								|| player.getItemBySlot(EquipmentSlot.OFFHAND).is(ModTags.LIGHT_ITEMS)) {
							return true;
						}
					}
					return false;
				}));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(
				this, Player.class, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
				.add(Attributes.FOLLOW_RANGE, 80.0D);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damageSource, float amount) {
		if (damageSource.is(DamageTypes.PLAYER_ATTACK)) {
			if (damageSource.getEntity() instanceof Player player) {
				if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESTEEL_SWORD)) {
					return super.hurtServer(level, damageSource, amount);
				}
				if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD)
						|| player.getItemBySlot(EquipmentSlot.OFFHAND).is(ModItems.FIRESALT_SHARD)) {
					this.setRemainingFireTicks(120);
				}
			}
			return false;
		}
		if (damageSource.is(ModTags.PHYSICAL_ATTACKS)) {
			return false;
		}
		if (damageSource.is(DamageTypes.ARROW)) {
			return super.hurtServer(level, damageSource, amount * 0.75f);
		}
		return super.hurtServer(level, damageSource, amount);
	}
}
