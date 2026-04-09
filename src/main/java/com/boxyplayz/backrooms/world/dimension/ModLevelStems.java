package com.boxyplayz.backrooms.world.dimension;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public class ModLevelStems {

        public static final ResourceKey<LevelStem> BLUE_CHANNEL_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "blue_channel"));

        public static final ResourceKey<LevelStem> LEVEL8_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level8_cavesystem"));

        public static final ResourceKey<LevelStem> LEVEL7_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level7_ocean"));

        public static final ResourceKey<LevelStem> LEVEL0_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_maze"));

        public static final ResourceKey<LevelStem> LEVEL94_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level94"));

        public static final ResourceKey<LevelStem> LEVEL1_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level1"));

        public static final ResourceKey<LevelStem> PITFALLS_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "pitfalls"));

        public static final ResourceKey<LevelStem> LEVEL0_2_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level0_2"));

        public static final ResourceKey<LevelStem> LEVEL_NEGATIVE_0_2_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "level_negative_0_2"));

        public static final ResourceKey<LevelStem> BROKEN_LEVEL_STEM = ResourceKey.create(
                        Registries.LEVEL_STEM,
                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "the_broken"));

        public static void RegisterModStems() {

        }
}
