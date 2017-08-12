package com.Mrbysco.JustARaftMod.init;

import com.Mrbysco.JustARaftMod.JustARaftMod;
import com.Mrbysco.JustARaftMod.Reference;
import com.Mrbysco.JustARaftMod.entity.EntityRaft;

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
