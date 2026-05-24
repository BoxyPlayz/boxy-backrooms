package com.boxyplayz.backrooms.world.generators;

import com.boxyplayz.backrooms.block.ModBlocks;
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

public class Level4ChunkGen extends BaseChunkGen {

	public Level4ChunkGen(Holder.Reference<Biome> biome) {
		super(new FixedBiomeSource(biome));
	}

	@Override
	public BlockState getBlockAt(PositionalRandomFactory randomFactory, int x, int y, int z) {
		if (y <= 0) {
			return ModBlocks.LEVEL4_CARPET.defaultBlockState();
		}
		if (y >= 4) {
			if (Math.floorMod(x, 4) == 0 && Math.floorMod(z, 4) == 0) {
				return ModBlocks.PURE_WHITE_GLOW.defaultBlockState();
			}
			return ModBlocks.LEVEL1_CEILING_AQUILA.defaultBlockState();
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public String getSeed() {
		return "abandonment";
	}

	public static final MapCodec<Level4ChunkGen> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					RegistryOps.retrieveElement(ModBiomes.LEVEL4_BIOME))
					.apply(instance,
							instance.stable(Level4ChunkGen::new)));

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public int getGenDepth() {
		return 32;
	}

	@Override
	public int getMinY() {
		return -16;
	}

}
