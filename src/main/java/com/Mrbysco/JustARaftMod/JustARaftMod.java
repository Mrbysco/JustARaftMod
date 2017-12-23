package com.Mrbysco.JustARaftMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Mrbysco.JustARaftMod.config.RaftConfigGen;
import com.Mrbysco.JustARaftMod.init.ModEntities;
import com.Mrbysco.JustARaftMod.init.ModItems;
import com.Mrbysco.JustARaftMod.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, 
	name = Reference.MOD_NAME, 
	version = Reference.VERSION, 
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
	guiFactory = "com.Mrbysco.JustARaftMod.config.GuiRaftConfigFactory")
	
public class JustARaftMod {
	
	@Instance(Reference.MOD_ID)
	public static JustARaftMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);
	
	public static RaftTab tabRaft = new RaftTab();
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{	
		logger.debug("Registering Raft Config");
		MinecraftForge.EVENT_BUS.register(new RaftConfigGen());
		
		logger.debug("Initializing Raft");
		ModItems.init();
		
		logger.debug("Registering Raft Item");
		ModItems.register();
		
		logger.debug("Registering Raft Entity");
		ModEntities.register();
		
		proxy.Preinit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.Init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}