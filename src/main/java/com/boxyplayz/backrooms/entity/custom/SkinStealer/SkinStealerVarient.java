package com.boxyplayz.backrooms.entity.custom.SkinStealer;

import java.util.Arrays;
import java.util.Comparator;

public enum SkinStealerVarient {
	PASSIVE(0),
	ANGERED(1);

	private static final SkinStealerVarient[] BY_ID = Arrays.stream(values())
			.sorted(Comparator.comparingInt(SkinStealerVarient::getId)).toArray(SkinStealerVarient[]::new);
	private final int id;

	SkinStealerVarient(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static SkinStealerVarient byId(int id) {
		return BY_ID[id % BY_ID.length];
	}
}
