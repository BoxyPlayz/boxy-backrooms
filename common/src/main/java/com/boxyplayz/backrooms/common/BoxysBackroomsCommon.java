package com.boxyplayz.backrooms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxyplayz.backrooms.common.effect.ModEffects;
import com.boxyplayz.backrooms.common.events.LevelChangeEvents;
import com.boxyplayz.backrooms.common.item.ModItems;
import com.boxyplayz.backrooms.common.networking.NetworkManagers;
import com.boxyplayz.backrooms.common.recipe.ModRecipes;
import com.boxyplayz.backrooms.common.world.ModBiomes;
import com.boxyplayz.backrooms.common.world.ModDimensions;
import com.boxyplayz.backrooms.common.world.ModStructures;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class BoxysBackroomsCommon {
    public static final String MOD_ID = "boxys_backrooms";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID,
            Registries.BLOCK_ENTITY_TYPE);

    public static void init() {
        ModBiomes.RegisterModBiomes();
        ModDamageTypes.RegisterDamageTypes();
        ModWorldClocks.RegisterModWorldClocks();
        ModWorldPresets.RegisterPresets();
        ModRecipes.RegisterModRecipes();
        ModTags.RegisterModTags();
        ModToolMaterials.RegisterToolMaterials();
        ModDimensions.RegisterModDimensions();
        ModEnchantments.RegisterModEnchantments();
        ModStructures.RegisterStructures();
        ModLootTables.RegisterLootTables();
        NetworkManagers.register();
        ModCreativeTabs.RegisterModCreativeTabs();
        ModEffects.RegisterModEffects();
        ModItems.registerModItems();
        LevelChangeEvents.init();
    }
}
