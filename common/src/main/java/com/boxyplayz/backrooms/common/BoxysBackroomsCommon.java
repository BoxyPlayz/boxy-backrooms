package com.boxyplayz.backrooms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.common.recipe.ModRecipes;
import com.boxyplayz.backrooms.common.world.ModBiomes;

public final class BoxysBackroomsCommon {
    public static final String MOD_ID = "boxys_backrooms";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModBiomes.RegisterModBiomes();
        ModDamageTypes.RegisterDamageTypes();
        ModWorldClocks.RegisterModWorldClocks();
        ModWorldPresets.RegisterPresets();
        ModRecipes.RegisterModRecipes();
    }
}
