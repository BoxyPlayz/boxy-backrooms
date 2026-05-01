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

public class Level3ChunkGen extends BaseChunkGen {

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 5) == 0;
	}

	/**
	 * Gets block from coordinates
	 * 
	 * @param randomFactory Random Factory
	 * @param x             X Coordinate
	 * @param y             Y Coordinate
	 * @param z             Z Coordinate
	 * @return Blockstate
	 */
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		BlockPos cellPos = new BlockPos(Math.floorDiv(x, 4), Math.floorDiv(y, 4), Math.floorDiv(z, 4));
		RandomSource cellRandom = randomFactory.at(cellPos);
		int cellType = cellRandom.nextIntBetweenInclusive(1, 4);
		BlockPos localCellPos = new BlockPos(Math.floorMod(x, 4), Math.floorMod(y, 4),
				Math.floorMod(z, 4));
		BlockState cellBaseBlock;
		switch (cellType) {
			case 1:
				cellBaseBlock = Blocks.IRON_BARS.defaultBlockState();
				break;

			default:
				cellBaseBlock = ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
				break;
		}
		if (y <= 0) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (y >= 4) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}
		if (localCellPos.getX() == 3 && getRandomBool(cellRandom)) {
			return cellBaseBlock;
		}
		if (localCellPos.getX() == 0 && getRandomBool(cellRandom)) {
			return cellBaseBlock;
		}
		if (localCellPos.getZ() == 3 && getRandomBool(cellRandom)) {
			return cellBaseBlock;
		}
		if (localCellPos.getZ() == 0 && getRandomBool(cellRandom)) {
			return cellBaseBlock;
		}

		return Blocks.AIR.defaultBlockState();
	}

	public Level3ChunkGen(Holder.Reference<Biome> hallways) {
		super(new FixedBiomeSource(hallways));
	}

	public static final MapCodec<Level3ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL3_BIOME))
					.apply(instance,
							instance.stable(Level3ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 32;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState,
			StructureManager structureManager, ChunkAccess chunkAccess) {
		PositionalRandomFactory worldSeed = randomState
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "electrikal"));

		int minY = getMinY();

		int chunkMinX = chunkAccess.getPos().getMinBlockX();
		int chunkMinZ = chunkAccess.getPos().getMinBlockZ();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int globalX = chunkMinX + x;
				int globalZ = chunkMinZ + z;
				for (int y = minY; y < minY + this.getGenDepth(); y++) {
					BlockState block = getBlockAt(worldSeed, globalX, y, globalZ);
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
		return -16;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor levelHeightAccessor,
			RandomState randomState) {
		PositionalRandomFactory worldSeed = randomState.getOrCreateRandomFactory(
				Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "electrikal"));

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
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "electrikal"));

		int height = this.getGenDepth();
		BlockState[] blocks = new BlockState[height];

		for (int y = getMinY(); y < height + this.getMinY(); y++) {
			blocks[y - this.getMinY()] = getBlockAt(worldSeed, x, y, z);
		}

		return new NoiseColumn(
				levelHeightAccessor.getMinY(), blocks);
	}

}
