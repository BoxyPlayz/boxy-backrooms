package com.boxyplayz.backrooms.biome;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

	public static final ResourceKey<Biome> LEVEL7_OCEAN_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

	public static final ResourceKey<Biome> LEVEL8_CAVESYSTEM_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

	public static final ResourceKey<Biome> LEVEL94_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));

	public static final ResourceKey<Biome> LEVEL1_AQUILA_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/aquila"));

	public static final ResourceKey<Biome> LEVEL1_GILDED_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/gilded"));

	public static final ResourceKey<Biome> LEVEL1_GARDEN_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/garden"));

	public static final ResourceKey<Biome> LEVEL1_GOTHIC_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/gothic"));

	public static final ResourceKey<Biome> LEVEL1_FABLED_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/fabled"));

	public static final ResourceKey<Biome> LEVEL1_OUROBOROS_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/fabled"));

	public static void RegisterModBiomes() {

	}

}
