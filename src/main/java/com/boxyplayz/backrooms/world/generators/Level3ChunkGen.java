package com.boxyplayz.backrooms.world.generators;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.ModBlockEntities;
import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class Level3ChunkGen extends BaseChunkGen {
	private BlockState getWallBlock(RandomSource random, BlockPos pos) {

		short randomNum = (short) random.nextIntBetweenInclusive(0, 100);
		if (randomNum < 20) {
			return Blocks.IRON_BLOCK.defaultBlockState();
		}
		if (randomNum < 30) {
			if (pos.getY() > 2) {
				return Blocks.IRON_BLOCK.defaultBlockState();
			}
			RandomSource horizRandom = random.forkPositional().at(pos.getX(), 0, pos.getZ());
			byte number = (byte) horizRandom.nextIntBetweenInclusive(0, 3);
			if (Math.floorMod(pos.getX(), 4) == number || Math.floorMod(pos.getZ(), 4) == number) {
				return Blocks.AIR.defaultBlockState();
			}
			return Blocks.IRON_BLOCK.defaultBlockState();
		}
		RandomSource blockRandom = random.forkPositional().at(pos);
		if (blockRandom.nextIntBetweenInclusive(1, 160) == 3) {
			return ModBlocks.POWER_OUTLET_BLOCK.defaultBlockState();
		}
		return ModBlocks.ELECTRICAL_BRICKS.defaultBlockState();
	}

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(1, 2) == 1;
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
		BlockPos localCellPos = new BlockPos(Math.floorMod(x, 4), Math.floorMod(y, 4),
				Math.floorMod(z, 4));

		RandomSource blockRandom = randomFactory.at(x, y, z);

		if (y <= 0) {
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		if (y >= 4) {
			if (blockRandom.nextIntBetweenInclusive(1, 24) == 2) {
				return ModBlocks.LEVEL3_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.GOTHIC_CONCRETE.defaultBlockState();
		}

		RandomSource eastRandom = randomFactory.at(cellPos.getX(), 0, cellPos.getZ());

		RandomSource southRandom = randomFactory.at(cellPos.getX(), 1, cellPos.getZ());

		if (localCellPos.getX() == 3 && getRandomBool(eastRandom)) {
			return getWallBlock(eastRandom, new BlockPos(x, y, z));
		}

		if (localCellPos.getZ() == 3 && getRandomBool(southRandom)) {
			return getWallBlock(southRandom, new BlockPos(x, y, z));
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
					if (block.is(ModBlocks.POWER_OUTLET_BLOCK)) {
						BlockEntity blockEntity = ModBlockEntities.POWER_OUTLET_BLOCK_ENTITY
								.create(new BlockPos(globalX, y, globalZ), block);
						if (blockEntity != null) {
							chunkAccess.setBlockEntity(blockEntity);
						}
					}
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
	String getSeed() {
		return "level3";
	}

}
