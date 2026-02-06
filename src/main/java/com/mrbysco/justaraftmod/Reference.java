package com.mrbysco.justaraftmod;

import net.minecraft.resources.Identifier;

public class Reference {
	public static final String MOD_ID = "justaraftmod";

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
