package com.Mrbysco.JustARaftMod.config;

import com.Mrbysco.JustARaftMod.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, category = "")
@Config.LangKey("jarm.config.title")
public class RaftConfigGen {
	
	@Config.Comment({"Raft Configuration"})
	public static RaftSettings raftconfig = new RaftSettings();
	
	public static class RaftSettings{
		//@Config.Comment("If this value is true collision with a wall will break the rafts (default: false)")
		//public boolean CollisionBreak = true;
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}