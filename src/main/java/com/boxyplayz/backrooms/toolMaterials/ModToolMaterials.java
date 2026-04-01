package com.boxyplayz.backrooms.toolMaterials;

import com.boxyplayz.backrooms.tags.ModTags;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
	public static final ToolMaterial FIRESTEEL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500,
			10,
			0, 20, ModTags.FIRESTEEL_REPAIR_ITEMS);

	public static void RegisterToolMaterials() {

	}
}
