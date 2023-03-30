package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class RaftTab {
	private static CreativeModeTab RAFT;

	@SubscribeEvent
	public void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		RAFT = event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "raft"), builder ->
				builder.icon(() -> new ItemStack(RaftRegistry.OAK_RAFT.get()))
						.title(Component.translatable("itemGroup.justaraftmod.raft"))
						.displayItems((displayParameters, output) -> {
							List<ItemStack> stacks = RaftRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
	}

	@SubscribeEvent
	public void addTabContents(final CreativeModeTabEvent.BuildContents event) {
		if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			List<ItemStack> stacks = RaftRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
			event.acceptAll(stacks);
		}
	}
}
