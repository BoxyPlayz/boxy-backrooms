package com.boxyplayz.backrooms.entity.custom.Balloon;

import java.util.Set;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BalloonEntity extends PathfinderMob {

	public static boolean CheckSpawnRules(final EntityType<BalloonEntity> type, final ServerLevelAccessor level,
			final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
		return !level.getBlockState(pos.below()).isAir()
				&& level.getBlockState(pos).isAir()
				&& level.getBlockState(pos.above()).isAir();
	}

	public BalloonEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.39D)
				.add(Attributes.ATTACK_DAMAGE, 16.0D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		if (source.getEntity() instanceof Player player) {
			ServerLevel target = level.getServer().getLevel(ModDimensions.LEVEL_FUN_DIMENSION);
			if (target == null)
				return super.hurtServer(level, source, damage);

			int x = player.blockPosition().getX();
			int z = player.blockPosition().getZ();
			int y = 1;

			BlockPos center = new BlockPos(x, y, z);

			int searchSize = 10;

			searchLoop: for (int dx = -searchSize; dx <= searchSize; dx++) {
				for (int dz = -searchSize; dz <= searchSize; dz++) {
					BlockPos newPos = center.offset(dx, 0, dz);
					if (target.getBlockState(newPos).isAir()) {
						x = newPos.getX();
						z = newPos.getZ();
						break searchLoop;
					}
				}
			}

			player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
		}
		return super.hurtServer(level, source, damage);
	}
}
