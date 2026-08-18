package com.boxyplayz.backrooms;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BoxysBackroomsClientConfig {
	public static final ModConfigSpec SPEC;

	public static final ModConfigSpec.BooleanValue ADD_HINTS;

	static {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
		builder.push("client");

		ADD_HINTS = builder.comment(" Enable text hints")
				.define("hints", true);

		builder.pop();

		SPEC = builder.build();
	}
}
