package com.Mrbysco.JustARaftMod.init;

import com.Mrbysco.JustARaftMod.JustARaftMod;
import com.Mrbysco.JustARaftMod.Reference;
import com.Mrbysco.JustARaftMod.entity.EntityRaft;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void register() {
		EntityRegistry.registerModEntity(EntityRaft.class, "Raft", 0, JustARaftMod.instance, 80, 3, true);
	}

}
