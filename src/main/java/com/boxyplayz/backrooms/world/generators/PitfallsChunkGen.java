package com.boxyplayz.backrooms.world.generators;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
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
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class PitfallsChunkGen extends ChunkGenerator {

	protected BlockState getRandomWallBlock(RandomSource random) {
		WallBlock blockType = WallBlock.values()[random.nextInt(0, WallBlock.values().length)];
		BlockState state = Blocks.OAK_LOG.defaultBlockState();
		switch (blockType) {
			case WallBlock.OAK:
				state = Blocks.OAK_LOG.defaultBlockState();
				break;

			case WallBlock.BIRCH:
				state = Blocks.BIRCH_LOG.defaultBlockState();
				break;

			case WallBlock.DARK_OAK:
				state = Blocks.DARK_OAK_LOG.defaultBlockState();
				break;

			case WallBlock.SPRUCE:
				state = Blocks.SPRUCE_LOG.defaultBlockState();
				break;

			case WallBlock.ACACIA:
				state = Blocks.ACACIA_LOG.defaultBlockState();
				break;

			case WallBlock.JUNGLE:
				state = Blocks.JUNGLE_LOG.defaultBlockState();
				break;

			case WallBlock.MANGROVE:
				state = Blocks.MANGROVE_LOG.defaultBlockState();
				break;

			case WallBlock.CHERRY:
				state = Blocks.CHERRY_LOG.defaultBlockState();
				break;

			case WallBlock.PALE:
				state = Blocks.PALE_OAK_LOG.defaultBlockState();
				break;

			case WallBlock.CRIMSON:
				state = Blocks.CRIMSON_STEM.defaultBlockState();
				break;

			case WallBlock.WARPED:
				state = Blocks.WARPED_STEM.defaultBlockState();
				break;

			default:
				state = Blocks.OAK_LOG.defaultBlockState();
				break;
		}

		return state;
	}

	public enum WallBlock {
		OAK,
		BIRCH,
		DARK_OAK,
		SPRUCE,
		ACACIA,
		JUNGLE,
		MANGROVE,
		CHERRY,
		PALE,
		CRIMSON,
		WARPED
	}

	protected boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 9) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Holder<Biome> biome = this.getBiomeSource().getNoiseBiome(x, y, z, null);
		return getBlockAt(randomFactory, x, y, z, biome);
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z, Holder<Biome> biome) {
		long chunkX = Math.floorDiv(x, 16);
		long chunkZ = Math.floorDiv(z, 16);

		// Floor
		if (y == 0) {
			return Blocks.BEDROCK.defaultBlockState();
		}
		if (y < 8) {
			return Blocks.DIRT.defaultBlockState();
		}
		if (y == 8) {
			return Blocks.GRASS_BLOCK.defaultBlockState();
		}
		if (Math.abs(x) > 16 || Math.abs(z) > 16) {
			int cellX = Math.floorDiv(Math.floorMod(x, 16), 4);
			int cellZ = Math.floorDiv(Math.floorMod(z, 16), 4);

			int localX = Math.abs(Math.floorMod(x, 4));
			int localZ = Math.abs(Math.floorMod(z, 4));

			RandomSource cellRandom = randomFactory.at(
					(int) (chunkX * 4 + cellX),
					0,
					(int) (chunkZ * 4 + cellZ));

			long unOffsettedValue = (x) * 4543214L + (z) * 3632454334523L;
			if (getRandomBool(cellRandom) && localZ == 0) {
				RandomSource wallRandom = randomFactory
						.fromSeed(unOffsettedValue);
				return this.getRandomWallBlock(wallRandom);
			}
			if (getRandomBool(cellRandom) && localZ == 3) {
				RandomSource wallRandom = randomFactory
						.fromSeed(unOffsettedValue);
				return this.getRandomWallBlock(wallRandom);
			}
			if (getRandomBool(cellRandom) && localX == 0) {
				RandomSource wallRandom = randomFactory
						.fromSeed(unOffsettedValue);
				return this.getRandomWallBlock(wallRandom);
			}
			if (getRandomBool(cellRandom) && localX == 3) {
				RandomSource wallRandom = randomFactory
						.fromSeed(unOffsettedValue);
				return this.getRandomWallBlock(wallRandom);
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	public PitfallsChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
	}

	public static final MapCodec<PitfallsChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.PITFALLS_BIOME))
					.apply(instance,
							instance.stable(PitfallsChunkGen::new)));

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
		return 80;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfallsseed"));

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
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfallsseed"));

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
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfallsseed"));

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
