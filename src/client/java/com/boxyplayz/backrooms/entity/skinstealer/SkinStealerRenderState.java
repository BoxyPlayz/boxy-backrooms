package com.boxyplayz.backrooms.entity.skinstealer;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.boxyplayz.backrooms.entity.custom.SkinStealer.SkinStealerVarient;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class SkinStealerRenderState extends HumanoidRenderState {
	@Nullable
	public String maskedPlayerUsername;
	@Nullable
	public UUID maskedPlayerUUID;
	@Nullable
	public SkinStealerVarient varient;
	@Nullable
	public Identifier cachedSkin;
}
