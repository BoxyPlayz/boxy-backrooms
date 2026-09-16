package com.boxyplayz.backrooms.world;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.world.generators.custom.AbyssChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.BlueChannelChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level0ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level0_2ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level10ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level11ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level1ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level2ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level3ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level4ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level5ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level6ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level7ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level94ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level9ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.LevelFunChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level_Negative_0_2ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.PitfallsChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.PromisedLandChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.TheBrokenChunkGen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModChunkGenerators {
	public static void registerModChunkGenerators() {
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level0_maze"),
				Level0ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level7_ocean"),
				Level7ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level94"),
				Level94ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "the_broken"),
				TheBrokenChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level1"),
				Level1ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "pitfalls"),
				PitfallsChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level0_2"),
				Level0_2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "negative_level0_2"),
				Level_Negative_0_2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "blue_channel"),
				BlueChannelChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level6"),
				Level6ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "levelfun"),
				LevelFunChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "promised_land"),
				PromisedLandChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level3"),
				Level3ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level2"),
				Level2ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level4"),
				Level4ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level5"),
				Level5ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level11"),
				Level11ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level9"),
				Level9ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level10"),
				Level10ChunkGen.CODEC);
		Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "the_abyss"),
				AbyssChunkGen.CODEC);

	}
}
