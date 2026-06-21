package com.boxyplayz.backrooms.dataattachments;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class DataAttachments {
	public static final AttachmentType<BlockPos> PLAYER_BACKROOMS_ENTRY_POINT = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "player_backrooms_entry_point"),
			builder -> builder
					.initializer(() -> new BlockPos(0, 400, 0))
					.persistent(BlockPos.CODEC)
					.copyOnDeath());

	public static final AttachmentType<Boolean> ACCESS_GRAY = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "access_gray"),
			builder -> builder
					.initializer(() -> false)
					.persistent(Codec.BOOL)
					.copyOnDeath()
					.syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));
}
