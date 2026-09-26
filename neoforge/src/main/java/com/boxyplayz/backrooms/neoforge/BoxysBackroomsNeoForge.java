package com.boxyplayz.backrooms.neoforge;

import java.util.function.Supplier;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.neoforge.blocks.PowerOutletBlock;
import com.boxyplayz.backrooms.neoforge.blocks.PowerOutletBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(BoxysBackroomsCommon.MOD_ID)
public final class BoxysBackroomsNeoForge {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BoxysBackroomsCommon.MOD_ID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BoxysBackroomsCommon.MOD_ID);

        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister
                        .create(Registries.BLOCK_ENTITY_TYPE, BoxysBackroomsCommon.MOD_ID);

        public static final DeferredBlock<PowerOutletBlock> POWER_OUTLET_BLOCK = BLOCKS.register("power_outlet",
                        registryName -> new PowerOutletBlock(
                                        BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(2.4F)
                                                        .sound(SoundType.IRON)
                                                        .lightLevel((state) -> 1)
                                                        .setId(ResourceKey.create(Registries.BLOCK, registryName))
                                                        .isRedstoneConductor((state, getter, pos) -> false)));

        public static final DeferredItem<BlockItem> POWER_OUTLET_BLOCK_ITEM = ITEMS.register("power_outlet",
                        registryName -> new BlockItem(POWER_OUTLET_BLOCK.get(), new Item.Properties()
                                        .setId(ResourceKey.create(Registries.ITEM, registryName))));

        public static final Supplier<BlockEntityType<PowerOutletBlockEntity>> POWER_OUTLET_BLOCK_ENTITY = BLOCK_ENTITY_TYPES
                        .register(
                                        "power_outlet",
                                        () -> new BlockEntityType<>(
                                                        PowerOutletBlockEntity::new,
                                                        false,
                                                        BoxysBackroomsNeoForge.POWER_OUTLET_BLOCK.get()));

        public BoxysBackroomsNeoForge(IEventBus modBus) {
                BoxysBackroomsCommon.init();
                modBus.addListener(this::registerCapabilities);
                BLOCKS.register(modBus);
                ITEMS.register(modBus);
                BLOCK_ENTITY_TYPES.register(modBus);
        }

        protected void registerCapabilities(RegisterCapabilitiesEvent event) {
                event.registerBlockEntity(
                                Capabilities.Energy.BLOCK,
                                POWER_OUTLET_BLOCK_ENTITY.get(),
                                (entity, side) -> entity.getEnergy());
        }
}
