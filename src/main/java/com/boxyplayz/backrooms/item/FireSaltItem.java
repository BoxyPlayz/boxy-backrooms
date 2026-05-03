package com.boxyplayz.backrooms.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

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

	@Override
	public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
		if (enchantment.is(Enchantments.FIRE_ASPECT)) {
			return false;
		}
		return super.canBeEnchantedWith(stack, enchantment, context);
	}
}
