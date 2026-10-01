package com.boxyplayz.backrooms.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.ModMaterials;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModEquipmentAssetProvider implements DataProvider {

	private final PackOutput.PathProvider pathProvider;

	public ModEquipmentAssetProvider(PackOutput output,
			CompletableFuture<HolderLookup.Provider> completableFuture) {
		pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
	}

	private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
		consumer.accept(ModMaterials.FIRESTEEL_EQUIPMENT_MATERIAL, EquipmentClientInfo.builder()
				.addHumanoidLayers(Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "firesteel")).build());
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
		bootstrap((id, asset) -> {
			if (equipmentAssets.putIfAbsent(id, asset) != null) {
				throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
			}
		});
		return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
	}

	@Override
	public String getName() {
		return "equipassets";
	}

}
