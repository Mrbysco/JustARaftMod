package com.mrbysco.justaraftmod.client;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.client.model.RaftModel;
import com.mrbysco.justaraftmod.client.renderer.RaftRenderer;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ClientHandler {
	public static final ModelLayerLocation RAFT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Reference.MOD_ID, "raft"), "main");

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RaftRegistry.RAFT.get(), RaftRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(RAFT, RaftModel::createRaftDefinition);
	}
}
