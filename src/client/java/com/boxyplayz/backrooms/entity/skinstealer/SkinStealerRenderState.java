package com.boxyplayz.backrooms.entity.skinstealer;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerVarient;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class SkinStealerRenderState extends LivingEntityRenderState {
	@Nullable
	public String maskedPlayerUsername;
	@Nullable
	public SkinStealerVarient varient;
}
