package com.mrbysco.justaraftmod.config;

import com.mrbysco.justaraftmod.Reference;

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
		@Config.Comment("Adjusting this setting changes the speed of the raft. (Default: 1.0) [Lower than 1 = slower | higher than 1 = faster]")
		public Double SpeedMultiplier = 1.0;
		
		@Config.Comment("Changing this to false makes rafts the same speed as on land while on a slippery block. (Default: true)")
		public Boolean SlipperyFast = true;
		
		//@Config.Comment("Changing this to false makes other mobs unable to get in the raft with you. (Default: true)")
		//public Boolean CatchingARide = true;
		
		@Config.Comment("Changing this to true makes the raft sink if theres 2 entities on it. (Default: false)")
		public Boolean SinkTheRaft = false;
		//Currently not implemented
		//@Config.Comment("Changing this brings back the old boat mechanic of it breaking if hitting a block hard. (Default: false)")
		//public Boolean DropIfCollided = false;
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