package com.boxyplayz.backrooms;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.creativetabs.ModCreativeTabs;
import com.boxyplayz.backrooms.item.ModItems;

public class BoxysBackrooms implements ModInitializer {
	public static final String MOD_ID = "boxys_backrooms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeTabs.RegisterModCreativeTabs();
	}
}