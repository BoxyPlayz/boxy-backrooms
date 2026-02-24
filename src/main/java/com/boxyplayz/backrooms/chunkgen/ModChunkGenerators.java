package com.boxyplayz.backrooms.chunkgen;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.chunkgen.generators.Level0ChunkGen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModChunkGenerators {
	public static void registerModChunkGenerators() {
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"), Level0ChunkGen.CODEC);
	}
}
