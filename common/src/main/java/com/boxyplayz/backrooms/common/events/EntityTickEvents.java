package com.boxyplayz.backrooms.common.events;

import java.util.List;
import java.util.Set;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.effect.ModEffects;
import com.boxyplayz.backrooms.common.savedata.PlayerBackroomsEntryLocations;
import com.boxyplayz.backrooms.common.savedata.PlayerOverworldEntryLocations;
import com.boxyplayz.backrooms.common.world.ModBiomes;
import com.boxyplayz.backrooms.common.world.ModDimensions;
import com.boxyplayz.backrooms.common.world.ModBiomes.AbyssBiomes;
import com.boxyplayz.backrooms.common.world.ModDimensions.DimensionInstance;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayer.RespawnConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.LevelData.RespawnData;

/**
 * Tick Entities
 */
public class EntityTickEvents {
	public static void RegisterEntityTickEvents() {

		TickEvent.SERVER_LEVEL_POST.register((ServerLevel level) -> {
			List<ServerPlayer> players = List.copyOf(level.players());
			players.forEach((ServerPlayer player) -> {
				if (player.level().dimension() == ModDimensions.THE_ABYSS.level) {
					boolean fullLeather = player.getItemBySlot(EquipmentSlot.CHEST).is(Items.LEATHER_CHESTPLATE)
							&& player.getItemBySlot(EquipmentSlot.HEAD).is(Items.LEATHER_HELMET)
							&& player.getItemBySlot(EquipmentSlot.LEGS).is(Items.LEATHER_LEGGINGS)
							&& player.getItemBySlot(EquipmentSlot.FEET).is(Items.LEATHER_BOOTS);

					boolean noArmor = player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
							&& player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()
							&& player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()
							&& player.getItemBySlot(EquipmentSlot.FEET).isEmpty();

					if (!fullLeather) {
						if (player.level().getBiome(player.blockPosition()).is(AbyssBiomes.ABYSS_COLD_BIOME)) {
							player.setTicksFrozen(
									Math.min(player.getTicksFrozen() + 4, player.getTicksRequiredToFreeze()));

							if (player.getTicksFrozen() >= player.getTicksRequiredToFreeze()
									&& player.tickCount % 40 == 0) {
								player.hurtServer(
										level,
										player.damageSources().freeze(),
										1.0F);
							}

						} else {
							player.setTicksFrozen(
									Math.max(0, player.getTicksFrozen() - 2));
						}
					}
					if (player.level().getBiome(player.blockPosition()).is(AbyssBiomes.ABYSS_HOT_BIOME)) {
						if (!noArmor) {
							if (fullLeather) {
								player.setRemainingFireTicks(20 * 20);
							} else {
								player.setRemainingFireTicks(4 * 20);
							}
						}
					}
				}
			});
		});

		TickEvent.SERVER_LEVEL_PRE.register((ServerLevel level) -> {
			List<ServerPlayer> players = List.copyOf(level.players());
			players.forEach((ServerPlayer player) -> {
				DimensionInstance dimension = ModDimensions.getDimensionFromLevel(player.level());

				if (dimension == null) {
					return;
				}

				if (dimension.props.wretchedCycle) {
					if (player.getFoodData().getFoodLevel() < 2) {
						if (!player.hasEffect(ModEffects.WRETCHED_CYCLE)) {
							MobEffectInstance instance = new MobEffectInstance(ModEffects.WRETCHED_CYCLE, 20 * 5);
							player.addEffect(instance);
						}
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL6.level) {
					if (player.level().getFluidState(player.blockPosition()).is(Fluids.WATER)) {
						ServerLevel target = player.level().getServer().getLevel(ModDimensions.LEVEL7.level);
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

				if (player.level().dimension() == ModDimensions.PROMISED_LAND.level) {
					if (player.position().y < -20) {
						ServerLevel target = player.level().getServer().getLevel(Level.OVERWORLD);
						if (target == null)
							return;
						PlayerOverworldEntryLocations locations = PlayerOverworldEntryLocations
								.getSavedLocations(player.level().getServer());
						PlayerBackroomsEntryLocations backLocations = PlayerBackroomsEntryLocations
								.getSavedLocations(player.level().getServer());
						player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 20, 20));
						BlockPos teleportPos = new BlockPos(0, 400, 0);
						if (locations.hasPlayer(player.getUUID())) {
							BlockPos attachPos = locations.getPosForPlayer(player.getUUID()).orElse(BlockPos.ZERO);
							teleportPos = new BlockPos(attachPos.getX(), 400, attachPos.getZ());

							locations.removePlayerPos(player.getUUID());
							backLocations.removePlayerPos(player.getUUID());
						}
						player.teleportTo(target, teleportPos.getX(), teleportPos.getY(), teleportPos.getZ(), Set.of(),
								player.getYRot(), player.getXRot(), false);
						RespawnConfig respawnConfig = new RespawnConfig(
								RespawnData.DEFAULT,
								true);

						player.setRespawnPosition(respawnConfig, false);
					}
				}

				if (player.level().dimension() == ModDimensions.LEVEL1.level
						&& player.level().getBiome(player.blockPosition())
								.is(ModBiomes.Level1Biomes.GARDEN_BIOME)) {
					if (!(player.hasEffect(ModEffects.GARDENERS_PAIN))) {
						player.addEffect(new MobEffectInstance(ModEffects.GARDENERS_PAIN, 15 * 20));
					} else {
						if (player.getEffect(ModEffects.GARDENERS_PAIN).getDuration() < 10 * 20) {
							player.addEffect(new MobEffectInstance(ModEffects.GARDENERS_PAIN, 15 * 20));
						}
					}
				} else {
					AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
					Identifier gardenersPainId = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID,
							"gardeners_pain");
					if (!(player.hasEffect(ModEffects.GARDENERS_PAIN))) {
						if (attribute.hasModifier(gardenersPainId)) {
							attribute.removeModifier(gardenersPainId);
						}
					}

				}

			});
		});
	}

}
