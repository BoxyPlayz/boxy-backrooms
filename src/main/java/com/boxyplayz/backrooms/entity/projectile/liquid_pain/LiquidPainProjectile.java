package com.boxyplayz.backrooms.entity.projectile.liquid_pain;

import com.boxyplayz.backrooms.damagetypes.ModDamageTypes;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.item.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LiquidPainProjectile extends ThrowableItemProjectile {
	public LiquidPainProjectile(final EntityType<? extends ThrowableItemProjectile> type, final Level level) {
		super(type, level);
	}

	public LiquidPainProjectile(EntityType<? extends ThrowableItemProjectile> type, double x, double y, double z,
			Level level, ItemStack itemStack) {
		super(type, x, y, z, level, itemStack);
	}

	public LiquidPainProjectile(final Level level, final LivingEntity mob, final ItemStack itemStack) {
		super(ModEntities.LIQUID_PAIN_PROJECTILE, mob, level, itemStack);
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.LIQUID_PAIN;
	}

	@Override
	protected void onHit(final HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);
		if (!level().isClientSide()) {
			Entity entity = hitResult.getEntity();
			ServerLevel level = (ServerLevel) entity.level();
			entity.hurtServer(level, new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
					.getOrThrow(ModDamageTypes.ANOMALY_DAMAGE)), 12);
		}
	}
}
