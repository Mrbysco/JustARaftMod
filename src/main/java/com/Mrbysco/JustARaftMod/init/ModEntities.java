package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.JustARaftMod;
import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.entity.EntityRaft;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void register() {
		EntityRegistry.registerModEntity(RAFT_REGISTRY, EntityRaft.class, RAFT, 0, JustARaftMod.instance, 80, 3, true);
	}

	public static final String RAFT = Reference.MOD_PREFIX + "Raft";
	public static final ResourceLocation RAFT_REGISTRY = Name("raft");
	
	private static ResourceLocation Name(String s) {
			return new ResourceLocation(Reference.MOD_ID, s);
	}
}
