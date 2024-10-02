package com.mrbysco.justaraftmod.entities;

import com.mrbysco.justaraftmod.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RaftType {
	private static final Map<Integer, RaftType> ID_MAP = new HashMap<>();
	private static final Map<String, RaftType> NAME_MAP = new HashMap<>();
	private static int nextId = 0;

	private final int id;
	private final String name;
	private final Block planks;
	private final Supplier<Item> raft;
	private final ResourceLocation textureLocation;

	public RaftType(Block planks, Supplier<Item> raft, String name, ResourceLocation textureLocation) {
		this.id = nextId++;
		this.name = name;
		this.planks = planks;
		this.raft = raft;
		this.textureLocation = textureLocation;
	}

	public RaftType(Block planks, Supplier<Item> raft, String name) {
		this(planks, raft, name, Reference.modLoc("textures/entity/raft/" + name + "_raft.png"));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Block getPlanks() {
		return planks;
	}

	public Supplier<Item> getRaft() {
		return raft;
	}

	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	public String toString() {
		return this.getName();
	}

	public static RaftType byId(int id) {
		return ID_MAP.getOrDefault(id, ID_MAP.get(0));
	}

	public static RaftType byName(String name) {
		return NAME_MAP.getOrDefault(name, ID_MAP.get(0));
	}

	public static RaftType registerRaftType(RaftType raftType) {
		ID_MAP.put(raftType.id, raftType);
		NAME_MAP.put(raftType.name, raftType);
		return raftType;
	}
}
