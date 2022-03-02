package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RaftTab {
	public static final CreativeModeTab RAFT = new CreativeModeTab(Reference.MOD_ID + ".raft") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(RaftRegistry.OAK_RAFT.get());
		}
	};
}
