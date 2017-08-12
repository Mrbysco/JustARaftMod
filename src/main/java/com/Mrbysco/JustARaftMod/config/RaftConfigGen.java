package com.Mrbysco.JustARaftMod.config;

import com.Mrbysco.JustARaftMod.Reference;

import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, category = "")
@Config.LangKey("jarm.config.title")
public class RaftConfigGen {
	
	@Config.Comment({"Raft Configuration"})
	public static RaftSettings raftconfig = new RaftSettings();
	
	public static class RaftSettings{
		//@Config.Comment("If this value is true collision with a wall will break the rafts (default: false)")
		//public boolean CollisionBreak = true;
	}
	
}