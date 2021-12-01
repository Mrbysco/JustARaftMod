package com.mrbysco.justaraftmod.client;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
    public static final ModelLayerLocation RAFT = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "raft"), "raft");

    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(RaftRegistry.RAFT.get(), RaftRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RAFT, RaftModel::createRaftDefinition);
    }
}
