package com.boxyplayz.backrooms.events;

import com.boxyplayz.backrooms.BoxysBackrooms;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public class LevelChangeEvents {
	public static Identifier level8BoostId = Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID,
			"level8_jump_boost");

	public static void init() {
		ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL.register((player, origin, destination) -> {
			if (destination.getBiome(player.blockPosition()).is(ModTags.LARGE_JUMP)) {
				player.getAttribute(Attributes.JUMP_STRENGTH)
						.addOrUpdateTransientModifier(new AttributeModifier(level8BoostId, 0.8, Operation.ADD_VALUE));
				player.getAttribute(Attributes.MOVEMENT_SPEED)
						.addOrUpdateTransientModifier(new AttributeModifier(level8BoostId, 0.1, Operation.ADD_VALUE));
			} else {
				player.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(level8BoostId);
				player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(level8BoostId);
			}
		});
	}
}
