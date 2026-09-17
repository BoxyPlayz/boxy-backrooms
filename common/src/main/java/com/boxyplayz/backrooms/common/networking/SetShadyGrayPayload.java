package com.boxyplayz.backrooms.common.networking;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SetShadyGrayPayload(boolean enabled) implements CustomPacketPayload {

	public static final Identifier GRAY_PAYLOAD_ID = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID,
			"gray_payload");
	public static final CustomPacketPayload.Type<SetShadyGrayPayload> TYPE = new CustomPacketPayload.Type<>(
			GRAY_PAYLOAD_ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, SetShadyGrayPayload> CODEC = StreamCodec
			.composite(ByteBufCodecs.BOOL, SetShadyGrayPayload::enabled,
					SetShadyGrayPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
