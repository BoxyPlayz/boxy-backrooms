package com.boxyplayz.backrooms.client;

public class ClientVariables {
	private static boolean hasShadyGray = false;

	public static void setShadyGray(boolean gray) {
		hasShadyGray = gray;
	}

	public static boolean getShadyGray() {
		return hasShadyGray;
	}
}
