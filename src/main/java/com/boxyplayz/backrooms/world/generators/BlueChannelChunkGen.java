package com.boxyplayz.backrooms.world.generators;

import java.util.HashSet;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

/**
 * Chunk Generation for The Blue Channel
 */
public class BlueChannelChunkGen extends BaseChunkGen {

	HashSet<Block> blocks = new HashSet<>();

	private boolean getRandomBool(RandomSource random) {
		return random.nextIntBetweenInclusive(0, 3) == 0;
	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);
		RandomSource chunkRandom = randomFactory.at(chunkX, 0, chunkZ);

		if (chunkRandom.nextIntBetweenInclusive(0, 160) == 5) {
			int chunkType = chunkRandom.nextIntBetweenInclusive(1, 2);
			int cellX;
			int cellZ;
			int localX;
			int localZ;
			RandomSource cellRandom;
			switch (chunkType) {
				case 1:
					if (y <= 40) {
						return ModBlocks.LEVEL0_CARPET.defaultBlockState();
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
					RandomSource thingSource = randomFactory.at(x, Math.floorDiv(y, 5), z);
					if (thingSource.nextInt(20) == 3) {
						return this.getRandomConcrete(thingSource);
					}
					return Blocks.AIR.defaultBlockState();

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
		blocks.add(Blocks.RED_CONCRETE);
		blocks.add(Blocks.ORANGE_CONCRETE);
		blocks.add(Blocks.YELLOW_CONCRETE);
		blocks.add(Blocks.LIME_CONCRETE);
		blocks.add(Blocks.GREEN_CONCRETE);
		blocks.add(Blocks.CYAN_CONCRETE);
		blocks.add(Blocks.BLUE_CONCRETE);
		blocks.add(Blocks.PURPLE_CONCRETE);
		blocks.add(Blocks.MAGENTA_CONCRETE);
		blocks.add(Blocks.LIGHT_BLUE_CONCRETE);
		blocks.add(Blocks.PINK_CONCRETE);
	}

	private BlockState getRandomConcrete(RandomSource random) {
		BlockState state = Blocks.BLACK_CONCRETE.defaultBlockState();
		for (Block b : blocks) {
			if (random.nextIntBetweenInclusive(0, 5) == 3) {
				state = b.defaultBlockState();
			}
		}
		return state;
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
	public int getMinY() {
		return 0;
	}

	@Override
	public String getSeed() {
		return "bluechannel";
	}

}
