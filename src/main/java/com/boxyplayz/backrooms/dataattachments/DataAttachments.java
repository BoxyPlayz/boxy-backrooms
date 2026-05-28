package com.boxyplayz.backrooms.dataattachments;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class DataAttachments {
	public static final AttachmentType<BlockPos> PLAYER_BACKROOMS_ENTRY_POINT = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "player_backrooms_entry_point"),
			builder -> builder
					.initializer(() -> new BlockPos(0, 400, 0))
					.persistent(BlockPos.CODEC)
					.copyOnDeath());
}
