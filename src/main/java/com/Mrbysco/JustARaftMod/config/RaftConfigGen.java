package com.Mrbysco.JustARaftMod.config;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.Mrbysco.JustARaftMod.JustARaftMod;
import com.Mrbysco.JustARaftMod.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Config(modid = Reference.MOD_ID)
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
	
	@Mod.EventBusSubscriber
	static class ConfigurationHolder {

		public static MethodHandle findFieldGetter(Class<?> clazz, String... fieldNames) {
			final Field field = ReflectionHelper.findField(clazz, fieldNames);

			try {
				return MethodHandles.lookup().unreflectGetter(field);
			} catch (IllegalAccessException e) {
				throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
			}
		}
		
		private static final MethodHandle CONFIGS_GETTER = findFieldGetter(ConfigManager.class, "CONFIGS");
		
		private static Configuration configuration;
		
		static Configuration getConfiguration() {
			if (configuration == null) {
				try {
					final String fileName = Reference.MOD_ID + ".cfg";
					
					@SuppressWarnings("unchecked")
					final Map<String, Configuration> configsMap = (Map<String, Configuration>) CONFIGS_GETTER.invokeExact();

					final Optional<Map.Entry<String, Configuration>> entryOptional = configsMap.entrySet().stream()
							.filter(entry -> fileName.equals(new File(entry.getKey()).getName()))
							.findFirst();

					entryOptional.ifPresent(stringConfigurationEntry -> configuration = stringConfigurationEntry.getValue());
				} catch (Throwable throwable) {
					JustARaftMod.logger.debug("Failed to get Configuration instance", throwable);
				}
			}

			return configuration;
		}
		
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.load(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}