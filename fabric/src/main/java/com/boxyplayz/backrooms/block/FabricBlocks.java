package com.boxyplayz.backrooms.block;

import com.boxyplayz.backrooms.block.custom.PowerOutletBlock;
import com.boxyplayz.backrooms.common.block.ModBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class FabricBlocks {

	public static final Block POWER_OUTLET_BLOCK = ModBlocks.register(
			"power_outlet",
			PowerOutletBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(2.4F).sound(SoundType.IRON)
					.lightLevel((state) -> 1)
					.isRedstoneConductor(Blocks::never),
			true);

	public static void RegisterFabricSidedBlocks() {

	}

}
