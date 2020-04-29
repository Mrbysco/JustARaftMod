package com.mrbysco.justaraftmod.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mrbysco.justaraftmod.entities.RaftEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class RaftModel<T extends Entity> extends EntityModel<T> {
	private final RendererModel Raft;
	private final RendererModel Logs;
	private final RendererModel StringFront;
	private final RendererModel StringBack;

	public RaftModel() {
		textureWidth = 128;
		textureHeight = 128;

		Raft = new RendererModel(this);
		Raft.setRotationPoint(0.0F, 0.0F, 0.0F);


		Logs = new RendererModel(this);
		Logs.setRotationPoint(0.0F, 4.0F, 0.0F);
		Raft.addChild(Logs);
		Logs.cubeList.add(new ModelBox(Logs, 0, 0, -14.0F, -2.0F, 7.0F, 28, 4, 4, 0.0F));
		Logs.cubeList.add(new ModelBox(Logs, 0, 0, -14.0F, -2.0F, 1.0F, 28, 4, 4, 0.0F));
		Logs.cubeList.add(new ModelBox(Logs, 0, 0, -14.0F, -2.0F, -5.0F, 28, 4, 4, 0.0F));
		Logs.cubeList.add(new ModelBox(Logs, 0, 0, -14.0F, -2.0F, -11.0F, 28, 4, 4, 0.0F));

		StringFront = new RendererModel(this);
		StringFront.setRotationPoint(0.0F, 4.0F, 0.0F);
		Raft.addChild(StringFront);
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 58, -12.0F, 2.0F, -11.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 58, -12.0F, 2.0F, -5.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 58, -12.0F, 2.0F, 1.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 58, -12.0F, 2.0F, 7.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 52, -12.0F, -2.0F, -7.0F, 2, 4, 2, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 52, -12.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 52, -12.0F, -2.0F, 5.0F, 2, 4, 2, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 12, 58, -12.0F, -2.0F, -13.0F, 2, 4, 2, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 12, 52, -12.0F, -2.0F, 11.0F, 2, 4, 2, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 46, -12.0F, -4.0F, -11.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 46, -12.0F, -4.0F, -5.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 46, -12.0F, -4.0F, 1.0F, 2, 2, 4, 0.0F));
		StringFront.cubeList.add(new ModelBox(StringFront, 0, 46, -12.0F, -4.0F, 7.0F, 2, 2, 4, 0.0F));

		StringBack = new RendererModel(this);
		StringBack.setRotationPoint(0.0F, 4.0F, 0.0F);
		Raft.addChild(StringBack);
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 58, 10.0F, 2.0F, 1.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 58, 10.0F, 2.0F, -11.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 58, 10.0F, 2.0F, -5.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 58, 10.0F, 2.0F, 7.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 52, 10.0F, -2.0F, -7.0F, 2, 4, 2, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 52, 10.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 52, 10.0F, -2.0F, 5.0F, 2, 4, 2, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 12, 58, 10.0F, -2.0F, -13.0F, 2, 4, 2, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 12, 52, 10.0F, -2.0F, 11.0F, 2, 4, 2, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 46, 10.0F, -4.0F, 7.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 46, 10.0F, -4.0F, 1.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 46, 10.0F, -4.0F, -5.0F, 2, 2, 4, 0.0F));
		StringBack.cubeList.add(new ModelBox(StringBack, 0, 46, 10.0F, -4.0F, -11.0F, 2, 2, 4, 0.0F));
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		Raft.render(scale);
		Logs.render(scale);
		StringFront.render(scale);
		StringBack.render(scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void renderMultipass(RaftEntity entityIn, float partialTicks, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float scale) {
		GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
	}
}