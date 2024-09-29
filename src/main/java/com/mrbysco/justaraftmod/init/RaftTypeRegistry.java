package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.entities.RaftType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class RaftTypeRegistry {
	public static final RaftType OAK = registerCustomRaftType(Blocks.OAK_PLANKS, RaftRegistry.OAK_RAFT, "oak");
	public static final RaftType SPRUCE = registerCustomRaftType(Blocks.SPRUCE_PLANKS, RaftRegistry.SPRUCE_RAFT,  "spruce");
	public static final RaftType BIRCH = registerCustomRaftType(Blocks.BIRCH_PLANKS, RaftRegistry.BIRCH_RAFT,  "birch");
	public static final RaftType JUNGLE = registerCustomRaftType(Blocks.JUNGLE_PLANKS, RaftRegistry.JUNGLE_RAFT,  "jungle");
	public static final RaftType ACACIA = registerCustomRaftType(Blocks.ACACIA_PLANKS, RaftRegistry.ACACIA_RAFT,  "acacia");
	public static final RaftType DARK_OAK = registerCustomRaftType(Blocks.DARK_OAK_PLANKS, RaftRegistry.DARK_OAK_RAFT,  "dark_oak");
	public static final RaftType BAMBOO = registerCustomRaftType(Blocks.BAMBOO, RaftRegistry.BAMBOO_RAFT,  "bamboo");
	public static final RaftType MANGROVE = registerCustomRaftType(Blocks.MANGROVE_PLANKS, RaftRegistry.MANGROVE_RAFT,  "mangrove");
	public static final RaftType CHERRY = registerCustomRaftType(Blocks.CHERRY_PLANKS, RaftRegistry.CHERRY_RAFT,  "cherry");

	public static RaftType registerCustomRaftType(Block planks, Supplier<Item> raft, String name) {
		return RaftType.registerRaftType(new RaftType(planks, raft,name));
	}
}
