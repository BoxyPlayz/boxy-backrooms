package com.boxyplayz.backrooms.networking;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DashPayload() implements CustomPacketPayload {
	public static final Identifier DASH_PAYLOAD_ID = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
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
