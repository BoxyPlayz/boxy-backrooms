package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.world.biome.ModBiomes;
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

public class Level11ChunkGen extends BaseChunkGen {

	public Level11ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	public static final MapCodec<Level11ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(RegistryOps.retrieveElement(ModBiomes.LEVEL11_BIOME)).apply(instance,
					instance.stable(Level11ChunkGen::new)));

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		if (y < 4) {
			return Blocks.SMOOTH_STONE.defaultBlockState();
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "citynoend";
	}

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
		return -16;
	}

}
