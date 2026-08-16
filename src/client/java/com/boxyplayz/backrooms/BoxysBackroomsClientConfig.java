package com.boxyplayz.backrooms;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BoxysBackroomsClientConfig {
	public static final ModConfigSpec SPEC;

	public static final ModConfigSpec.BooleanValue ADD_DASH_HINT;

	static {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
		builder.push("client");

		ADD_DASH_HINT = builder.comment(" Enable text hint that shows that you can dash")
				.define("dash_hint", true);

		builder.pop();

		SPEC = builder.build();
	}
}
