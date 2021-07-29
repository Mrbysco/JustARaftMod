package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RaftTab {
    public static final ItemGroup RAFT = new ItemGroup(Reference.MOD_ID + ".raft") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(RaftRegistry.OAK_RAFT.get());
        }
    };
}
