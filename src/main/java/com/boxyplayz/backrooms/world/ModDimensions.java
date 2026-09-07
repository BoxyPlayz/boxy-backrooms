package com.boxyplayz.backrooms.world;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class ModDimensions {

        private ModDimensions() {
        }

        public static final DimensionInstance BLUE_CHANNEL = new DimensionInstance("blue_channel");

        public static final DimensionInstance LEVEL8 = new DimensionInstance("level8_cavesystem");

        public static final DimensionInstance LEVEL7 = new DimensionInstance("level7_ocean");

        public static final DimensionInstance LEVEL6 = new DimensionInstance("level6");

        public static final DimensionInstance LEVEL0 = new DimensionInstance("level0_maze");

        public static final DimensionInstance LEVEL94 = new DimensionInstance("level94");

        public static final DimensionInstance LEVEL1 = new DimensionInstance("level1");

        public static final DimensionInstance PITFALLS = new DimensionInstance("pitfalls");

        public static final DimensionInstance LEVEL0_2 = new DimensionInstance("level0_2");

        public static final DimensionInstance LEVEL_NEGATIVE_0_2 = new DimensionInstance(
                        "level_negative_0_2");

        public static final DimensionInstance LEVEL_FUN = new DimensionInstance("levelfun");

        public static final DimensionInstance PROMISED_LAND = new DimensionInstance("promised_land");

        public static final DimensionInstance LEVEL5 = new DimensionInstance("level5");

        public static final DimensionInstance LEVEL3 = new DimensionInstance("level3");

        public static final DimensionInstance LEVEL2 = new DimensionInstance("level2");

        public static final DimensionInstance LEVEL4 = new DimensionInstance("level4");

        public static final DimensionInstance THE_BROKEN = new DimensionInstance("the_broken");

        public static final DimensionInstance LEVEL11 = new DimensionInstance("level11");

        public static final DimensionInstance LEVEL9 = new DimensionInstance("level9");

        public static final DimensionInstance LEVEL10 = new DimensionInstance("level10");

        public static final DimensionInstance THE_ABYSS = new DimensionInstance("the_abyss");

        public static class DimensionInstance {
                public final ResourceKey<Level> level;

                public DimensionInstance(String id) {
                        this.level = ResourceKey.create(
                                        Registries.DIMENSION,
                                        Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, id));
                }
        }

        public static void RegisterModDimensions() {

        }
}
