package com.mrbysco.justaraftmod.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RaftModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer Raft;
	private final ModelRenderer Logs;
	private final ModelRenderer StringFront;
	private final ModelRenderer StringBack;

	public RaftModel() {
		texWidth = 128;
		texHeight = 128;

		Raft = new ModelRenderer(this);
		Raft.setPos(0.0F, 0.0F, 0.0F);


		Logs = new ModelRenderer(this);
		Logs.setPos(0.0F, 4.0F, 0.0F);
		Raft.addChild(Logs);
		Logs.texOffs(0, 0).addBox(-14.0F, -2.0F, 7.0F, 28.0F, 4.0F, 4.0F, 0.0F, true);
		Logs.texOffs(0, 0).addBox(-14.0F, -2.0F, 1.0F, 28.0F, 4.0F, 4.0F, 0.0F, true);
		Logs.texOffs(0, 0).addBox(-14.0F, -2.0F, -5.0F, 28.0F, 4.0F, 4.0F, 0.0F, true);
		Logs.texOffs(0, 0).addBox(-14.0F, -2.0F, -11.0F, 28.0F, 4.0F, 4.0F, 0.0F, true);

		StringFront = new ModelRenderer(this);
		StringFront.setPos(0.0F, 4.0F, 0.0F);
		Raft.addChild(StringFront);
		StringFront.texOffs(0, 58).addBox(-12.0F, 2.0F, -11.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 58).addBox(-12.0F, 2.0F, -5.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 58).addBox(-12.0F, 2.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 58).addBox(-12.0F, 2.0F, 7.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 52).addBox(-12.0F, -2.0F, -7.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringFront.texOffs(0, 52).addBox(-12.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringFront.texOffs(0, 52).addBox(-12.0F, -2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringFront.texOffs(12, 58).addBox(-12.0F, -2.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringFront.texOffs(12, 52).addBox(-12.0F, -2.0F, 11.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringFront.texOffs(0, 46).addBox(-12.0F, -4.0F, -11.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 46).addBox(-12.0F, -4.0F, -5.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 46).addBox(-12.0F, -4.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringFront.texOffs(0, 46).addBox(-12.0F, -4.0F, 7.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

		StringBack = new ModelRenderer(this);
		StringBack.setPos(0.0F, 4.0F, 0.0F);
		Raft.addChild(StringBack);
		StringBack.texOffs(0, 58).addBox(10.0F, 2.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 58).addBox(10.0F, 2.0F, -11.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 58).addBox(10.0F, 2.0F, -5.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 58).addBox(10.0F, 2.0F, 7.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 52).addBox(10.0F, -2.0F, -7.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringBack.texOffs(0, 52).addBox(10.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringBack.texOffs(0, 52).addBox(10.0F, -2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringBack.texOffs(12, 58).addBox(10.0F, -2.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringBack.texOffs(12, 52).addBox(10.0F, -2.0F, 11.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		StringBack.texOffs(0, 46).addBox(10.0F, -4.0F, 7.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 46).addBox(10.0F, -4.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 46).addBox(10.0F, -4.0F, -5.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
		StringBack.texOffs(0, 46).addBox(10.0F, -4.0F, -11.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Raft.render(matrixStack, buffer, packedLight, packedOverlay);
		Logs.render(matrixStack, buffer, packedLight, packedOverlay);
		StringFront.render(matrixStack, buffer, packedLight, packedOverlay);
		StringBack.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}