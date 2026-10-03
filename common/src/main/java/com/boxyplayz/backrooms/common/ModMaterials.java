package com.boxyplayz.backrooms.common;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModMaterials {
	public static final ResourceKey<? extends Registry<EquipmentAsset>> EQUIPMENT_REGISTRY_KEY = ResourceKey
			.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

	public static final ResourceKey<EquipmentAsset> FIRESTEEL_EQUIPMENT_MATERIAL = ResourceKey
			.create(EQUIPMENT_REGISTRY_KEY, Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "firesteel"));

	public static final ToolMaterial FIRESTEEL_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
			500,
			10,
			0, 20, ModTags.FIRESTEEL_REPAIR_ITEMS);

	public static final ArmorMaterial FIRESTEEL_ARMOR_MATERIAL = new ArmorMaterial(22,
			ArmorMaterials.makeDefense(2, 5, 6, 2, 5),
			30,
			SoundEvents.ARMOR_EQUIP_IRON,
			1.0f,
			0.05f,
			ModTags.FIRESTEEL_REPAIR_ITEMS,
			FIRESTEEL_EQUIPMENT_MATERIAL);

	public static void RegisterToolMaterials() {

	}
}
