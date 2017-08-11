package com.Mrbysco.JustARaftMod;

import com.Mrbysco.JustARaftMod.init.ModItems;

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
