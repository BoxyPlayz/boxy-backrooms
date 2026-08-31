package com.boxyplayz.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.boxyplayz.backrooms.world.dimension.ModDimensions;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.world.level.MoonPhase;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
	@Inject(method = "renderSun", at = @At("HEAD"), cancellable = true)
	private void backrooms$renderSun(float rainBrightness,
			PoseStack poseStack,
			CallbackInfo ci) {
		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.level != null && minecraft.level.dimension().equals(ModDimensions.ABYSS_DIMENSION)) {
			ci.cancel();
		}
	}

	@Inject(method = "renderMoon", at = @At("HEAD"), cancellable = true)
	private void backrooms$renderMoon(final MoonPhase moonPhase, final float rainBrightness, final PoseStack poseStack,
			CallbackInfo ci) {
		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.level != null && minecraft.level.dimension().equals(ModDimensions.ABYSS_DIMENSION)) {
			ci.cancel();
		}
	}
}
