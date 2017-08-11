package com.Mrbysco.JustARaftMod.config;

import com.Mrbysco.JustARaftMod.Reference;

import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, category = "")
@Config.LangKey("jarm.config.title")
public class RaftConfigGen {
	
	@Config.Comment({"Raft Configuration"})
	public static RaftSettings raftconfig = new RaftSettings();
	
	public static class RaftSettings{
		@Config.Comment("If this value is true the Mundane Redstone Ore will drop a Vanilla Ore block when mined with silk touch (default: false)")
		public boolean SilkVanilla = false;
		
		@Config.Comment("If this value is true the Mundane Redstone Ore will drop a Vanilla Ore block when mined with silk touch (default: false)")
		public float OnWaterSpeed = 0.9F;
	}
	
}