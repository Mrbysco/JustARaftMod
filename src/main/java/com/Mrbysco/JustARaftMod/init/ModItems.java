package com.mrbysco.justaraftmod.init;

import java.util.ArrayList;

import com.mrbysco.justaraftmod.Items.ItemRaft;
import com.mrbysco.justaraftmod.entity.EntityRaft;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class ModItems {

	public static Item raft, spruce_raft, birch_raft, jungle_raft, acacia_raft, dark_oak_raft;
	
	public static ArrayList<Item> ITEMS = new ArrayList<>();
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        raft = registerItem(new ItemRaft(EntityRaft.Type.OAK));
		spruce_raft = registerItem(new ItemRaft(EntityRaft.Type.SPRUCE));
		birch_raft = registerItem(new ItemRaft(EntityRaft.Type.BIRCH));
		jungle_raft = registerItem(new ItemRaft(EntityRaft.Type.JUNGLE));
		acacia_raft = registerItem(new ItemRaft(EntityRaft.Type.ACACIA));
		dark_oak_raft = registerItem(new ItemRaft(EntityRaft.Type.DARK_OAK));

        registry.registerAll(ITEMS.toArray(new Item[0]));
    }
	
	public static <T extends Item> T registerItem(T item)
    {
        ITEMS.add(item);
        return item;
    }
}