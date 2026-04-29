package com.boxyplayz.backrooms.world.generators;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

/**
 * Chunk Generation for The Blue Channel
 */
public class BlueChannelChunkGen extends BaseChunkGen {
	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromHashOf("blueChannel");
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z, Holder<Biome> biome) {
		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);
		RandomSource chunkRandom = randomFactory.at(chunkX, 0, chunkZ);

		if (chunkRandom.nextIntBetweenInclusive(0, 40) == 5) {
			int chunkType = chunkRandom.nextIntBetweenInclusive(1, 4);
			int cellX;
			int cellZ;
			int localX;
			int localZ;
			RandomSource cellRandom;
			RandomSource blockRandom = randomFactory.at(x, y, z);
			switch (chunkType) {
				case 1:
					if (y <= 40) {
						return ModBlocks.LEVEL0_CARPET.defaultBlockState();
					}
					if (y >= 44) {
						if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
							return ModBlocks.LEVEL0_CEILING_LIGHT.defaultBlockState();
						}
						return ModBlocks.LEVEL0_CEILING_TILE.defaultBlockState();
					}
					cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
					cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

					localX = Math.abs(Math.floorMod(x, 4));
					localZ = Math.abs(Math.floorMod(z, 4));

					cellRandom = randomFactory.at(
							(int) (chunkX * 4 + cellX),
							0,
							(int) (chunkZ * 4 + cellZ));

					if (getRandomBool(cellRandom) && localZ == 0) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localZ == 3) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localX == 0) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localX == 3) {
						return ModBlocks.LEVEL0_WALLPAPER.defaultBlockState();
					}
					break;

				case 2:
					if (y <= 40) {
						return ModBlocks.INFERIOR_CARPET.defaultBlockState();
					}
					if (y >= 44) {
						if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
							return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
						}
						return ModBlocks.INFERIOR_CEILING_TILE.defaultBlockState();
					}
					cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
					cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

					localX = Math.abs(Math.floorMod(x, 4));
					localZ = Math.abs(Math.floorMod(z, 4));

					cellRandom = randomFactory.at(
							(int) (chunkX * 4 + cellX),
							0,
							(int) (chunkZ * 4 + cellZ));

					if (getRandomBool(cellRandom) && localZ == 0) {
						return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localZ == 3) {
						return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localX == 0) {
						return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
					}
					if (getRandomBool(cellRandom) && localX == 3) {
						return ModBlocks.INFERIOR_WALLPAPER.defaultBlockState();
					}
					break;

				case 3:
					if (y <= 40) {
						if (y <= 20) {
							return ModBlocks.OCEAN_TRANSPORTER.defaultBlockState();
						}
						return Blocks.WATER.defaultBlockState();
					}
					break;

				case 4:
					double recievedValue = this.getNoise(randomFactory).getValue(x * 0.1, y * 0.1, z * 0.1);
					if (recievedValue > 0.2) {
						if (blockRandom.nextIntBetweenInclusive(0, 10) == 1) {
							return ModBlocks.ERRORSLATE.defaultBlockState();
						} else {
							return Blocks.DEEPSLATE.defaultBlockState();
						}
					} else {
						if (y < 5) {
							return ModBlocks.PURE_BLUE.defaultBlockState();
						} else {
							return Blocks.AIR.defaultBlockState();
						}
					}

				default:
					return Blocks.AIR.defaultBlockState();
			}
		}
		// Floor
		if (y <= 40) {
			return ModBlocks.PURE_BLUE.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public BlueChannelChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<BlueChannelChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.BLUE_CHANNEL_BIOME))
					.apply(instance,
							instance.stable(BlueChannelChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 256;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue"));

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
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		for (int y = getMinY() + getGenDepth() - 1; y >= getMinY(); y--) {
			if (!getBlockAt(worldSeed, x, y, z).isAir()) {
				return y + 1;
			}
		}

		return this.getMinY();
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

}
