package com.boxyplayz.backrooms.creativetabs;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.item.ModItems;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
	public static final ResourceKey<CreativeModeTab> BACKROOMS_ITEM_GROUP_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "item_group"));
	public static final CreativeModeTab BACKROOMS_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.GRAY_ALMOND_WATER))
			.title(Component.translatable("itemGroup.boxys_backrooms"))
			.build();

	public static void RegisterModCreativeTabs() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BACKROOMS_ITEM_GROUP_KEY, BACKROOMS_ITEM_GROUP);
	}

}
