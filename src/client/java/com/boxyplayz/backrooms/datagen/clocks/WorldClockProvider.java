package com.boxyplayz.backrooms.datagen.clocks;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.clock.ModWorldClocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.clock.WorldClock;

public class WorldClockProvider extends FabricDynamicRegistryProvider {

	private static void register(BootstrapContext<WorldClock> context, ResourceKey<WorldClock> key,
			WorldClock clock) {
		context.register(key, clock);
	}

	public WorldClockProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "WorldClocksBackrooms";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.WORLD_CLOCK));
	}

	public static void bootstrap(BootstrapContext<WorldClock> context) {
		register(context, ModWorldClocks.LEVEL_94_CLOCK, new WorldClock());
	}

}
