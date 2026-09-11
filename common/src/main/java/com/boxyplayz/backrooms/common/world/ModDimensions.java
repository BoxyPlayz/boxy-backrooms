package com.boxyplayz.backrooms.common.world;

import java.util.HashSet;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class ModDimensions {

        protected static final HashSet<DimensionInstance> backroomsDimensions = new HashSet<DimensionInstance>();

        @Nullable
        public static DimensionInstance getDimensionFromLevel(Level level) {
                for (DimensionInstance dimension : backroomsDimensions) {
                        if (level.dimension().equals(dimension.level)) {
                                return dimension;
                        }
                }
                return null;
        }

        private ModDimensions() {
        }

        public static final DimensionInstance BLUE_CHANNEL = new DimensionInstance("blue_channel",
                        new DimensionProperties(false));

        public static final DimensionInstance LEVEL8 = new DimensionInstance("level8_cavesystem",
                        new DimensionProperties(true));

        public static final DimensionInstance LEVEL7 = new DimensionInstance("level7_ocean",
                        new DimensionProperties(true));

        public static final DimensionInstance LEVEL6 = new DimensionInstance("level6", new DimensionProperties(true));

        public static final DimensionInstance LEVEL0 = new DimensionInstance("level0_maze",
                        new DimensionProperties(false));

        public static final DimensionInstance LEVEL94 = new DimensionInstance("level94", new DimensionProperties(true));

        public static final DimensionInstance LEVEL1 = new DimensionInstance("level1", new DimensionProperties(true));

        public static final DimensionInstance PITFALLS = new DimensionInstance("pitfalls",
                        new DimensionProperties(true));

        public static final DimensionInstance LEVEL0_2 = new DimensionInstance("level0_2",
                        new DimensionProperties(true));

        public static final DimensionInstance LEVEL_NEGATIVE_0_2 = new DimensionInstance(
                        "level_negative_0_2", new DimensionProperties(true));

        public static final DimensionInstance LEVEL_FUN = new DimensionInstance("levelfun",
                        new DimensionProperties(true));

        public static final DimensionInstance PROMISED_LAND = new DimensionInstance("promised_land",
                        new DimensionProperties(false));

        public static final DimensionInstance LEVEL5 = new DimensionInstance("level5", new DimensionProperties(true));

        public static final DimensionInstance LEVEL3 = new DimensionInstance("level3", new DimensionProperties(true));

        public static final DimensionInstance LEVEL2 = new DimensionInstance("level2", new DimensionProperties(true));

        public static final DimensionInstance LEVEL4 = new DimensionInstance("level4", new DimensionProperties(true));

        public static final DimensionInstance THE_BROKEN = new DimensionInstance("the_broken",
                        new DimensionProperties(false));

        public static final DimensionInstance LEVEL11 = new DimensionInstance("level11",
                        new DimensionProperties(false));

        public static final DimensionInstance LEVEL9 = new DimensionInstance("level9", new DimensionProperties(true));

        public static final DimensionInstance LEVEL10 = new DimensionInstance("level10",
                        new DimensionProperties(false));

        public static final DimensionInstance THE_ABYSS = new DimensionInstance("the_abyss",
                        new DimensionProperties(false));

        public static class DimensionInstance {
                public final ResourceKey<Level> level;
                public final ResourceKey<DimensionType> type;
                public final ResourceKey<LevelStem> stem;
                public final DimensionProperties props;

                public DimensionInstance(String id, DimensionProperties props) {
                        this.level = ResourceKey.create(
                                        Registries.DIMENSION,
                                        Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, id));
                        this.type = ResourceKey.create(
                                        Registries.DIMENSION_TYPE,
                                        Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, id));
                        this.stem = ResourceKey.create(
                                        Registries.LEVEL_STEM,
                                        Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID, id));
                        this.props = props;

                        backroomsDimensions.add(this);
                }

                public boolean is(Level level) {
                        return level.dimension() == this.level;
                }
        }

        public static class DimensionProperties {
                public final boolean wretchedCycle;

                public DimensionProperties(boolean wretchedCycle) {
                        this.wretchedCycle = wretchedCycle;
                }

                public static class Builder {
                        protected boolean wretchedCycle = false;

                        public Builder() {

                        }

                        public Builder setWretchedCycle(boolean wretchedCycle) {
                                this.wretchedCycle = wretchedCycle;
                                return this;
                        }

                        public DimensionProperties build() {
                                return new DimensionProperties(this.wretchedCycle);
                        }
                }

        }

        public static void RegisterModDimensions() {
        }
}
