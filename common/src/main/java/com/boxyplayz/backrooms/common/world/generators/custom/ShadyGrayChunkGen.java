package com.boxyplayz.backrooms.common.world.generators.custom;

import com.boxyplayz.backrooms.common.world.ModBiomes;
import com.boxyplayz.backrooms.common.world.generators.BaseChunkGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public class ShadyGrayChunkGen extends BaseChunkGen {

	public static final MapCodec<ShadyGrayChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.ShadyGrayBiomes.ZEROZERO))
					.apply(instance,
							instance.stable(ShadyGrayChunkGen::new)));

	public ShadyGrayChunkGen(Holder<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
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
