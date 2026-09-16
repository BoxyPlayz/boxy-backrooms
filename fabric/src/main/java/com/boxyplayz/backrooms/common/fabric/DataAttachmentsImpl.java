package com.boxyplayz.backrooms.common.fabric;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class DataAttachmentsImpl {

	public static final AttachmentType<BlockPos> PLAYER_BACKROOMS_ENTRY_POINT = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "player_backrooms_entry_point"),
			builder -> builder
					.initializer(() -> new BlockPos(0, 400, 0))
					.persistent(BlockPos.CODEC)
					.copyOnDeath());

	public static final AttachmentType<Boolean> ACCESS_GRAY = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "access_gray"),
			builder -> builder
					.initializer(() -> false)
					.persistent(Codec.BOOL)
					.copyOnDeath()
					.syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));

	public static final AttachmentType<Integer> SKINSTEALER_PASSIVE_TIMER = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "skinstealer_passive_timer"),
			builder -> builder
					.initializer(() -> 0) // The default value of the Attachment, if one has not been set.
					.persistent(Codec.INT) // Dictates how this Attachment's data should be saved and loaded.
	);

	public static final AttachmentType<Boolean> SKINSTEALER_PASSIVE = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "skinstealer_passive"),
			builder -> builder
					.initializer(() -> false) // The default value of the Attachment, if one has not been set.
					.persistent(Codec.BOOL) // Dictates how this Attachment's data should be saved and loaded.
					.syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));

	public static BlockPos getEntryPoint(Player player) {
		return player.getAttached(PLAYER_BACKROOMS_ENTRY_POINT);
	}

	public static void setEntryPoint(Player player, BlockPos pos) {
		player.setAttached(PLAYER_BACKROOMS_ENTRY_POINT, pos);
	}

	public static boolean hasEntryPoint(Player player) {
		return player.hasAttached(PLAYER_BACKROOMS_ENTRY_POINT);
	}

	public static void removeEntryPoint(Player player) {
		player.removeAttached(PLAYER_BACKROOMS_ENTRY_POINT);
	}

	public static boolean getShadyGray(Player player) {
		return player.getAttachedOrElse(ACCESS_GRAY, false);
	}

	public static void setShadyGray(Player player, boolean enabled) {
		player.setAttached(ACCESS_GRAY, enabled);
	}

	public static boolean hasShadyGray(Player player) {
		return player.hasAttached(ACCESS_GRAY);
	}

	public static int getPeaceful(Entity entity) {
		int value = entity.getAttachedOrElse(SKINSTEALER_PASSIVE_TIMER, 0);

		return value;

	}

	public static void setPeaceful(Entity entity, int timer) {
		entity.setAttached(SKINSTEALER_PASSIVE_TIMER, timer);
	}

	public static boolean isPassive(Entity entity) {
		return entity.getAttachedOrElse(SKINSTEALER_PASSIVE, false);
	}

	public static void setPassive(Entity entity, boolean passive) {
		entity.setAttached(SKINSTEALER_PASSIVE, passive);
	}
}
