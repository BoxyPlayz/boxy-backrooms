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

public class PlayerOverworldEntryLocations extends SavedData {
	private HashMap<String, BlockPos> uuids;

	public PlayerOverworldEntryLocations() {
		this.uuids = new HashMap<String, BlockPos>();
	}

	public PlayerOverworldEntryLocations(Map<String, BlockPos> uuids) {
		this.uuids = new HashMap<>(uuids);
	}

	private static final Codec<PlayerOverworldEntryLocations> CODEC = Codec.unboundedMap(Codec.STRING, BlockPos.CODEC)
			.xmap(
					PlayerOverworldEntryLocations::new,
					PlayerOverworldEntryLocations::getData);

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

	private static final SavedDataType<PlayerOverworldEntryLocations> TYPE = new SavedDataType<PlayerOverworldEntryLocations>(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "player_spawn_locations"),
			PlayerOverworldEntryLocations::new,
			CODEC,
			null);

	public static PlayerOverworldEntryLocations getSavedLocations(MinecraftServer server) {
		return server.getDataStorage().computeIfAbsent(TYPE);
	}

	public boolean hasPlayer(UUID player) {
		return uuids.containsKey(player.toString());
	}
}
