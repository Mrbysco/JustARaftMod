package com.mrbysco.justaraftmod;

import com.mrbysco.justaraftmod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RaftTab extends CreativeTabs{

	public RaftTab() {
		super(Reference.MOD_ID);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.raft);
	}
}
