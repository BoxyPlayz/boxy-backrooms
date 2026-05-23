package com.boxyplayz.backrooms.networking;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ElevatorPayload(String destination, int entityId) implements CustomPacketPayload {

	public static final Identifier ELEVATOR_PAYLOAD_ID = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
			"elevator_payload");
	public static final CustomPacketPayload.Type<ElevatorPayload> TYPE = new CustomPacketPayload.Type<>(
			ELEVATOR_PAYLOAD_ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, ElevatorPayload> CODEC = StreamCodec
			.composite(ByteBufCodecs.STRING_UTF8, ElevatorPayload::destination,
					ByteBufCodecs.INT, ElevatorPayload::entityId,
					ElevatorPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
