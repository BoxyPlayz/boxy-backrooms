package com.boxyplayz.backrooms.codecutils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PatchouliBook {
	private final String name;
	private final String landingText;
	private final int version;
	private final ResourceKey<CreativeModeTab> tab;
	private final boolean useResourcePack;

	public PatchouliBook(String name, String landingText, int version, ResourceKey<CreativeModeTab> creativeTab,
			boolean useResourcePack) {
		this.name = name;
		this.landingText = landingText;
		this.version = version;
		this.tab = creativeTab;
		this.useResourcePack = useResourcePack;
	}

	public PatchouliBook(String name, String landingText, ResourceKey<CreativeModeTab> creativeTab) {
		this.name = name;
		this.landingText = landingText;
		this.version = 1;
		this.tab = creativeTab;
		this.useResourcePack = true;
	}

	public String getName() {
		return this.name;
	}

	public String getLandText() {
		return this.landingText;
	}

	public int getVersion() {
		return this.version;
	}

	public ResourceKey<CreativeModeTab> getTab() {
		return this.tab;
	}

	public String getTabId() {
		return this.tab.identifier().toString();
	}

	public boolean getResourcePack() {
		return this.useResourcePack;
	}

	public static final Codec<PatchouliBook> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			// Up to 16 fields can be declared here
			Codec.STRING.fieldOf("name").forGetter(PatchouliBook::getName),
			Codec.STRING.fieldOf("landing_text").forGetter(PatchouliBook::getLandText),
			Codec.INT.fieldOf("version").forGetter(PatchouliBook::getVersion),
			ResourceKey.codec(Registries.CREATIVE_MODE_TAB).fieldOf("creative_tab")
					.forGetter(PatchouliBook::getTab),
			Codec.BOOL.fieldOf("use_resource_pack").forGetter(PatchouliBook::getResourcePack))
			.apply(instance, PatchouliBook::new));
}