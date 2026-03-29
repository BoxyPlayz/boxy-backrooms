package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModChunkGenerators {
	public static void registerModChunkGenerators() {
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"), Level0ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"), Level7ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"), Level94ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "the_broken"), TheBrokenChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1"), Level1ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"), PitfallsChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_2"), Level0_2ChunkGen.CODEC);
	}
}
