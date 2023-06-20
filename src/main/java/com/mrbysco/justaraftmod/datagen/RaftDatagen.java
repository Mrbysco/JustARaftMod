package com.mrbysco.justaraftmod.datagen;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RaftDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(true, new Recipes(packOutput));
		}
		if (event.includeClient()) {
			generator.addProvider(true, new Language(packOutput));
			generator.addProvider(true, new ItemModels(packOutput, helper));
		}
	}

	private static class Recipes extends RecipeProvider {
		public Recipes(PackOutput packOutput) {
			super(packOutput);
		}

		@Override
		protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
			generateRaftRecipe(RaftRegistry.ACACIA_RAFT, ItemTags.ACACIA_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.BAMBOO_RAFT, Items.BAMBOO).save(consumer);
			generateRaftRecipe(RaftRegistry.BIRCH_RAFT, ItemTags.BIRCH_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.DARK_OAK_RAFT, ItemTags.DARK_OAK_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.JUNGLE_RAFT, ItemTags.JUNGLE_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.MANGROVE_RAFT, ItemTags.MANGROVE_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.OAK_RAFT, ItemTags.OAK_LOGS).save(consumer);
			generateRaftRecipe(RaftRegistry.SPRUCE_RAFT, ItemTags.SPRUCE_LOGS).save(consumer);
		}

		private RecipeBuilder generateRaftRecipe(RegistryObject<Item> raft, TagKey<Item> logTag) {
			return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, raft.get())
					.pattern("S S")
					.pattern("LLL")
					.pattern("S S")
					.define('L', logTag)
					.define('S', Tags.Items.STRING)
					.unlockedBy("has_log", has(logTag));
		}

		private RecipeBuilder generateRaftRecipe(RegistryObject<Item> raft, ItemLike log) {
			return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, raft.get())
					.pattern("S S")
					.pattern("LLL")
					.pattern("S S")
					.define('L', log)
					.define('S', Tags.Items.STRING)
					.unlockedBy("has_log", has(log));
		}
	}

	private static class Language extends LanguageProvider {
		public Language(PackOutput packOutput) {
			super(packOutput, Reference.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			this.add("itemGroup.justaraftmod.raft", "Just A Raft Mod");
			this.add("justaraftmod.config.title", "Just Another Raft Config");

			addItem(RaftRegistry.OAK_RAFT, "Oak Log Raft");
			addItem(RaftRegistry.SPRUCE_RAFT, "Spruce Log Raft");
			addItem(RaftRegistry.BIRCH_RAFT, "Birch Log Raft");
			addItem(RaftRegistry.JUNGLE_RAFT, "Jungle Log Raft");
			addItem(RaftRegistry.ACACIA_RAFT, "Acacia Log Raft");
			addItem(RaftRegistry.DARK_OAK_RAFT, "Dark Oak Log Raft");
			addItem(RaftRegistry.BAMBOO_RAFT, "Bamboo Raft");
			addItem(RaftRegistry.MANGROVE_RAFT, "Mangrove Raft");

			this.addEntityType(RaftRegistry.RAFT, "Raft");
		}
	}

	private static class ItemModels extends ItemModelProvider {
		public ItemModels(PackOutput packOutput, ExistingFileHelper helper) {
			super(packOutput, Reference.MOD_ID, helper);
		}

		@Override
		protected void registerModels() {
			withExistingParent(RaftRegistry.ACACIA_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "acacia_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "acacia_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "acacia_log_top"));
			withExistingParent(RaftRegistry.BAMBOO_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk"));
			withExistingParent(RaftRegistry.BIRCH_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "birch_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "birch_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "birch_log_top"));
			withExistingParent(RaftRegistry.DARK_OAK_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log_top"));
			withExistingParent(RaftRegistry.JUNGLE_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "jungle_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "jungle_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "jungle_log_top"));
			withExistingParent(RaftRegistry.MANGROVE_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log_top"));
			withExistingParent(RaftRegistry.OAK_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "oak_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "oak_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "oak_log_top"));
			withExistingParent(RaftRegistry.SPRUCE_RAFT.getId().getPath(), modLoc("item/raft_base"))
					.texture("particle", mcLoc(BLOCK_FOLDER + "/" + "spruce_log"))
					.texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "spruce_log"))
					.texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "spruce_log_top"));
		}
	}
}
