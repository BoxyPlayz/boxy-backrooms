package com.boxyplayz.backrooms.entity.skinstealer;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

@Environment(EnvType.CLIENT)
public class SkinStealerRenderState extends HumanoidRenderState {
	@Nullable
	public boolean isPassive;
}
