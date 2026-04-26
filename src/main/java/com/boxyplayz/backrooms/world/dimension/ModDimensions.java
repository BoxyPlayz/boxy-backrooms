package com.boxyplayz.backrooms.world.dimension;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class ModDimensions {

        public static final ResourceKey<Level> BLUE_CHANNEL_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue_channel"));

        public static final ResourceKey<Level> LEVEL8_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

        public static final ResourceKey<Level> LEVEL7_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

        public static final ResourceKey<Level> LEVEL6_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level6"));

        public static final ResourceKey<Level> LEVEL0_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"));

        public static final ResourceKey<Level> LEVEL94_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));

        public static final ResourceKey<Level> LEVEL1_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1"));

        public static final ResourceKey<Level> PITFALLS_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"));

        public static final ResourceKey<Level> LEVEL0_2_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_2"));

        public static final ResourceKey<Level> LEVEL_NEGATIVE_0_2_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level_negative_0_2"));

        public static final ResourceKey<Level> LEVEL_FUN_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "levelfun"));

        public static final ResourceKey<Level> PROMISED_LAND_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "promised_land"));

        public static void RegisterModDimensions() {

        }
}
