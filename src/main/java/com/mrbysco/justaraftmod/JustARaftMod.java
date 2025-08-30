package com.mrbysco.justaraftmod;

import com.mojang.logging.LogUtils;
import com.mrbysco.justaraftmod.client.ClientHandler;
import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import com.mrbysco.justaraftmod.init.RaftTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID)
public class JustARaftMod {
	public static final Logger LOGGER = LogUtils.getLogger();

	public JustARaftMod(IEventBus eventBus, Dist dist, ModContainer container) {
		container.registerConfig(ModConfig.Type.SERVER, RaftConfig.serverSpec);
		eventBus.register(RaftConfig.class);

		eventBus.register(new RaftTab());

		RaftRegistry.ITEMS.register(eventBus);
		RaftRegistry.CREATIVE_MODE_TABS.register(eventBus);
		RaftRegistry.ENTITIES.register(eventBus);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}
	}
}
