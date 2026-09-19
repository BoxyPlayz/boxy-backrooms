package com.boxyplayz.backrooms.common.events;

import com.boxyplayz.backrooms.common.BoxysBackroomsCommon;
import com.boxyplayz.backrooms.common.ModTags;
import com.boxyplayz.backrooms.common.world.ModDimensions;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public class LevelChangeEvents {
	public static Identifier level8BoostId = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID,
			"level8_jump_boost");
	public static Identifier levelRunBoostId = Identifier.fromNamespaceAndPath(BoxysBackroomsCommon.MOD_ID,
			"levelrun_jump_boost");

	public static void init() {
		PlayerEvent.CHANGE_DIMENSION.register((player, origin, destination) -> {
			ServerLevel target = player.level().getServer().getLevel(destination);
			if (target.getBiome(player.blockPosition()).is(ModTags.LARGE_JUMP)) {
				player.getAttribute(Attributes.JUMP_STRENGTH)
						.addOrReplacePermanentModifier(new AttributeModifier(level8BoostId, 0.8, Operation.ADD_VALUE));
				player.getAttribute(Attributes.MOVEMENT_SPEED)
						.addOrReplacePermanentModifier(new AttributeModifier(level8BoostId, 0.1, Operation.ADD_VALUE));
			} else {
				player.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(level8BoostId);
				player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(level8BoostId);
			}
			if (destination.equals(ModDimensions.RUN_FOR_LIFE.level)) {
				player.getAttribute(Attributes.MOVEMENT_SPEED).addOrReplacePermanentModifier(
						new AttributeModifier(levelRunBoostId, 0.2, Operation.ADD_VALUE));
			} else {
				player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(levelRunBoostId);
			}
		});
	}
}
