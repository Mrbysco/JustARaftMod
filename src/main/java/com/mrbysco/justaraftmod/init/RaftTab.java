package com.mrbysco.justaraftmod.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class RaftTab {
	@SubscribeEvent
	public void addTabContents(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			List<ItemStack> stacks = RaftRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
			event.acceptAll(stacks);
		}
	}
}
