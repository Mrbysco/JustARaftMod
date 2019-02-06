package com.mrbysco.justaraftmod.proxy;

import com.mrbysco.justaraftmod.entity.EntityRaft;
import com.mrbysco.justaraftmod.render.RenderRaft;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy{

	public void Preinit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRaft.class, RenderRaft.FACTORY);
	}

	public void Init() {
		
	}
}
