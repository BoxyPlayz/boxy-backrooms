package com.boxyplayz.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.boxyplayz.backrooms.common.world.ModBiomes;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Swim;

@Mixin(Swim.class)
public class SwimmingMixin {
	@Inject(method = "shouldSwim", at = @At("HEAD"), cancellable = true)
	public static <T extends Mob> void backrooms$shouldSwim(final T mob, CallbackInfoReturnable<Boolean> cir) {
		if (mob.level().getBiome(mob.blockPosition()).is(ModBiomes.ShadyGrayBiomes.THREETHREE)) {
			cir.setReturnValue(true);
			return;
		}
		return;
	}
}
