package com.boxyplayz.backrooms.common.fluid;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.item.ModItems;

import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModFluids {

	public static final RegistrySupplier<ArchitecturyFlowingFluid.Source> LIQUID_REALITY_SOURCE = BoxysBackroomsCommon.FLUIDS
			.register("liquid_reality", () -> new ArchitecturyFlowingFluid.Source(Holder.LIQUID_REALITY_ATTRIBUTES));

	public static final RegistrySupplier<ArchitecturyFlowingFluid.Flowing> LIQUID_REALITY_FLOWING = BoxysBackroomsCommon.FLUIDS
			.register("flowing_reality", () -> new ArchitecturyFlowingFluid.Flowing(Holder.LIQUID_REALITY_ATTRIBUTES));

	public static final RegistrySupplier<LiquidBlock> LIQUID_REALITY_BLOCK = BoxysBackroomsCommon.BLOCKS
			.register("liquid_reality", () -> {
				ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK,
						Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "liquid_reality"));
				return new ArchitecturyLiquidBlock(LIQUID_REALITY_SOURCE,
						BlockBehaviour.Properties.of().liquid().setId(key));
			});

	public static class Holder {

		public static final ArchitecturyFluidAttributes LIQUID_REALITY_ATTRIBUTES = SimpleArchitecturyFluidAttributes
				.of(() -> ModFluids.LIQUID_REALITY_FLOWING.get(), () -> ModFluids.LIQUID_REALITY_SOURCE.get())
				.block(ModFluids.LIQUID_REALITY_BLOCK)
				.bucketItem(ModItems.REALITY_BUCKET)
				.sourceTexture(Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "fluid/reality_source"))
				.flowingTexture(Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "fluid/reality_flowing"))
				.convertToSource(true)
				.rarity(Rarity.EPIC)
				.density(0)
				.color(0X6B00FF)
				.luminosity(4);

	}

	public static void Register() {
		BoxysBackroomsCommon.FLUIDS.register();

	}
}
