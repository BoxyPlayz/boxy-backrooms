package com.boxyplayz.backrooms.world.generators;

import java.util.HashSet;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.utils.Misc;
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
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class TheBrokenChunkGen extends BaseChunkGen {

	HashSet<Block> blocks = new HashSet<>();

	private SimplexNoise noise;

	private SimplexNoise getNoise(PositionalRandomFactory randomFactory) {
		if (this.noise == null) {
			RandomSource random = randomFactory.fromHashOf(this.getSeed());
			this.noise = new SimplexNoise(random);
		}

		return this.noise;

	}

	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int faraway = (int) Math.floor(Misc.normalizeValues(x, z) / 10);
		int maxDiff = 50 - faraway;
		if (maxDiff < 1) {
			maxDiff = 1;
		}
		int randomHeight = randomFactory.at(x, 3253, z).nextIntBetweenInclusive(1, maxDiff);
		int noiseHeight = (int) (getNoise(randomFactory).getValue(x * 0.01, z * 0.01) * 30);
		int height = Math.floorDiv(randomHeight + (noiseHeight * 2), 3);

		if (y <= height) {
			BlockState state = Blocks.BLACK_CONCRETE.defaultBlockState();
			if (faraway > 100) {
				state = ModBlocks.PURE_BLUE.defaultBlockState();
			} else {
				RandomSource blockRandom = randomFactory.at(x, y, z);
				for (Block b : blocks) {
					if (blockRandom.nextIntBetweenInclusive(0, 5) == 3) {
						state = b.defaultBlockState();
					}
				}
			}
			return state;
		}
		return Blocks.AIR.defaultBlockState();
	}

	public TheBrokenChunkGen(Holder.Reference<Biome> reference) {
		super(new FixedBiomeSource(reference));
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

	public static final MapCodec<TheBrokenChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.BROKEN_BIOME)).apply(instance,
					instance.stable(TheBrokenChunkGen::new)));

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
		return "fduhjsabh";
	}

}
