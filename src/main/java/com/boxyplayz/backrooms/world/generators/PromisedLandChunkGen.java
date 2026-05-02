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

public class PromisedLandChunkGen extends BaseChunkGen {
	protected boolean isVOID(PositionalRandomFactory factory, int chunkX, int chunkZ) {
		RandomSource random = factory.at(chunkX, 0, chunkZ);
		return random.nextInt(8) == 0;
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

		RandomSource chunkRandom = randomFactory.at((int) chunkX, 0, (int) chunkZ);

		if (isVOID(randomFactory, chunkX, chunkZ)) {
			return Blocks.AIR.defaultBlockState();
		}

		// Floor
		if (y <= 0) {
			return ModBlocks.PROMISED_CARPET.defaultBlockState();
		}

		// Ceiling
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 2 && Math.floorMod(z, 4) == 2) {
				return ModBlocks.PROMISED_CEILING_LIGHT.defaultBlockState();
			}
			return ModBlocks.PROMISED_CEILING_TILE.defaultBlockState();
		}

		if (isVOID(randomFactory, chunkX - 1, chunkZ) && Math.floorMod(x, 16) == 0) {
			return Blocks.PINK_STAINED_GLASS.defaultBlockState();
		}

		if (isVOID(randomFactory, chunkX + 1, chunkZ) && Math.floorMod(x, 16) == 15) {
			return Blocks.PINK_STAINED_GLASS.defaultBlockState();
		}

		if (isVOID(randomFactory, chunkX, chunkZ + 1) && Math.floorMod(z, 16) == 15) {
			return Blocks.PINK_STAINED_GLASS.defaultBlockState();
		}

		if (isVOID(randomFactory, chunkX, chunkZ - 1) && Math.floorMod(z, 16) == 0) {
			return Blocks.PINK_STAINED_GLASS.defaultBlockState();
		}

		int localX = Math.floorMod(x, 16);
		int localZ = Math.floorMod(z, 16);

		if (getRandomBool(chunkRandom) && localZ == 0) {
			return ModBlocks.PROMISED_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(chunkRandom) && localZ == 15) {
			return ModBlocks.PROMISED_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(chunkRandom) && localX == 0) {
			return ModBlocks.PROMISED_WALLPAPER.defaultBlockState();
		}
		if (getRandomBool(chunkRandom) && localX == 15) {
			return ModBlocks.PROMISED_WALLPAPER.defaultBlockState();
		}

		return Blocks.AIR.defaultBlockState();
	}

	public PromisedLandChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<PromisedLandChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.PROMISED_BIOME))
					.apply(instance,
							instance.stable(PromisedLandChunkGen::new)));

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
				.getOrCreateRandomFactory(Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0seed"));

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
		return -16;
	}

	@Override
	String getSeed() {
		return "imadeapromise";
	}

}
