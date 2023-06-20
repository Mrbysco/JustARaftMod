package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.entities.Raft;
import com.mrbysco.justaraftmod.entities.Raft.Type;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class RaftRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);

	public static final RegistryObject<Item> OAK_RAFT = ITEMS.register("oak_raft", () -> new RaftItem(Raft.Type.OAK, itemBuilder()));
	public static final RegistryObject<Item> SPRUCE_RAFT = ITEMS.register("spruce_raft", () -> new RaftItem(Raft.Type.SPRUCE, itemBuilder()));
	public static final RegistryObject<Item> BIRCH_RAFT = ITEMS.register("birch_raft", () -> new RaftItem(Raft.Type.BIRCH, itemBuilder()));
	public static final RegistryObject<Item> JUNGLE_RAFT = ITEMS.register("jungle_raft", () -> new RaftItem(Raft.Type.JUNGLE, itemBuilder()));
	public static final RegistryObject<Item> ACACIA_RAFT = ITEMS.register("acacia_raft", () -> new RaftItem(Raft.Type.ACACIA, itemBuilder()));
	public static final RegistryObject<Item> DARK_OAK_RAFT = ITEMS.register("dark_oak_raft", () -> new RaftItem(Raft.Type.DARK_OAK, itemBuilder()));

	public static final RegistryObject<Item> BAMBOO_RAFT = ITEMS.register("bamboo_raft", () -> new RaftItem(Type.BAMBOO, itemBuilder()));
	public static final RegistryObject<Item> MANGROVE_RAFT = ITEMS.register("mangrove_raft", () -> new RaftItem(Type.MANGROVE, itemBuilder()));

	public static final RegistryObject<CreativeModeTab> RAFT_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(RaftRegistry.OAK_RAFT.get()))
			.title(Component.translatable("itemGroup.justaraftmod.raft"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = RaftRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
	public static final RegistryObject<EntityType<Raft>> RAFT = ENTITIES.register("raft", () -> register("raft", EntityType.Builder.<Raft>of(Raft::new, MobCategory.MISC)
			.sized(1.375F, 0.3F)
			.setCustomClientFactory(Raft::new)));

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build(id);
	}

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
