package com.mrbysco.justaraftmod.datagen;

import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RaftDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new Recipes.Runner(packOutput, lookupProvider));
		RaftBlockTags blockTags = new RaftBlockTags(packOutput, lookupProvider);
		generator.addProvider(true, blockTags);
		generator.addProvider(true, new RaftItemTags(packOutput, lookupProvider, blockTags));
		generator.addProvider(true, new RaftEntityTags(packOutput, lookupProvider));

		generator.addProvider(true, new Language(packOutput));
		generator.addProvider(true, new Models(packOutput));

	}

	private static class Recipes extends RecipeProvider {
		public Recipes(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
			super(provider, recipeOutput);
		}

		@Override
		protected void buildRecipes() {
			generateRaftRecipe(RaftRegistry.ACACIA_RAFT, ItemTags.ACACIA_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.BAMBOO_RAFT, Items.BAMBOO).save(output);
			generateRaftRecipe(RaftRegistry.BIRCH_RAFT, ItemTags.BIRCH_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.CHERRY_RAFT, ItemTags.CHERRY_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.DARK_OAK_RAFT, ItemTags.DARK_OAK_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.JUNGLE_RAFT, ItemTags.JUNGLE_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.MANGROVE_RAFT, ItemTags.MANGROVE_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.OAK_RAFT, ItemTags.OAK_LOGS).save(output);
			generateRaftRecipe(RaftRegistry.SPRUCE_RAFT, ItemTags.SPRUCE_LOGS).save(output);
		}

		private RecipeBuilder generateRaftRecipe(DeferredHolder<Item, RaftItem> raft, TagKey<Item> logTag) {
			return shaped(RecipeCategory.TRANSPORTATION, raft.get())
					.pattern("S S")
					.pattern("LLL")
					.pattern("S S")
					.define('L', logTag)
					.define('S', Tags.Items.STRINGS)
					.unlockedBy("has_log", has(logTag))
					.unlockedBy("has_string", has(Tags.Items.STRINGS));
		}

		private RecipeBuilder generateRaftRecipe(DeferredHolder<Item, RaftItem> raft, ItemLike log) {
			return shaped(RecipeCategory.TRANSPORTATION, raft.get())
					.pattern("S S")
					.pattern("LLL")
					.pattern("S S")
					.define('L', log)
					.define('S', Tags.Items.STRINGS)
					.unlockedBy("has_log", has(log))
					.unlockedBy("has_string", has(Tags.Items.STRINGS));
		}

		public static class Runner extends RecipeProvider.Runner {
			public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
				super(output, completableFuture);
			}

			@Override
			protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
				return new Recipes(provider, recipeOutput);
			}

			@Override
			public String getName() {
				return "Statues Recipes";
			}
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
			addItem(RaftRegistry.CHERRY_RAFT, "Cherry Log Raft");
			addItem(RaftRegistry.JUNGLE_RAFT, "Jungle Log Raft");
			addItem(RaftRegistry.ACACIA_RAFT, "Acacia Log Raft");
			addItem(RaftRegistry.DARK_OAK_RAFT, "Dark Oak Log Raft");
			addItem(RaftRegistry.BAMBOO_RAFT, "Bamboo Raft");
			addItem(RaftRegistry.MANGROVE_RAFT, "Mangrove Raft");

			this.addEntityType(RaftRegistry.RAFT, "Raft");

			this.addConfig("Server", "Server", "Server settings");
			this.addConfig("SpeedMultiplier", "Speed Multiplier", "Adjusting this setting changes the speed of the raft. (Default: 1.0) [Lower than 1 = slower | higher than 1 = faster]");
			this.addConfig("TurnMultiplier", "Turn Multiplier", "Adjusting this setting changes the speed of turning the raft. (Default: 1.0) [Lower than 1 = slower | higher than 1 = faster]");
			this.addConfig("SlipperyFast", "Slippery Fast", "Changing this to false makes rafts the same speed as on land while on a slippery block. (Default: true)");
			this.addConfig("SinkTheRaft", "Sink The Raft", "Changing this to true makes the raft sink if there's 2 entities on it. (Default: false)");
		}

		/**
		 * Add the translation for a config entry
		 *
		 * @param path        The path of the config entry
		 * @param name        The name of the config entry
		 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
		 */
		private void addConfig(String path, String name, @Nullable String description) {
			this.add(Reference.MOD_ID + ".configuration." + path, name);
			if (description != null && !description.isEmpty())
				this.add(Reference.MOD_ID + ".configuration." + path + ".tooltip", description);
		}
	}

	private static class Models extends ModelProvider {
		public static final TextureSlot LOG_SIDE = TextureSlot.create("log_side");
		public static final TextureSlot LOG_TOP = TextureSlot.create("log_top");
		public static final ModelTemplate RAFT = ModelTemplates.createItem("justaraftmod:raft_base", LOG_SIDE, LOG_TOP);

		public Models(PackOutput packOutput) {
			super(packOutput, Reference.MOD_ID);
		}

		@Override
		protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
			createRaft(itemModels, RaftRegistry.ACACIA_RAFT, mcLocation("acacia_log").withPrefix("block/"), mcLocation("acacia_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.BAMBOO_RAFT, mcLocation("bamboo_stalk").withPrefix("block/"), mcLocation("bamboo_stalk").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.BIRCH_RAFT, mcLocation("birch_log").withPrefix("block/"), mcLocation("birch_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.CHERRY_RAFT, mcLocation("cherry_log").withPrefix("block/"), mcLocation("cherry_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.DARK_OAK_RAFT, mcLocation("dark_oak_log").withPrefix("block/"), mcLocation("dark_oak_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.JUNGLE_RAFT, mcLocation("jungle_log").withPrefix("block/"), mcLocation("jungle_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.MANGROVE_RAFT, mcLocation("mangrove_log").withPrefix("block/"), mcLocation("mangrove_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.OAK_RAFT, mcLocation("oak_log").withPrefix("block/"), mcLocation("oak_log_top").withPrefix("block/"));
			createRaft(itemModels, RaftRegistry.SPRUCE_RAFT, mcLocation("spruce_log").withPrefix("block/"), mcLocation("spruce_log_top").withPrefix("block/"));
		}

		private void createRaft(ItemModelGenerators itemModels, DeferredItem<?> deferredItem, ResourceLocation side, ResourceLocation top) {
			ResourceLocation model = RAFT.create(ModelLocationUtils.getModelLocation(deferredItem.get()),
					getRaftMapping(side, top), itemModels.modelOutput);
			itemModels.itemModelOutput.accept(deferredItem.get(), ItemModelUtils.plainModel(model));
		}

		private TextureMapping getRaftMapping(ResourceLocation side, ResourceLocation top) {
			return new TextureMapping().put(LOG_SIDE, side).put(LOG_TOP, top);
		}
//
//		@Override
//		protected void registerModels() {
//			withExistingParent(RaftRegistry.ACACIA_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "acacia_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "acacia_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "acacia_log_top"));
//			withExistingParent(RaftRegistry.BAMBOO_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "bamboo_stalk"));
//			withExistingParent(RaftRegistry.BIRCH_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "birch_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "birch_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "birch_log_top"));
//			withExistingParent(RaftRegistry.CHERRY_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "cherry_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "cherry_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "cherry_log_top"));
//			withExistingParent(RaftRegistry.DARK_OAK_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "dark_oak_log_top"));
//			withExistingParent(RaftRegistry.JUNGLE_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "jungle_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "jungle_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "jungle_log_top"));
//			withExistingParent(RaftRegistry.MANGROVE_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "mangrove_log_top"));
//			withExistingParent(RaftRegistry.OAK_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "oak_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "oak_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "oak_log_top"));
//			withExistingParent(RaftRegistry.SPRUCE_RAFT.getId().getPath(), modLoc("item/raft_base")).texture("particle", mcLoc(BLOCK_FOLDER + "/" + "spruce_log")).texture("log_side", mcLoc(BLOCK_FOLDER + "/" + "spruce_log")).texture("log_top", mcLoc(BLOCK_FOLDER + "/" + "spruce_log_top"));
//		}
	}

	public static class RaftBlockTags extends BlockTagsProvider {
		public RaftBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(output, lookupProvider, Reference.MOD_ID);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {

		}
	}

	public static class RaftItemTags extends ItemTagsProvider {

		public RaftItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, TagsProvider<Block> blockTagProvider) {
			super(output, lookupProvider, blockTagProvider.contentsGetter(), Reference.MOD_ID);
		}

		public static final TagKey<Item> RAFTS = net.minecraft.tags.ItemTags.create(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "rafts"));

		@Override
		public void addTags(HolderLookup.Provider provider) {
			this.tag(RAFTS).add(RaftRegistry.OAK_RAFT.get(), RaftRegistry.SPRUCE_RAFT.get(), RaftRegistry.BIRCH_RAFT.get(), RaftRegistry.JUNGLE_RAFT.get(), RaftRegistry.ACACIA_RAFT.get(), RaftRegistry.DARK_OAK_RAFT.get(), RaftRegistry.BAMBOO_RAFT.get(), RaftRegistry.MANGROVE_RAFT.get(), RaftRegistry.CHERRY_RAFT.get());
		}
	}

	public static class RaftEntityTags extends EntityTypeTagsProvider {

		public RaftEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(output, lookupProvider, Reference.MOD_ID);
		}

		@Override
		public void addTags(HolderLookup.Provider provider) {
			this.tag(Tags.EntityTypes.BOATS).add(RaftRegistry.RAFT.get());
		}
	}

}
