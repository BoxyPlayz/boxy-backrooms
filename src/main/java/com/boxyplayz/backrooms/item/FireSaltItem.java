package com.boxyplayz.backrooms.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Burning item.
 */
public class FireSaltItem extends Item {

	/**
	 * IT BURNS
	 * 
	 * @param properties Props
	 */
	public FireSaltItem(Properties properties) {
		super(properties);
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.setRemainingFireTicks(120);
	}
}
