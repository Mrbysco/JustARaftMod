package com.Mrbysco.JustARaftMod.proxy;

import com.Mrbysco.JustARaftMod.entity.EntityRaft;
import com.Mrbysco.JustARaftMod.render.RenderRaft;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy{

	public void Preinit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRaft.class, RenderRaft.FACTORY);
	}

	public void Init() {
		
	}
}
