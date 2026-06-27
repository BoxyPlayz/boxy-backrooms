package com.boxyplayz.backrooms.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

public class DataUtils {
	public static <T> Holder<T> getHolder(BootstrapContext<?> context, ResourceKey<Registry<T>> registry,
			ResourceKey<T> key) {
		return context.lookup(registry).getOrThrow(key);
	}
}
