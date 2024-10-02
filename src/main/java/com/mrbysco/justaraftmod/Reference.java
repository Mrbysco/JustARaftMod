package com.mrbysco.justaraftmod;

import net.minecraft.resources.ResourceLocation;

public class Reference {
	public static final String MOD_ID = "justaraftmod";

	public static ResourceLocation modLoc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
