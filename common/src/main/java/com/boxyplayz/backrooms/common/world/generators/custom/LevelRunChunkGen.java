package com.boxyplayz.backrooms.common.world.generators.custom;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.common.world.generators.BaseChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class LevelRunChunkGen extends BaseChunkGen {

	public static final MapCodec<LevelRunChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(Biomes.THE_VOID))
					.apply(instance,
							instance.stable(LevelRunChunkGen::new)));

	public LevelRunChunkGen(Holder<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		Range<Integer> width = Range.of(-3, 3);
		Range<Integer> height = Range.of(2, 5);
		if (x == 0 && y == height.getMaximum() + 1 && Math.floorMod(z, 4) == 2) {
			return Blocks.REDSTONE_LAMP.defaultBlockState().setValue(RedstoneLampBlock.LIT, true);
		}
		if (!(width.contains(x) && height.contains(y)) || z <= -100) {
			return Blocks.RED_CONCRETE.defaultBlockState();
		}

		RandomSource rowRandom = randomFactory.at(0, y, z);

		if (y == height.getMinimum()) {
			if (rowRandom.nextInt(20) == 3) {
				return Blocks.OAK_PLANKS.defaultBlockState();
			}
		}

		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "runforyourlife";
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 48;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
