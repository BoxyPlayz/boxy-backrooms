package com.boxyplayz.backrooms.block;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.block.entity.PowerOutletBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
	private static <T extends BlockEntity> BlockEntityType<T> register(
			String name,
			FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
			Block... blocks) {
		Identifier id = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, name);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id,
				FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}

	public static final BlockEntityType<PowerOutletBlockEntity> POWER_OUTLET_BLOCK_ENTITY = register("power_outlet",
			PowerOutletBlockEntity::new, ModBlocks.POWER_OUTLET_BLOCK);

	public static void RegisterModBlockEntities() {
	}
}
