package com.boxyplayz.backrooms.item;

import com.boxyplayz.backrooms.entity.ModEntities;
import com.boxyplayz.backrooms.entity.projectile.liquid_pain.LiquidPainProjectile;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class LiquidPainItem extends Item implements ProjectileItem {

	public LiquidPainItem(Properties properties) {
		super(properties);
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
		return new LiquidPainProjectile(ModEntities.LIQUID_PAIN_PROJECTILE, position.x(), position.y(),
				position.z(), level, itemStack);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (level instanceof ServerLevel serverLevel) {
			Projectile.spawnProjectileFromRotation(LiquidPainProjectile::new, serverLevel, itemStack, player, 0.0F,
					1.5F, 1.0F);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		itemStack.consume(1, player);
		return super.use(level, player, hand);
	}

}
