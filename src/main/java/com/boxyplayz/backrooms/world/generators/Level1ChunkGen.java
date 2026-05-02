package com.boxyplayz.backrooms.world.generators;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.biomesources.Level1BiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class Level1ChunkGen extends BaseChunkGen {

	protected boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	protected boolean getRandomBool(RandomSource random, int max) {
		return random.nextIntBetweenInclusive(0, max) == 0;
	}

	protected boolean getRandom(RandomSource random) {
		return (random.nextIntBetweenInclusive(1, 10) == 1);
	}

	protected BlockState getRandomWood(RandomSource random) {
		int woodId = random.nextInt(1, 3);
		switch (woodId) {
			case 1:
				return Blocks.OAK_PLANKS.defaultBlockState();

			case 2:
				return Blocks.SPRUCE_PLANKS.defaultBlockState();

			case 3:
				return Blocks.DARK_OAK_PLANKS.defaultBlockState();
			default:
				return Blocks.AIR.defaultBlockState();
		}
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z,
			Holder<Biome> biome) {
		if (y > 7) {
			return Blocks.BEDROCK.defaultBlockState();
		}
		int localX = Math.floorMod(x, 16);
		int localZ = Math.floorMod(z, 16);
		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);

		if (biome.is(ModBiomes.Level1Biomes.AQUILA_BIOME)) {
			// Floor
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				Range<Integer> range = Range.of(0, 3);
				if (range.contains(Math.floorMod(x, 8))
						&&
						range.contains(Math.floorMod(z, 8))) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}

			if ((Math.floorMod(x, 8) == 1 || Math.floorMod(x, 8) == 2)
					&&
					(Math.floorMod(z, 8) == 1 || Math.floorMod(z, 8) == 2)) {
				return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
			}
		} else if (biome.is(ModBiomes.Level1Biomes.GILDED_BIOME)) {
			RandomSource random = randomFactory.at(Math.floorDiv(x, 16), Math.floorDiv(y, 7), Math.floorDiv(z, 16));

			// Floor
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}

			// Walls
			if (getRandom(random)) {
				if (localX == 15) {
					return ModBlocks.LEVEL1_WALL_GILD.defaultBlockState();
				}
			}
			if (getRandom(random)) {
				if (localX == 0) {
					return ModBlocks.LEVEL1_WALL_GILD.defaultBlockState();
				}
			}
			if (getRandom(random)) {
				if (localZ == 15) {
					return ModBlocks.LEVEL1_WALL_GILD.defaultBlockState();
				}
			}
			if (getRandom(random)) {
				if (localZ == 0) {
					return ModBlocks.LEVEL1_WALL_GILD.defaultBlockState();
				}
			}

			if (Math.floorMod(y, 7) == 5) {
				if (Math.floorMod(localZ, 4) == 0) {
					if (Math.floorMod(Math.floorDiv(localX, 2), 4) == 0) {
						return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
					}
				}
			}
		} else if (biome.is(ModBiomes.Level1Biomes.GOTHIC_BIOME)) {
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
			}

			if (Math.floorMod(localX, 7) == 3 && Math.floorMod(localZ, 7) == 3) {
				return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
			}

			Range<Integer> baseRange = Range.of(2, 4);
			Range<Integer> lightRange = Range.of(1, 5);

			if (lightRange.contains(Math.floorMod(localX, 7)) && lightRange.contains(Math.floorMod(localZ, 7))) {
				if (baseRange.contains(Math.floorMod(localX, 7)) && baseRange.contains(Math.floorMod(localZ, 7))) {
					if (Math.floorMod(y, 7) == 1 || Math.floorMod(y, 7) == 5) {
						return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
					}
				} else {
					if (Math.floorMod(y, 7) == 6) {
						return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
					}
				}
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
			}
		} else if (biome.is(ModBiomes.Level1Biomes.OUROBOROS_BIOME)) {
			RandomSource blockRandom = randomFactory.at(x, y, z);
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			RandomSource pillarRandom = randomFactory.at(x, Math.floorDiv(y, 7), z);

			if (pillarRandom.nextIntBetweenInclusive(1, 20) == 1) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			int cellX = Math.floorDiv(Math.floorMod(x, 16), 8);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), 8);

			int localerX = Math.abs(Math.floorMod(x, 8));
			int localerZ = Math.abs(Math.floorMod(z, 8));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					Math.floorDiv(y, 7),
					(int) (chunkZ * 4 + cellZ));

			if (getRandomBool(cellRandom, 10) && localerZ == 0) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 10) && localerZ == 7) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 10) && localerX == 0) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 10) && localerX == 7) {
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				if (blockRandom.nextIntBetweenInclusive(0, 8) == 1) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.AGED_CONCRETE.defaultBlockState();
			}
		} else if (biome.is(ModBiomes.Level1Biomes.GARDEN_BIOME)) {
			RandomSource blockRandom = randomFactory.at(x, y, z);
			// Floor
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.PURE_GRASS.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
			}

			int cellX = Math.floorDiv(Math.floorMod(x, 16), 8);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), 8);

			int localerX = Math.abs(Math.floorMod(x, 8));
			int localerZ = Math.abs(Math.floorMod(z, 8));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					Math.floorDiv(y, 7),
					(int) (chunkZ * 4 + cellZ));

			if (getRandomBool(cellRandom, 3) && localerZ == 0) {
				return ModBlocks.GARDEN_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 3) && localerZ == 7) {
				return ModBlocks.GARDEN_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 3) && localerX == 0) {
				return ModBlocks.GARDEN_CONCRETE.defaultBlockState();
			}
			if (getRandomBool(cellRandom, 3) && localerX == 7) {
				return ModBlocks.GARDEN_CONCRETE.defaultBlockState();
			}

			if (Math.floorMod(y, 7) == 1 && blockRandom.nextBoolean()) {
				int randomVal = blockRandom.nextIntBetweenInclusive(1, 20);
				switch (randomVal) {
					case 1:
						return Blocks.SHORT_GRASS.defaultBlockState();

					case 2:
						return Blocks.FERN.defaultBlockState();

					case 3:
						return Blocks.FERN.defaultBlockState();

					case 4:
						return Blocks.DANDELION.defaultBlockState();

					case 5:
						return Blocks.GOLDEN_DANDELION.defaultBlockState();

					case 6:
						return Blocks.POPPY.defaultBlockState();

					case 7:
						return Blocks.BLUE_ORCHID.defaultBlockState();

					case 8:
						return Blocks.ALLIUM.defaultBlockState();

					case 9:
						return Blocks.AZURE_BLUET.defaultBlockState();

					case 10:
						return Blocks.RED_TULIP.defaultBlockState();

					case 11:
						return Blocks.ORANGE_TULIP.defaultBlockState();

					case 12:
						return Blocks.WHITE_TULIP.defaultBlockState();

					case 13:
						return Blocks.PINK_TULIP.defaultBlockState();

					case 14:
						return Blocks.OXEYE_DAISY.defaultBlockState();

					case 15:
						return Blocks.CORNFLOWER.defaultBlockState();

					case 16:
						return Blocks.WITHER_ROSE.defaultBlockState();

					case 17:
						return Blocks.LILY_OF_THE_VALLEY.defaultBlockState();

					case 18:
						return Blocks.TORCHFLOWER.defaultBlockState();

					case 19:
						return Blocks.PINK_PETALS.defaultBlockState();

					case 20:
						return Blocks.WILDFLOWERS.defaultBlockState();

					default:
						break;
				}
			}

		} else if (biome.is(ModBiomes.Level1Biomes.FABLED_BIOME)) {
			// Floor
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				if (Math.floorMod(localZ, 4) == 0) {
					if (Math.floorMod(Math.floorDiv(localX, 2), 4) == 0) {
						return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
					}
				}

				return Blocks.SPRUCE_PLANKS.defaultBlockState();
			}

			int cellSize = 4;

			int cellX = Math.floorDiv(Math.floorMod(x, 16), cellSize);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), cellSize);

			int localerX = Math.abs(Math.floorMod(x, cellSize));
			int localerZ = Math.abs(Math.floorMod(z, cellSize));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					Math.floorDiv(y, 7),
					(int) (chunkZ * 4 + cellZ));

			if (getRandomBool(cellRandom) && localerZ == 0) {
				RandomSource randomWallSource = randomFactory.at(x, Math.floorDiv(y, 7), Math.floorDiv(z, cellSize));
				return getRandomWood(randomWallSource);
			}
			if (getRandomBool(cellRandom) && localerZ == cellSize - 1) {
				RandomSource randomWallSource = randomFactory.at(x, Math.floorDiv(y, 7), Math.floorDiv(z, cellSize));
				return getRandomWood(randomWallSource);
			}
			if (getRandomBool(cellRandom) && localerX == 0) {
				RandomSource randomWallSource = randomFactory.at(Math.floorDiv(x, cellSize), Math.floorDiv(y, 7), z);
				return getRandomWood(randomWallSource);
			}
			if (getRandomBool(cellRandom) && localerX == cellSize - 1) {
				RandomSource randomWallSource = randomFactory.at(Math.floorDiv(x, cellSize), Math.floorDiv(y, 7), z);
				return getRandomWood(randomWallSource);
			}

		} else {
			// Floor
			if (Math.floorMod(y, 7) == 0) {
				return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
			}

			// Ceiling
			if (Math.floorMod(y, 7) == 6) {
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}

		}

		if (Math.floorMod(y, 7) == 1) {
			RandomSource random = randomFactory.at(x, y, z);
			if (random.nextIntBetweenInclusive(0, 1000) == 1) {
				return ModBlocks.LEVEL1_CRATE.defaultBlockState();
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level1ChunkGen(Holder.Reference<Biome> aquila, Holder.Reference<Biome> garden,
			Holder.Reference<Biome> fabled, Holder.Reference<Biome> ouroboros, Holder.Reference<Biome> gothic,
			Holder.Reference<Biome> gilded) {
		super(new Level1BiomeSource(aquila, garden, fabled, ouroboros, gothic, gilded));
	}

	public static final MapCodec<Level1ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.AQUILA_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GARDEN_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.FABLED_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.OUROBOROS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GOTHIC_BIOME),
					RegistryOps.retrieveElement(ModBiomes.Level1Biomes.GILDED_BIOME)).apply(instance,
							instance.stable(Level1ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 64;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		int minY = getMinY();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = minY; y < minY + this.getGenDepth(); y++) {
					Holder<Biome> biome = chunkAccess.getNoiseBiome(globalX, y, globalZ);
					BlockState block = getBlockAt(worldSeed, globalX, y, globalZ, biome);
					chunkAccess.setBlockState(
							new BlockPos(x, y, z),
							block,
							0);
				}
			}
		}

		chunkAccess.getOrCreateHeightmapUnprimed(Types.WORLD_SURFACE_WG);
		chunkAccess.getOrCreateHeightmapUnprimed(Types.OCEAN_FLOOR_WG);
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		for (int y = getMinY() + getGenDepth() - 1; y >= getMinY(); y--) {
			if (!getBlockAt(worldSeed, x, y, z).isAir()) {
				return y + 1;
			}

		}

		return this.getMinY();
	}

	@Override
	String getSeed() {
		return "level1";
	}

}
