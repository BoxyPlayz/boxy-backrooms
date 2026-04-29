package com.boxyplayz.backrooms.events;

import java.util.List;
import java.util.Set;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.utils.Misc;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayer.RespawnConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.LevelData.RespawnData;

/**
 * Tick Entities
 */
public class EntityTickEvents {
	/**
	 * Registers the event for {@link ServerTickEvents.StartLevelTick}
	 */
	public static void RegisterEntityTickEvents() {
		ServerTickEvents.START_LEVEL_TICK.register((ServerLevel level) -> {
			List<ServerPlayer> players = List.copyOf(level.players());
			players.forEach((ServerPlayer player) -> {
				if (player.level().dimension() == ModDimensions.LEVEL7_DIMENSION) {
					if (player.getAirSupply() < player.getMaxAirSupply()) {
						player.setAirSupply(player.getMaxAirSupply());
					}
				}
				if (Misc.isWretchableBackrooms(player.level())) {
					if (player.getFoodData().getFoodLevel() < 2) {
						if (!player.hasEffect(ModEffects.WRETCHED_CYCLE)) {
							MobEffectInstance instance = new MobEffectInstance(ModEffects.WRETCHED_CYCLE, 20 * 60 * 5);
							player.addEffect(instance);
						}
					}
				}
				if (player.level().dimension() == ModDimensions.PITFALLS_DIMENSION) {
					if (Math.sqrt((player.position().x * player.position().x)
							+ (player.position().z * player.position().z)) > 1000) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL94_DIMENSION);
						if (target == null)
							return;
						player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 10 * 20, 20));
						player.teleportTo(target, 0.5, 120, 0.5, Set.of(), player.getYRot(), player.getXRot(), false);
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL6_DIMENSION) {
					if (player.level().getFluidState(player.blockPosition()).is(Fluids.WATER)) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL7_DIMENSION);
						if (target == null) {
							return;
						}

						int x = player.blockPosition().getX();
						int z = player.blockPosition().getZ();
						int y = 200;
						player.teleportTo(target, x + 0.5, y, z + 0.5, Set.of(), player.getYRot(), player.getXRot(),
								false);
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL0_DIMENSION) {
					if (player.position().y < -10) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.PITFALLS_DIMENSION);
						if (target == null)
							return;
						player.fallDistance = 0;
						player.teleportTo(target, 0, 10, 0, Set.of(), player.getYRot(), player.getXRot(), false);
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL_NEGATIVE_0_2_DIMENSION) {
					if (player.position().y < -10) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.BLUE_CHANNEL_DIMENSION);
						if (target == null)
							return;
						player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 10 * 20, 20));
						player.teleportTo(target, player.position().x, 120, player.position().z, Set.of(),
								player.getYRot(), player.getXRot(), false);
					}
				}

				if (player.level().dimension() == ModDimensions.PROMISED_LAND_DIMENSION) {
					if (player.position().y < -20) {
						ServerLevel target = player.level().getServer().getLevel(Level.OVERWORLD);
						if (target == null)
							return;
						player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 20, 20));
						player.teleportTo(target, player.position().x, 300, player.position().z, Set.of(),
								player.getYRot(), player.getXRot(), false);
						RespawnConfig respawnConfig = new RespawnConfig(
								RespawnData.DEFAULT,
								true);

						player.setRespawnPosition(respawnConfig, false);
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL1_DIMENSION) {
					if (player.level().getBiome(new BlockPos(
							((int) Math.floor(player.position().x)),
							((int) Math.floor(player.position().y)),
							((int) Math.floor(player.position().z))))
							.is(ModBiomes.Level1Biomes.GARDEN_BIOME)) {
						if (!(player.hasEffect(ModEffects.GARDENERS_PAIN))) {
							player.addEffect(new MobEffectInstance(ModEffects.GARDENERS_PAIN, 15 * 20));
						} else {
							if (player.getEffect(ModEffects.GARDENERS_PAIN).getDuration() < 10 * 20) {
								player.addEffect(new MobEffectInstance(ModEffects.GARDENERS_PAIN, 15 * 20));
							}
						}
					}
				}

				AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
				Identifier gardenersPainId = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
						"gardeners_pain");
				if (!(player.hasEffect(ModEffects.GARDENERS_PAIN))) {
					if (attribute.hasModifier(gardenersPainId)) {
						attribute.removeModifier(gardenersPainId);
					}
				}

			});
		});
	}

}
