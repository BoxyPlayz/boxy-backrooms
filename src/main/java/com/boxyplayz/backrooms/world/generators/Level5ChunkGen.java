package com.boxyplayz.backrooms.world.generators;

import org.apache.commons.lang3.Range;

import com.boxyplayz.backrooms.block.ModBlocks;
import com.boxyplayz.backrooms.block.custom.Level5CarpetBlock;
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

public class Level5ChunkGen extends BaseChunkGen {

	public static final MapCodec<Level5ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL5_BIOME))
					.apply(instance,
							instance.stable(Level5ChunkGen::new)));

	public Level5ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		int localX = Math.floorMod(x, 24);
		int localZ = Math.floorMod(z, 48);
		Range<Integer> horizHallRange = Range.of(0, 3);

		RandomSource blockRandom = randomFactory.at(x, y, z);
		if (y <= getMinY() + 3) {
			int carpetType = blockRandom.nextIntBetweenInclusive(0, 2);
			return ModBlocks.LEVEL5_CARPET.defaultBlockState().setValue(Level5CarpetBlock.TYPE, carpetType);
		}
		if (!(horizHallRange.contains(localX) || horizHallRange.contains(localZ))) {
			return Blocks.QUARTZ_BRICKS.defaultBlockState();
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "terrorhotel";
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 128;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
