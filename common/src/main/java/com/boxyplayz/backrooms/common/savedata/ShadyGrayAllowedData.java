package com.boxyplayz.backrooms.common.savedata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class ShadyGrayAllowedData extends SavedData {
	private ArrayList<String> uuids;

	public ShadyGrayAllowedData() {
		this.uuids = new ArrayList<String>();
	}

	public ShadyGrayAllowedData(List<String> uuids) {
		this.uuids = new ArrayList<>(uuids);
	}

	private static final Codec<ShadyGrayAllowedData> CODEC = Codec.list(Codec.STRING).xmap(ShadyGrayAllowedData::new,
			ShadyGrayAllowedData::getUUIDs);

	public boolean uuidInList(UUID uuid) {
		return uuids.contains(uuid.toString());
	}

	public List<String> getUUIDs() {
		return uuids;
	}

	public void addPlayerToList(UUID player) {
		if (!uuids.contains(player.toString())) {
			uuids.add(player.toString());
			setDirty();
		}
	}

	private static final SavedDataType<ShadyGrayAllowedData> TYPE = new SavedDataType<ShadyGrayAllowedData>(
			Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "shady_gray_allowlist"),
			ShadyGrayAllowedData::new,
			CODEC,
			null);

	public static ShadyGrayAllowedData getSavedShadyPlayerList(MinecraftServer server) {
		return server.getDataStorage().computeIfAbsent(TYPE);
	}
}
