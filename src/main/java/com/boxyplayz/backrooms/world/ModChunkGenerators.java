package com.boxyplayz.backrooms.world;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.world.generators.BlueChannelChunkGen;
import com.boxyplayz.backrooms.world.generators.Level0ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level0_2ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level11ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level1ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level2ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level3ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level4ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level5ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level6ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level7ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level94ChunkGen;
import com.boxyplayz.backrooms.world.generators.Level9ChunkGen;
import com.boxyplayz.backrooms.world.generators.LevelFunChunkGen;
import com.boxyplayz.backrooms.world.generators.Level_Negative_0_2ChunkGen;
import com.boxyplayz.backrooms.world.generators.PitfallsChunkGen;
import com.boxyplayz.backrooms.world.generators.PromisedLandChunkGen;
import com.boxyplayz.backrooms.world.generators.TheBrokenChunkGen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModChunkGenerators {
	public static void registerModChunkGenerators() {
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"),
				Level0ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"),
				Level7ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"),
				Level94ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "the_broken"),
				TheBrokenChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1"),
				Level1ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"),
				PitfallsChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_2"),
				Level0_2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "negative_level0_2"),
				Level_Negative_0_2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue_channel"),
				BlueChannelChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level6"),
				Level6ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun"),
				LevelFunChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "promised_land"),
				PromisedLandChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level3"),
				Level3ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level2"),
				Level2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level4"),
				Level4ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level5"),
				Level5ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level11"),
				Level11ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level9"),
				Level9ChunkGen.CODEC);

	}
}
