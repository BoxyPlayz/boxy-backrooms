package com.boxyplayz.backrooms.mixin;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.item.ModItems;
import com.boxyplayz.backrooms.common.world.ModBiomes.Level0Biomes;
import com.boxyplayz.backrooms.common.world.ModDimensions;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@ModifyReturnValue(method = "getDamageAfterArmorAbsorb", at = @At("RETURN"))
	protected float backrooms$getDamageAfterArmorAbsorb(float original, final DamageSource damageSource, float damage) {
		LivingEntity entity = (LivingEntity) (Object) this;
		float reduction = 0;
		if (damageSource.is(ModTags.FIRE_ATTACKS)) {
			if (entity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FIRESTEEL_HELMET.get())) {
				reduction += 0.25f;
			}
			if (entity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.FIRESTEEL_CHESTPLATE.get())) {
				reduction += 0.25f;
			}
			if (entity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.FIRESTEEL_LEGGINGS.get())) {
				reduction += 0.25f;
			}
			if (entity.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FIRESTEEL_BOOTS.get())) {
				reduction += 0.25f;
			}
		}
		return (original * (1 - reduction));
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void backrooms$firesteelRegeneration(final CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (!entity.level().isClientSide()) {

			if (entity.isOnFire()) {
				if (entity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FIRESTEEL_HELMET.get())
						&& entity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.FIRESTEEL_CHESTPLATE.get())
						&& entity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.FIRESTEEL_LEGGINGS.get())
						&& entity.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FIRESTEEL_BOOTS.get())) {
					if (Math.floorMod(entity.tickCount, 20 * 2) == 0) {
						entity.heal(1);
					}
				}
			}

			ServerLevel level = (ServerLevel) entity.level();

			if (level.dimension() == ModDimensions.LEVEL7.level) {
				if (entity.getAirSupply() < entity.getMaxAirSupply()) {
					entity.setAirSupply(entity.getMaxAirSupply());
				}
			}

			if (entity.position().y() < -16) {
				if (level.dimension() == ModDimensions.THE_BROKEN.level) {
					ServerLevel target = level.getServer().getLevel(ModDimensions.THE_ABYSS.level);
					if (target == null)
						return;
					entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 30, 9));
					entity.teleportTo(target, 0, 300, 0, Set.of(),
							entity.getYRot(), entity.getXRot(),
							false);
				}

				if (level.dimension() == ModDimensions.BLUE_CHANNEL.level) {
					ServerLevel target = level.getServer().getLevel(ModDimensions.THE_BROKEN.level);
					if (target == null)
						return;
					entity.teleportTo(target, 0, 120, 0, Set.of(),
							entity.getYRot(), entity.getXRot(),
							false);
				}

				if (level.dimension() == ModDimensions.LEVEL0.level) {
					if (level.getBiome(entity.blockPosition()).is(Level0Biomes.PITFALLS_BIOME)) {
						ServerLevel target = level.getServer()
								.getLevel(ModDimensions.PITFALLS.level);
						if (target == null)
							return;
						entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 10 * 20, 20));
						entity.teleportTo(target, 0, 10, 0, Set.of(), entity.getYRot(), entity.getXRot(), false);
					}
				}

				if (level.dimension() == ModDimensions.LEVEL_NEGATIVE_0_2.level) {
					ServerLevel target = level.getServer()
							.getLevel(ModDimensions.BLUE_CHANNEL.level);
					if (target == null)
						return;
					entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 10 * 20, 20));
					entity.teleportTo(target, entity.position().x, 120, entity.position().z, Set.of(),
							entity.getYRot(), entity.getXRot(), false);

				}
			}

			if (level.dimension() == ModDimensions.PITFALLS.level) {
				if (Math.abs(entity.position().x()) > 500 || Math.abs(entity.position().z()) > 500) {
					ServerLevel target = level.getServer().getLevel(ModDimensions.LEVEL94.level);
					if (target == null)
						return;
					entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 10 * 20, 20));
					entity.teleportTo(target, 0.5, 120, 0.5, Set.of(), entity.getYRot(), entity.getXRot(), false);
				}
			}
		}
	}

	@Inject(method = "handleDamageEvent", at = @At("HEAD"), cancellable = true)
	protected void backrooms$handleDamageEvent(final DamageSource source, final CallbackInfo cir) {
		if (source.is(ModTags.FIRE_ATTACKS)) {
			LivingEntity entity = (LivingEntity) (Object) this;

			if (entity.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FIRESTEEL_BOOTS.get()) &&
					entity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.FIRESTEEL_LEGGINGS.get()) &&
					entity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.FIRESTEEL_CHESTPLATE.get()) &&
					entity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FIRESTEEL_HELMET.get())) {
				cir.cancel();
			}
		}
	}
}
