package com.boxyplayz.backrooms.world.biome;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

/**
 * {@link ResourceKey}s for all biomes added
 */
public class ModBiomes {

	/**
	 * Ocean
	 */
	public static final ResourceKey<Biome> LEVEL7_OCEAN_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

	/**
	 * Cave Systems
	 */
	public static final ResourceKey<Biome> LEVEL8_CAVESYSTEM_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

	/**
	 * Motion
	 */
	public static final ResourceKey<Biome> LEVEL94_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));
	/**
	 * Electical
	 */
	public static final ResourceKey<Biome> LEVEL3_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level3"));

	/**
	 * Pitfalls
	 */
	public static final ResourceKey<Biome> PITFALLS_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"));

	/**
	 * The Blue Chanel
	 */
	public static final ResourceKey<Biome> BLUE_CHANNEL_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue_channel"));

	/**
	 * Lights out
	 */
	public static final ResourceKey<Biome> LEVEL6_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level6"));

	/**
	 * The Promised Land
	 */
	public static final ResourceKey<Biome> PROMISED_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "promised"));

	/**
	 * Error 500
	 */
	public static final ResourceKey<Biome> BROKEN_BIOME = ResourceKey.create(
			Registries.BIOME,
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "broken"));

	/**
	 * Biomes for sections of level 1. Subclass of {@link ModBiomes}
	 */
	public static class Level1Biomes {
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
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1/ouroboros"));
	}

	/**
	 * Biomes for sections of Level 0. Subclass of {@link ModBiomes}
	 */
	public static class Level0Biomes {
		/**
		 * Good ol Backrooms
		 */
		public static final ResourceKey<Biome> NORMAL_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/normal"));
		/**
		 * Structural Support
		 */
		public static final ResourceKey<Biome> COLUMNS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/columns"));

		/**
		 * Danger Zone
		 */
		public static final ResourceKey<Biome> BLACKOUT_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/blackout"));

		/**
		 * Don't fall in!
		 */
		public static final ResourceKey<Biome> PITFALLS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0/pitfalls"));

	}

	/**
	 * Biomes for a fun game =)
	 */
	public static class LevelFunBiomes {
		public static final ResourceKey<Biome> HALLWAYS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun/hallways"));

		public static final ResourceKey<Biome> PLAYROOMS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun/playrooms"));

		public static final ResourceKey<Biome> TRAMPOLINE_PARK_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun/trampolines"));

		public static final ResourceKey<Biome> PARTY_ROOMS_BIOME = ResourceKey.create(
				Registries.BIOME,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun/party_rooms"));
	}

	/**
	 * Register biome resource keys
	 */
	public static void RegisterModBiomes() {

	}

}
