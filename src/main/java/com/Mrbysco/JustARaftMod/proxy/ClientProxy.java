package com.Mrbysco.JustARaftMod.proxy;

import com.Mrbysco.JustARaftMod.entity.EntityRaft;
import com.Mrbysco.JustARaftMod.init.ModItems;
import com.Mrbysco.JustARaftMod.render.RenderRaft;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy{

	public void Preinit() {
		ModItems.registerRenders();
		RenderingRegistry.registerEntityRenderingHandler(EntityRaft.class, RenderRaft.FACTORY);
	}

	public void Init() {
		
	}
}
