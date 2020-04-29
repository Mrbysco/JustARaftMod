package com.mrbysco.justaraftmod.client;

import com.mrbysco.justaraftmod.entities.RaftEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RaftEntity.class, RaftRenderer::new);
    }
}
