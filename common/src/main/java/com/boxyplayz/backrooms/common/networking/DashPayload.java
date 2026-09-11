package com.boxyplayz.backrooms.common.networking;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DashPayload() implements CustomPacketPayload {
	public static final Identifier DASH_PAYLOAD_ID = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID,
			"dash");

	public static final CustomPacketPayload.Type<DashPayload> TYPE = new CustomPacketPayload.Type<>(
			DASH_PAYLOAD_ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, DashPayload> CODEC = StreamCodec
			.unit(new DashPayload());

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
