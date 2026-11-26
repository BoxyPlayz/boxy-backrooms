package com.boxyplayz.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.boxyplayz.backrooms.common.world.ModBiomes;

import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
	public void backrooms$isSwimming(CallbackInfoReturnable<Boolean> cir) {
		Entity entity = (Entity) (Object) this;
		if (entity.level().getBiome(entity.blockPosition()).is(ModBiomes.ShadyGrayBiomes.THREETHREE)) {
			cir.setReturnValue(true);
			return;
		}
		return;
	}
}
