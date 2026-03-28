package com.boxyplayz.backrooms.world.biome;

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

	public static final ResourceKey<Biome> PITFALLS_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"));

	public class Level1Biomes {
		public static final ResourceKey<Biome> AQUILA_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/aquila"));

		public static final ResourceKey<Biome> GILDED_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/gilded"));

		public static final ResourceKey<Biome> GARDEN_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/garden"));

		public static final ResourceKey<Biome> GOTHIC_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/gothic"));

		public static final ResourceKey<Biome> FABLED_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/fabled"));

		public static final ResourceKey<Biome> OUROBOROS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/fabled"));
	}

	public class Level0Biomes {
		public static final ResourceKey<Biome> NORMAL_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/normal"));
		public static final ResourceKey<Biome> COLUMNS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/columns"));
		public static final ResourceKey<Biome> BLACKOUT_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/blackout"));
		public static final ResourceKey<Biome> PITFALLS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/pitfalls"));

	}

	public static void RegisterModBiomes() {

	}

}
