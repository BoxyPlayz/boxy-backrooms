package com.boxyplayz.backrooms.effect.custom;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.living.Wretch.WretchEntity;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class WretchedCycleEffect extends MobEffect {

	public WretchedCycleEffect(MobEffectCategory category, int color) {
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

		if (Math.floorMod(mob.tickCount, 20 * 1) == 2) {
			Identifier wretchedCycleId = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "wretched_cycle");
			AttributeInstance mobGetAttribute = mob.getAttribute(Attributes.ATTACK_DAMAGE);
			if (mobGetAttribute != null) {

				double oldModifier = 0;
				AttributeModifier elderModifier = mobGetAttribute.getModifier(wretchedCycleId);
				if (elderModifier != null) {
					oldModifier = elderModifier.amount();
				}
				mobGetAttribute.removeModifier(wretchedCycleId);

				double newStrengthAddition = Math.min(oldModifier + (amplification + 1), 16);

				mobGetAttribute
						.addTransientModifier(new AttributeModifier(
								wretchedCycleId, newStrengthAddition,
								AttributeModifier.Operation.ADD_VALUE));

				if (mob.getHealth() > 5) {
					mob.setHealth(mob.getHealth() - 5);
				} else {
					double x = mob.getX();
					double y = mob.getY();
					double z = mob.getZ();
					mob.kill(serverLevel);
					WretchEntity wretchEntity = new WretchEntity(ModEntities.WRETCH, serverLevel);
					wretchEntity.setPos(x, y, z);
					serverLevel.addFreshEntity(wretchEntity);

				}
			} else {
				BoxysBackrooms.LOGGER.error("Attack Damage doesnt f****ing exist.");
			}

		}

		return super.applyEffectTick(serverLevel, mob, amplification);
	}

}
