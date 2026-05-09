package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class PitfallsChunkGen extends BaseChunkGen {

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
	public int getGenDepth() {
		return 80;
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	String getSeed() {
		return "pitfalls";
	}

}
