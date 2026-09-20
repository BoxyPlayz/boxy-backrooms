package com.boxyplayz.backrooms.common.world.generators.custom;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.common.world.generators.BaseChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
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
			if (Math.floorMod(z, 3) == 1 && Math.abs(x) == width.getMaximum() + 1 && y <= height.getMinimum() + 1
					&& y >= height.getMinimum()) {
				return Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.HALF,
						((y == height.getMinimum()) ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER))
						.setValue(DoorBlock.OPEN, false)
						.setValue(DoorBlock.FACING, (x < 0) ? Direction.EAST : Direction.WEST);
			}
			return Blocks.RED_CONCRETE.defaultBlockState();
		}

		RandomSource sectionRandom = randomFactory.at(0, 0, Math.floorDiv(z, 4));
		RandomSource rowRandom = randomFactory.at(0, y, z);

		int type = sectionRandom.nextInt(2);
		if (type == 0 && y == height.getMinimum()) {
			if (rowRandom.nextInt(11) == 3) {
				return Blocks.OAK_PLANKS.defaultBlockState();
			}
		} else if (type == 1 && y == height.getMinimum() + 1) {
			if (rowRandom.nextInt(15) == 1) {
				return Blocks.OAK_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP);
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
