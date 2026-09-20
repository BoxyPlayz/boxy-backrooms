package com.boxyplayz.backrooms.common.world.generators;

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
import com.boxyplayz.backrooms.common.world.generators.custom.LevelRunChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.Level_Negative_0_2ChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.PitfallsChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.PromisedLandChunkGen;
import com.boxyplayz.backrooms.common.world.generators.custom.TheBrokenChunkGen;
import com.mojang.serialization.MapCodec;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class ModChunkGenerators {
	private static final DeferredRegister<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister
			.create(
					BoxysBackroomsCommon.MOD_ID,
					Registries.CHUNK_GENERATOR);

	public static void registerModChunkGenerators() {
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level0_maze"),
				() -> Level0ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level7_ocean"),
				() -> Level7ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level94"),
				() -> Level94ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "the_broken"),
				() -> TheBrokenChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level1"),
				() -> Level1ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "pitfalls"),
				() -> PitfallsChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level0_2"),
				() -> Level0_2ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "negative_level0_2"),
				() -> Level_Negative_0_2ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "blue_channel"),
				() -> BlueChannelChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level6"),
				() -> Level6ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "levelfun"),
				() -> LevelFunChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "promised_land"),
				() -> PromisedLandChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level3"),
				() -> Level3ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level2"),
				() -> Level2ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level4"),
				() -> Level4ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level5"),
				() -> Level5ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level11"),
				() -> Level11ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level9"),
				() -> Level9ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "level10"),
				() -> Level10ChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "the_abyss"),
				() -> AbyssChunkGen.CODEC);
		CHUNK_GENERATORS.register(
				Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "run_for_your_life"),
				() -> LevelRunChunkGen.CODEC);

		CHUNK_GENERATORS.register();

	}
}
