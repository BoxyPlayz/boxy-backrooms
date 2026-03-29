package com.boxyplayz.backrooms.events;

import java.util.List;
import java.util.Set;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.effect.ModEffects;
import com.boxyplayz.backrooms.entity.custom.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.item.ModItems;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.dimension.ModDimensions;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class EntityEvents {
	public static void RegisterEntityEvents() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((LivingEntity entity, DamageSource source, float amount) -> {
			if ((entity instanceof SmilerEntity)) {
				if (source.is(DamageTypes.PLAYER_ATTACK)) {
					if (source.getEntity() instanceof Player player) {
						if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
								|| player.getItemBySlot(EquipmentSlot.OFFHAND)
										.is(ModItems.FIRESALT_SHARD.asItem())) {
							entity.setRemainingFireTicks(120);
						}
					}
					return false;
				}
				if (source.is(DamageTypes.MACE_SMASH)) {
					return false;
				}
				if (source.is(DamageTypes.SPEAR)) {
					return false;
				}
			}
			return true;
		});

		UseEntityCallback.EVENT.register(
				(Player player, Level world, InteractionHand hand, Entity entity, EntityHitResult hitResult) -> {
					if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FIRESALT_SHARD.asItem())
							|| player.getItemBySlot(EquipmentSlot.OFFHAND)
									.is(ModItems.FIRESALT_SHARD.asItem())) {
						entity.setRemainingFireTicks(120);
					}

					return InteractionResult.PASS;
				});

		ServerTickEvents.START_LEVEL_TICK.register((ServerLevel level) -> {
			List<ServerPlayer> players = List.copyOf(level.players());
			players.forEach((ServerPlayer player) -> {
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

				if (player.level().dimension() == ModDimensions.LEVEL0_DIMENSION) {
					if (player.position().y < -10) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.PITFALLS_DIMENSION);
						if (target == null)
							return;
						player.fallDistance = 0;
						player.teleportTo(target, 0, 10, 0, Set.of(), player.getYRot(), player.getXRot(), false);
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
