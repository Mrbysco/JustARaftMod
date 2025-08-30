package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.entities.Raft;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class RaftRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);
	public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(Reference.MOD_ID);

	public static final DeferredItem<RaftItem> OAK_RAFT = ITEMS.registerItem("oak_raft", (properties) -> new RaftItem(RaftTypeRegistry.OAK, properties));
	public static final DeferredItem<RaftItem> SPRUCE_RAFT = ITEMS.registerItem("spruce_raft", (properties) -> new RaftItem(RaftTypeRegistry.SPRUCE, properties));
	public static final DeferredItem<RaftItem> BIRCH_RAFT = ITEMS.registerItem("birch_raft", (properties) -> new RaftItem(RaftTypeRegistry.BIRCH, properties));
	public static final DeferredItem<RaftItem> JUNGLE_RAFT = ITEMS.registerItem("jungle_raft", (properties) -> new RaftItem(RaftTypeRegistry.JUNGLE, properties));
	public static final DeferredItem<RaftItem> ACACIA_RAFT = ITEMS.registerItem("acacia_raft", (properties) -> new RaftItem(RaftTypeRegistry.ACACIA, properties));
	public static final DeferredItem<RaftItem> DARK_OAK_RAFT = ITEMS.registerItem("dark_oak_raft", (properties) -> new RaftItem(RaftTypeRegistry.DARK_OAK, properties));

	public static final DeferredItem<RaftItem> BAMBOO_RAFT = ITEMS.registerItem("bamboo_raft", (properties) -> new RaftItem(RaftTypeRegistry.BAMBOO, properties));
	public static final DeferredItem<RaftItem> MANGROVE_RAFT = ITEMS.registerItem("mangrove_raft", (properties) -> new RaftItem(RaftTypeRegistry.MANGROVE, properties));
	public static final DeferredItem<RaftItem> CHERRY_RAFT = ITEMS.registerItem("cherry_raft", (properties) -> new RaftItem(RaftTypeRegistry.CHERRY, properties));

	public static final Supplier<CreativeModeTab> RAFT_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(RaftRegistry.OAK_RAFT.get()))
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.justaraftmod.raft"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = RaftRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());

	public static final Supplier<EntityType<Raft>> RAFT = ENTITIES.registerEntityType("raft",
			Raft::new,
			MobCategory.MISC,
			builder -> builder
					.sized(1.375F, 0.3F)
					.eyeHeight(0.5625F)
					.clientTrackingRange(10)
	);
}
