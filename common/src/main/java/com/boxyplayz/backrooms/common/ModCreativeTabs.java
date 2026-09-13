package com.boxyplayz.backrooms.common;

import com.boxyplayz.backrooms.common.item.ModItems;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
	public static final Identifier BACKROOMS_ITEM_GROUP_ID = Identifier
			.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, "item_group");
	public static final ResourceKey<CreativeModeTab> BACKROOMS_ITEM_GROUP_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			BACKROOMS_ITEM_GROUP_ID);
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BoxysBackroomsCommon.MOD_ID,
			Registries.CREATIVE_MODE_TAB);
	public static final RegistrySupplier<CreativeModeTab> BACKROOMS_ITEM_GROUP = TABS.register(
			BACKROOMS_ITEM_GROUP_ID,
			() -> CreativeTabRegistry.create(
					Component.translatable("itemGroup.boxys_backrooms"),
					() -> new ItemStack(ModItems.GRAY_ALMOND_WATER)));

	public static void RegisterModCreativeTabs() {
		TABS.register();
	}
}
