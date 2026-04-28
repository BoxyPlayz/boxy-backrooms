package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.boxyplayz.backrooms.world.biomesources.LevelFunBiomeSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class LevelFunChunkGen extends ChunkGenerator {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z, Holder<Biome> biome) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		RandomSource chunkRandom = randomFactory.at((int) chunkX, 0, (int) chunkZ);
		RandomSource blockRandom = randomFactory.at(x, y, z);

		if (biome.is(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME)) {
			if (y <= 0) {
				return ModBlocks.FUN_FLOOR.defaultBlockState();
			}
			if (y >= 6) {
				if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0 && y == 6) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}
			BlockState chunkBaseWallBlock;
			switch (chunkRandom.nextIntBetweenInclusive(1, 4)) {
				case 1:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;

				case 2:
					chunkBaseWallBlock = ModBlocks.FUN_GREEN.defaultBlockState();
					break;

				case 3:
					chunkBaseWallBlock = ModBlocks.FUN_PINK.defaultBlockState();
					break;

				case 4:
					chunkBaseWallBlock = ModBlocks.FUN_PURPLE.defaultBlockState();
					break;

				default:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;
			}

			short cellX = (short) Math.floorDiv(Math.floorMod(x, 16), 4);
			short cellZ = (short) Math.floorDiv(Math.floorMod(z, 16), 4);
			int localX = Math.abs(Math.floorMod(x, 4));
			int localZ = Math.abs(Math.floorMod(z, 4));
			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					0,
					(int) (chunkZ * 4 + cellZ));
			if (getRandomBool(cellRandom) && localZ == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localZ == 3) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localX == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(cellRandom) && localX == 3) {
				return chunkBaseWallBlock;
			}
		}

		if (biome.is(ModBiomes.LevelFunBiomes.TRAMPOLINE_PARK_BIOME)) {
			if (y <= 0) {
				int localX = Math.abs(Math.floorMod(x, 4));
				int localZ = Math.abs(Math.floorMod(z, 4));
				if (localX == 0) {
					return ModBlocks.FUN_GREEN.defaultBlockState();
				}
				if (localZ == 0) {
					return ModBlocks.FUN_PURPLE.defaultBlockState();
				}
				if (y >= -8) {
					return ModBlocks.BLACK_TRAMPOLINE.defaultBlockState();
				}
				return ModBlocks.FUN_FLOOR.defaultBlockState();
			}
			if (y >= 8) {
				if (Math.floorMod(x, 4) == 2 && y == 8) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}

		}

		if (biome.is(ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME)) {
			if (y <= 0) {
				return ModBlocks.FUN_FLOOR.defaultBlockState();
			}
			if (y >= 6) {
				if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0 && y == 6) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
			}
			BlockState chunkBaseWallBlock;
			switch (chunkRandom.nextIntBetweenInclusive(1, 4)) {
				case 1:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;

				case 2:
					chunkBaseWallBlock = ModBlocks.FUN_GREEN.defaultBlockState();
					break;

				case 3:
					chunkBaseWallBlock = ModBlocks.FUN_PINK.defaultBlockState();
					break;

				case 4:
					chunkBaseWallBlock = ModBlocks.FUN_PURPLE.defaultBlockState();
					break;

				default:
					chunkBaseWallBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;
			}
			int localX = Math.abs(Math.floorMod(x, 16));
			int localZ = Math.abs(Math.floorMod(z, 16));
			if (getRandomBool(chunkRandom) && localZ == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(chunkRandom) && localZ == 15) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(chunkRandom) && localX == 0) {
				return chunkBaseWallBlock;
			}
			if (getRandomBool(chunkRandom) && localX == 15) {
				return chunkBaseWallBlock;
			}

			if (y == 1) {
				if (localX == 5 || localX == 6) {
					if ((localZ == 5 || localZ == 6)) {
						return Blocks.POLISHED_BLACKSTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE,
								SlabType.TOP);
					}
					if (!getRandomBool(blockRandom)) {
						if ((localZ == 3)) {
							return Blocks.QUARTZ_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
									Direction.NORTH);
						}
						if ((localZ == 8)) {
							return Blocks.QUARTZ_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
									Direction.SOUTH);
						}
					}
				}
				if (!getRandomBool(blockRandom)) {
					if (localZ == 5 || localZ == 6) {
						if ((localX == 3)) {
							return Blocks.QUARTZ_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST);
						}
						if ((localX == 8)) {
							return Blocks.QUARTZ_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST);
						}
					}
				}

				if (blockRandom.nextIntBetweenInclusive(0, 2500) == 3) {
					return ModBlocks.PROMISED_GATE.defaultBlockState();
				}
			}
		}

		if (biome.is(ModBiomes.LevelFunBiomes.PLAYROOMS_BIOME)) {
			BlockPos cellPos = new BlockPos(Math.floorDiv(x, 4), Math.floorDiv(y, 4), Math.floorDiv(z, 4));
			RandomSource cellRandom = randomFactory.at(cellPos);
			int cellType = cellRandom.nextIntBetweenInclusive(1, 4);
			BlockPos localCellPos = new BlockPos(Math.floorMod(x, 4), Math.floorMod(y, 4),
					Math.floorMod(z, 4));
			BlockState cellBaseBlock;
			switch (cellType) {
				case 1:
					cellBaseBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;

				case 2:
					cellBaseBlock = ModBlocks.FUN_GREEN.defaultBlockState();
					break;

				case 3:
					cellBaseBlock = ModBlocks.FUN_PINK.defaultBlockState();
					break;

				case 4:
					cellBaseBlock = ModBlocks.FUN_PURPLE.defaultBlockState();
					break;

				default:
					cellBaseBlock = ModBlocks.FUN_YELLOW.defaultBlockState();
					break;
			}
			if (y <= 0) {
				return cellBaseBlock;
			}
			if (y >= 110) {
				return cellBaseBlock;
			}
			if (localCellPos.getY() == 3 && getRandomBool(cellRandom)) {
				if ((localCellPos.getX() == 1 || localCellPos.getX() == 2)
						&& (localCellPos.getZ() == 1 || localCellPos.getZ() == 2)) {
					return ModBlocks.LEVEL1_CEILING_LIGHT.defaultBlockState();
				}
				return cellBaseBlock;
			}
			if (localCellPos.getX() == 3 && getRandomBool(cellRandom)) {
				if ((localCellPos.getY() == 1 || localCellPos.getY() == 2)
						&& (localCellPos.getZ() == 1 || localCellPos.getZ() == 2)) {
					return Blocks.GLASS.defaultBlockState();
				}
				return cellBaseBlock;
			}
			if (localCellPos.getX() == 0 && getRandomBool(cellRandom)) {
				if ((localCellPos.getY() == 1 || localCellPos.getY() == 2)
						&& (localCellPos.getZ() == 1 || localCellPos.getZ() == 2)) {
					return Blocks.GLASS.defaultBlockState();
				}
				return cellBaseBlock;
			}
			if (localCellPos.getZ() == 3 && getRandomBool(cellRandom)) {
				if ((localCellPos.getY() == 1 || localCellPos.getY() == 2)
						&& (localCellPos.getX() == 1 || localCellPos.getX() == 2)) {
					return Blocks.GLASS.defaultBlockState();
				}
				return cellBaseBlock;
			}
			if (localCellPos.getZ() == 0 && getRandomBool(cellRandom)) {
				if ((localCellPos.getY() == 1 || localCellPos.getY() == 2)
						&& (localCellPos.getX() == 1 || localCellPos.getX() == 2)) {
					return Blocks.GLASS.defaultBlockState();
				}
				return cellBaseBlock;
			}
			if (localCellPos.getY() == 0 && getRandomBool(cellRandom)) {
				return cellBaseBlock;
			}
			if (localCellPos.getY() == 1 && blockRandom.nextIntBetweenInclusive(1, 26) == 1) {
				return ModBlocks.FUN_CRATE.defaultBlockState();

			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public LevelFunChunkGen(Holder.Reference<Biome> hallways, Holder.Reference<Biome> playrooms,
			Holder.Reference<Biome> trampoline_park, Holder.Reference<Biome> partyrooms) {
		super(new LevelFunBiomeSource(hallways, playrooms, trampoline_park, partyrooms));
	}

	public static final MapCodec<LevelFunChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.HALLWAYS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PLAYROOMS_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.TRAMPOLINE_PARK_BIOME),
					RegistryOps.retrieveElement(ModBiomes.LevelFunBiomes.PARTY_ROOMS_BIOME))
					.apply(instance,
							instance.stable(LevelFunChunkGen::new)));

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
		return 128;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

		int minY = getMinY();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				Holder<Biome> biome = chunkAccess.getNoiseBiome(globalX, 0, globalZ);
				for (int y = minY; y < minY + this.getGenDepth(); y++) {
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
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getMinY() {
		return -16;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

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
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "funandanexit"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

	@Override
	public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
	}

}
