package com.mrbysco.justaraftmod.config;

import com.mrbysco.justaraftmod.JustARaftMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class RaftConfig {
    public static class Server {
        public final DoubleValue SpeedMultiplier;
        public final DoubleValue TurnMultiplier;
        public final BooleanValue SlipperyFast;
        public final BooleanValue SinkTheRaft;

        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Server settings")
                    .push("Server");

            SpeedMultiplier = builder
                    .comment("Adjusting this setting changes the speed of the raft. (Default: 1.0) [Lower than 1 = slower | higher than 1 = faster]")
                    .defineInRange("SpeedMultiplier", 1.0D, Double.MIN_VALUE, Double.MAX_VALUE);

            TurnMultiplier = builder
                    .comment("Adjusting this setting changes the speed of turning the raft. (Default: 1.0) [Lower than 1 = slower | higher than 1 = faster]")
                    .defineInRange("TurnMultiplier", 1.0D, Double.MIN_VALUE, Double.MAX_VALUE);

            SlipperyFast = builder
                    .comment("Changing this to false makes rafts the same speed as on land while on a slippery block. (Default: true)")
                    .define("SlipperyFast", true);

            SinkTheRaft = builder
                    .comment("Changing this to true makes the raft sink if there's 2 entities on it. (Default: false)")
                    .define("SinkTheRaft", false);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec serverSpec;
    public static final RaftConfig.Server SERVER;

    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(RaftConfig.Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        JustARaftMod.LOGGER.debug("Loaded JustARaftMod's config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading configEvent) {
        JustARaftMod.LOGGER.debug("JustARaftMod's config just got changed on the file system!");
    }
}
