package com.mrbysco.justaraftmod;

import com.mrbysco.justaraftmod.client.ClientHandler;
import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class JustARaftMod {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public JustARaftMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, RaftConfig.serverSpec);
        eventBus.register(RaftConfig.class);

        RaftRegistry.ITEMS.register(eventBus);
        RaftRegistry.ENTITIES.register(eventBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(ClientHandler::registerEntityRenders);
            eventBus.addListener(ClientHandler::registerLayerDefinitions);
        });
    }
}
