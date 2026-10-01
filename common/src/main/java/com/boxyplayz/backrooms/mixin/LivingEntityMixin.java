package com.boxyplayz.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.item.ModItems;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.world.damagesource.DamageSource;
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
}
