package com.boxyplayz.backrooms.client;

import com.boxyplayz.backrooms.common.fluid.ModFluids;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.neoforged.fml.config.ModConfig;

/**
 * Begin Client.
 */
public class BoxysBackroomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BoxysBackroomsCommonClient.init();

		ConfigRegistry.INSTANCE.register("boxys_backrooms", ModConfig.Type.CLIENT,
				BoxysBackroomsClientConfig.SPEC);

		FluidRenderingRegistry.register(ModFluids.LIQUID_REALITY_SOURCE.get(), ModFluids.LIQUID_REALITY_FLOWING.get(),
				new FluidModel.Unbaked(
						new Material(Identifier.withDefaultNamespace("fluid/reality_source")),
						new Material(Identifier.withDefaultNamespace("fluid/reality_flowing")),
						null,
						BlockTintSources.constant(ARGB.opaque(0X6B00FF))));
	}
}