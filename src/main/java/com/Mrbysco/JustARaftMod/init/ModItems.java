package com.Mrbysco.JustARaftMod.init;

import com.Mrbysco.JustARaftMod.Items.ItemRaft;
import com.Mrbysco.JustARaftMod.entity.EntityRaft;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems {

	public static Item raft, spruce_raft, birch_raft, jungle_raft, acacia_raft, dark_oak_raft;
	
	public static void init(){
		raft = new ItemRaft(EntityRaft.Type.OAK);
		spruce_raft = new ItemRaft(EntityRaft.Type.SPRUCE);
		birch_raft = new ItemRaft(EntityRaft.Type.BIRCH);
		jungle_raft = new ItemRaft(EntityRaft.Type.JUNGLE);
		acacia_raft = new ItemRaft(EntityRaft.Type.ACACIA);
		dark_oak_raft = new ItemRaft(EntityRaft.Type.DARK_OAK);
		}
	
	public static void register()
	{
		ForgeRegistries.ITEMS.register(raft);
		ForgeRegistries.ITEMS.register(spruce_raft);
		ForgeRegistries.ITEMS.register(birch_raft);
		ForgeRegistries.ITEMS.register(jungle_raft);
		ForgeRegistries.ITEMS.register(acacia_raft);
		ForgeRegistries.ITEMS.register(dark_oak_raft);
	}
	
	public static void registerRenders()
	{
		registerRender(raft);
		registerRender(spruce_raft);
		registerRender(birch_raft);
		registerRender(jungle_raft);
		registerRender(acacia_raft);
		registerRender(dark_oak_raft);
	}
	
	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}