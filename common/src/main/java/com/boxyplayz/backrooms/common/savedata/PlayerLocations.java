package com.boxyplayz.backrooms.common.savedata;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class PlayerLocations extends SavedData {
	private HashMap<String, BlockPos> uuids;

	public PlayerLocations() {
		this.uuids = new HashMap<String, BlockPos>();
	}

	public PlayerLocations(Map<String, BlockPos> uuids) {
		this.uuids = new HashMap<>(uuids);
	}

	private static final Codec<PlayerLocations> CODEC = Codec.unboundedMap(Codec.STRING, BlockPos.CODEC).xmap(
			PlayerLocations::new,
			PlayerLocations::getData);

	public Optional<BlockPos> getPosForPlayer(UUID uuid) {
		if (uuids.containsKey(uuid.toString())) {
			return Optional.of(uuids.get(uuid.toString()));
		}
		return Optional.empty();
	}

	public void removePlayerPos(UUID uuid) {
		if (uuids.containsKey(uuid.toString())) {
			uuids.remove(uuid.toString());
			setDirty();
		}
	}

	public Map<String, BlockPos> getData() {
		return uuids;
	}

	public void addPlayerToList(UUID player, BlockPos pos) {
		uuids.put(player.toString(), pos);

		setDirty();
	}

	private static final SavedDataType<PlayerLocations> TYPE = new SavedDataType<PlayerLocations>(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "player_spawn_locations"),
			PlayerLocations::new,
			CODEC,
			null);

	public static PlayerLocations getSavedLocations(MinecraftServer server) {
		return server.getDataStorage().computeIfAbsent(TYPE);
	}

	public boolean hasPlayer(UUID player) {
		return uuids.containsKey(player.toString());
	}
}
