package com.Mrbysco.JustARaftMod.config;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class GuiRaftConfigFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return GuiRaftConfig.class;
	}

	@Nullable
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Nullable
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}