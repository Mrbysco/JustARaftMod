package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.entities.RaftType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class RaftTypeRegistry {
	public static final RaftType OAK = registerCustomRaftType(Blocks.OAK_PLANKS, "oak");
	public static final RaftType SPRUCE = registerCustomRaftType(Blocks.SPRUCE_PLANKS, "spruce");
	public static final RaftType BIRCH = registerCustomRaftType(Blocks.BIRCH_PLANKS, "birch");
	public static final RaftType JUNGLE = registerCustomRaftType(Blocks.JUNGLE_PLANKS, "jungle");
	public static final RaftType ACACIA = registerCustomRaftType(Blocks.ACACIA_PLANKS, "acacia");
	public static final RaftType DARK_OAK = registerCustomRaftType(Blocks.DARK_OAK_PLANKS, "dark_oak");
	public static final RaftType BAMBOO = registerCustomRaftType(Blocks.BAMBOO, "bamboo");
	public static final RaftType MANGROVE = registerCustomRaftType(Blocks.MANGROVE_PLANKS, "mangrove");
	public static final RaftType CHERRY = registerCustomRaftType(Blocks.CHERRY_PLANKS, "cherry");

	public static RaftType registerCustomRaftType(Block planks, String name) {
		return RaftType.registerRaftType(new RaftType(planks, name));
	}
}
