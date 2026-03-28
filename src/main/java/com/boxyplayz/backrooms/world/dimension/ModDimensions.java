package com.boxyplayz.backrooms.world.dimension;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class ModDimensions {

        public static final ResourceKey<Level> LEVEL8_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

        public static final ResourceKey<Level> LEVEL7_DIMENSION = ResourceKey.create(
                        Registries.DIMENSION,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

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

        public static void RegisterModDimensions() {

        }
}
