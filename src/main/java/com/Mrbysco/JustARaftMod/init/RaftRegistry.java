package com.mrbysco.justaraftmod.init;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.entities.RaftEntity;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RaftRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<Item> OAK_RAFT = ITEMS.register("oak_raft", () -> new RaftItem(RaftEntity.Type.OAK, itemBuilder()));
    public static final RegistryObject<Item> SPRUCE_RAFT = ITEMS.register("spruce_raft", () -> new RaftItem(RaftEntity.Type.SPRUCE, itemBuilder()));
    public static final RegistryObject<Item> BIRCH_RAFT = ITEMS.register("birch_raft", () -> new RaftItem(RaftEntity.Type.BIRCH, itemBuilder()));
    public static final RegistryObject<Item> JUNGLE_RAFT = ITEMS.register("jungle_raft", () -> new RaftItem(RaftEntity.Type.JUNGLE, itemBuilder()));
    public static final RegistryObject<Item> ACACIA_RAFT = ITEMS.register("acacia_raft", () -> new RaftItem(RaftEntity.Type.ACACIA, itemBuilder()));
    public static final RegistryObject<Item> DARK_OAK_RAFT = ITEMS.register("dark_oak_raft", () -> new RaftItem(RaftEntity.Type.DARK_OAK, itemBuilder()));

    public static final RegistryObject<EntityType<RaftEntity>> RAFT = ENTITIES.register("raft", () -> register("raft", EntityType.Builder.<RaftEntity>of(RaftEntity::new, MobCategory.MISC)
            .sized(1.375F, 0.3F)
            .setCustomClientFactory(RaftEntity::new)));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build(id);
    }

    private static Item.Properties itemBuilder() {
        return new Item.Properties();
    }
}
