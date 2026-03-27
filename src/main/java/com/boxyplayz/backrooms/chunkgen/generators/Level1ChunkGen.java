package com.boxyplayz.backrooms.chunkgen.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.biome.ModBiomes;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.chunkgen.biomesources.Level1BiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class Level1ChunkGen extends ChunkGenerator {

	private SimplexNoise noise;

	protected SimplexNoise getNoise(PositionalRandomFactory worldSeed) {
		if (this.noise == null) {
			RandomSource random = worldSeed.fromSeed(1);
			this.noise = new SimplexNoise(random);
		}
		return this.noise;
	}

	public BlockState getBlockAt(SimplexNoise localNoise, PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(localNoise, randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(SimplexNoise localNoise, PositionalRandomFactory randomFactory, int x, int y, int z,
			Holder<Biome> biome) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		long localChunkX = Math.floorMod(x, 16);
		long localChunkZ = Math.floorMod(z, 16);

		RandomSource layerChunkRandom = randomFactory
				.fromSeed(chunkX * 341873128712L + chunkZ * 132897987541L + Math.floorDiv(y, 7) * 47323);

		boolean rampOnSegment = layerChunkRandom.nextIntBetweenInclusive(0, 50) == 0;

		RandomSource belowLayerChunkRandom = randomFactory
				.fromSeed(chunkX * 341873128712L + chunkZ * 132897987541L + (Math.floorDiv(y, 7) - 1) * 47323);

		boolean rampOnLowerSegment = belowLayerChunkRandom.nextIntBetweenInclusive(0, 50) == 0;

		// Floor
		if (Math.floorMod(y, 7) == 0) {
			if (rampOnLowerSegment && !(y == getMinY()) && !(y == getMinY() + getGenDepth() - 1)) {
				if (localChunkX > 4 && localChunkX < 12) {
					return Blocks.AIR.defaultBlockState();
				}
			}
			return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
		}

		// Ceiling
		if (Math.floorMod(y, 7) == 6) {
			if (rampOnSegment) {
				if (localChunkX > 4 && localChunkX < 12) {
					if (localChunkZ == 15) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					return Blocks.AIR.defaultBlockState();
				}
			}
			return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
		}

		if (rampOnSegment) {
			if (localChunkX > 4 && localChunkX < 12) {
				if (localChunkZ > 4 && localChunkZ <= 6) {
					if (Math.floorMod(y, 7) == 1) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					if (Math.floorMod(y, 7) > 1) {
						return Blocks.AIR.defaultBlockState();
					}
				}
				if (localChunkZ > 6 && localChunkZ <= 8) {
					if (Math.floorMod(y, 7) == 2) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					if (Math.floorMod(y, 7) > 2) {
						return Blocks.AIR.defaultBlockState();
					}
				}
				if (localChunkZ > 8 && localChunkZ <= 10) {
					if (Math.floorMod(y, 7) == 3) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					if (Math.floorMod(y, 7) > 3) {
						return Blocks.AIR.defaultBlockState();
					}
				}
				if (localChunkZ > 10 && localChunkZ <= 12) {
					if (Math.floorMod(y, 7) == 4) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					if (Math.floorMod(y, 7) > 4) {
						return Blocks.AIR.defaultBlockState();
					}
				}
				if (localChunkZ > 12 && localChunkZ < 16) {
					if (Math.floorMod(y, 7) == 5) {
						return ModBlocks.LEVEL1_FLOOR_AQUILA.defaultBlockState();
					}
					if (Math.floorMod(y, 7) > 5) {
						return Blocks.AIR.defaultBlockState();
					}
				}
			}
		}

		// Fallback
		if ((Math.floorMod(x, 8) == 0 || Math.floorMod(x, 8) == 1)
				&&
				(Math.floorMod(z, 8) == 0 || Math.floorMod(z, 8) == 1) && !(rampOnLowerSegment)) {
			return ModBlocks.LEVEL1_PILLAR_AQUILA.defaultBlockState();
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
	public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager,
			StructureManager structureManager, ChunkAccess chunkAccess) {
	}

	@Override
	public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState,
			ChunkAccess chunkAccess) {
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
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
					BlockState block = getBlockAt(this.getNoise(worldSeed), worldSeed, globalX, y, globalZ, biome);
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
	public int getSeaLevel() {
		return 0;
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
			if (!getBlockAt(this.getNoise(worldSeed), worldSeed, x, y, z).isAir()) {
				return y + 1;
			}

		}

		return this.getMinY();
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1seed"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(this.getNoise(worldSeed), worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
