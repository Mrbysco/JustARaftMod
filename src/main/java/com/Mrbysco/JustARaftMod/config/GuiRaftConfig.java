package com.Mrbysco.JustARaftMod.config;

import java.util.List;

import com.Mrbysco.JustARaftMod.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiRaftConfig extends GuiConfig {
	private static final String LANG_PREFIX = Reference.MOD_ID + ".category.";

	public GuiRaftConfig(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(), Reference.MOD_ID, false, false, I18n.format(Reference.MOD_ID + ".config.title"));
	}

	private static List<IConfigElement> getConfigElements() {
		final Configuration configuration = RaftConfigGen.ConfigurationHolder.getConfiguration();

		final ConfigCategory topLevelCategory = configuration.getCategory(Configuration.CATEGORY_GENERAL);
		topLevelCategory.getChildren().forEach(configCategory -> configCategory.setLanguageKey(LANG_PREFIX + configCategory.getName()));

		return new ConfigElement(topLevelCategory).getChildElements();
	}
}