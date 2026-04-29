package com.boxyplayz.backrooms.effect.custom;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * Gardener's Pain
 */
public class GardenersPainEffect extends MobEffect {

	/**
	 * Create Effect
	 * 
	 * @param category MobCategory
	 * @param color    Color
	 */
	public GardenersPainEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
		if (amplification > 4) {
			amplification = 4;
		}

		if (Math.floorMod(mob.tickCount, 20 * 5) == 0) {
			Identifier gardenersPainId = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "gardeners_pain");
			AttributeInstance mobGetAttribute = mob.getAttribute(Attributes.MAX_HEALTH);
			if (mobGetAttribute != null) {

				double oldModifier = 0;
				AttributeModifier elderModifier = mobGetAttribute.getModifier(gardenersPainId);
				if (elderModifier != null) {
					oldModifier = elderModifier.amount();
				}
				mobGetAttribute.removeModifier(gardenersPainId);

				double newHealthRemoval = oldModifier - (amplification + 1);

				double baseHP = mobGetAttribute.getBaseValue();
				double minMods = 2 - baseHP;

				if (newHealthRemoval < minMods) {
					newHealthRemoval = minMods;
				}

				mobGetAttribute
						.addTransientModifier(new AttributeModifier(
								gardenersPainId, newHealthRemoval,
								AttributeModifier.Operation.ADD_VALUE));

				if (mob.getHealth() > mob.getMaxHealth()) {
					mob.setHealth(mob.getMaxHealth());
				}
			} else {
				BoxysBackrooms.LOGGER.error("Max health doesnt f****ing exist.");
			}

		}

		return super.applyEffectTick(serverLevel, mob, amplification);
	}

}
