package com.mrbysco.justaraftmod.client;

import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RaftRegistry.RAFT.get(), RaftRenderer::new);
    }
}
