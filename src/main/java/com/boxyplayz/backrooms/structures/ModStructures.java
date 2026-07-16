package com.boxyplayz.backrooms.structures;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class ModStructures {
	public static final ResourceKey<Structure> LEVEL7_ACCESS_STRUCTURE = ResourceKey.create(Registries.STRUCTURE,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_access"));
	public static final ResourceKey<StructureTemplatePool> LEVEL7_ACCESS_TEMPLATE_POOL = ResourceKey.create(
			Registries.TEMPLATE_POOL,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_access"));
	public static final ResourceKey<StructureSet> LEVEL7_ACCESS_STRUCTURE_SET = ResourceKey.create(
			Registries.STRUCTURE_SET,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_access"));

	public static void RegisterStructures() {

	}
}
