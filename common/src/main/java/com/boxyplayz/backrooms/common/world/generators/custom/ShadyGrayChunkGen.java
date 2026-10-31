package com.boxyplayz.backrooms.common.world.generators.custom;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.common.world.ModBiomes;
import com.boxyplayz.backrooms.common.world.biomesources.ShadyGrayBiomeSource;
import com.boxyplayz.backrooms.common.world.generators.BaseChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import net.minecraft.world.phys.Vec2;

public class ShadyGrayChunkGen extends BaseChunkGen {

	public static int ZEROZERO_START = 384;
	public static int TWOTWO_START = 256;
	public static int THREETHREE_START = 160;
	public static int FOURFOUR_START = 0;

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(this.getSeed());
			this.noise = new SimplexNoise(random);
		}

		return this.noise;

	}

	public static final MapCodec<ShadyGrayChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.ShadyGrayBiomes.ZEROZERO),
					RegistryOps.retrieveElement(ModBiomes.ShadyGrayBiomes.TWOTWO))
					.apply(instance,
							instance.stable(ShadyGrayChunkGen::new)));

	public ShadyGrayChunkGen(Holder<Biome> zero, Holder<Biome> two) {
		super(new ShadyGrayBiomeSource(zero, two, zero, zero));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int chunkX = Math.floorDiv(x, 16);
		int chunkZ = Math.floorDiv(z, 16);
		int localX = Math.floorMod(x, 16);
		int localZ = Math.floorMod(z, 16);

		if (y >= ZEROZERO_START) {
			if (y == ZEROZERO_START) {
				return Blocks.BEDROCK.defaultBlockState();
			}
			double value = ZEROZERO_START + (1.01 + getNoise(randomFactory).getValue(x * 0.002, z * 0.002)) * 20;
			if (y < value) {
				if (!(y + 1 < value)) {
					return Blocks.GRASS_BLOCK.defaultBlockState();
				}
				return Blocks.DIRT.defaultBlockState();
			}
			return Blocks.AIR.defaultBlockState();
		}

		if (y >= TWOTWO_START) {
			if (y == TWOTWO_START) {
				return Blocks.BEDROCK.defaultBlockState();
			}
			int treePosHeight = (int) Math
					.floor(TWOTWO_START
							+ (1.01 + getNoise(randomFactory).getValue(chunkX * 16 * 0.002, chunkZ * 16 * 0.002)) * 20);

			RandomSource chunkRandom = randomFactory.at(chunkX, 0, chunkZ);

			Vec2 treePos = new Vec2(chunkRandom.nextIntBetweenInclusive(4, 12),
					chunkRandom.nextIntBetweenInclusive(4, 12));

			int treeHeight = chunkRandom.nextIntBetweenInclusive(9, 17);

			if (localX == treePos.x && localZ == treePos.y) {
				if (y >= treePosHeight && y <= treePosHeight + treeHeight) {
					return Blocks.SPRUCE_LOG.defaultBlockState();
				}
			}

			Range<Integer> leavesXRange = Range.of((int) Math.floor(treePos.x) - 2, (int) Math.floor(treePos.x) + 2);
			Range<Integer> leavesYRange = Range.of(treePosHeight + treeHeight - 2, treePosHeight + treeHeight + 2);
			Range<Integer> leavesZRange = Range.of((int) Math.floor(treePos.y) - 2, (int) Math.floor(treePos.y) + 2);

			if (leavesXRange.contains(localX))
				if (leavesZRange.contains(localZ))
					if (leavesYRange.contains(y))
						return Blocks.SPRUCE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);

			double value = TWOTWO_START + (1.01 + getNoise(randomFactory).getValue(x * 0.002, z * 0.002)) * 20;
			RandomSource blockRandom = randomFactory.at(x, y, z);
			if (y < value) {
				if (!(y + 1 < value)) {
					return Blocks.GRASS_BLOCK.defaultBlockState().setValue(GrassBlock.SNOWY, true);
				}
				return Blocks.DIRT.defaultBlockState();
			} else {
				if (y - 1 < value) {
					if (!(blockRandom.nextInt(80) == 2))
						return Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS,
								blockRandom.nextIntBetweenInclusive(1, 3));
				}
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "shadygray";
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 512;
	}

	@Override
	public int getMinY() {
		return 0;
	}

}
