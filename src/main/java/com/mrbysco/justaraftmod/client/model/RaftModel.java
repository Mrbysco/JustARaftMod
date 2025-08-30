package com.mrbysco.justaraftmod.client.model;

import com.mrbysco.justaraftmod.client.state.RaftRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RaftModel extends EntityModel<RaftRenderState> {

	public RaftModel(ModelPart part) {
		super(part);
	}

	public static LayerDefinition createRaftDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition definition = meshDefinition.getRoot();

		definition.addOrReplaceChild("logs", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-14.0F, -2.0F, 7.0F, 28.0F, 4.0F, 4.0F)
						.texOffs(0, 0).addBox(-14.0F, -2.0F, 1.0F, 28.0F, 4.0F, 4.0F)
						.texOffs(0, 0).addBox(-14.0F, -2.0F, -5.0F, 28.0F, 4.0F, 4.0F)
						.texOffs(0, 0).addBox(-14.0F, -2.0F, -11.0F, 28.0F, 4.0F, 4.0F),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		definition.addOrReplaceChild("string_front", CubeListBuilder.create()
						.texOffs(0, 58).addBox(-12.0F, 2.0F, -11.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(-12.0F, 2.0F, -5.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(-12.0F, 2.0F, 1.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(-12.0F, 2.0F, 7.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 52).addBox(-12.0F, -2.0F, -7.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 52).addBox(-12.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 52).addBox(-12.0F, -2.0F, 5.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(12, 58).addBox(-12.0F, -2.0F, -13.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(12, 52).addBox(-12.0F, -2.0F, 11.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 46).addBox(-12.0F, -4.0F, -11.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(-12.0F, -4.0F, -5.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(-12.0F, -4.0F, 1.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(-12.0F, -4.0F, 7.0F, 2.0F, 2.0F, 4.0F),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		definition.addOrReplaceChild("string_back", CubeListBuilder.create()
						.texOffs(0, 58).addBox(10.0F, 2.0F, 1.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(10.0F, 2.0F, -11.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(10.0F, 2.0F, -5.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 58).addBox(10.0F, 2.0F, 7.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 52).addBox(10.0F, -2.0F, -7.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 52).addBox(10.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 52).addBox(10.0F, -2.0F, 5.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(12, 58).addBox(10.0F, -2.0F, -13.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(12, 52).addBox(10.0F, -2.0F, 11.0F, 2.0F, 4.0F, 2.0F)
						.texOffs(0, 46).addBox(10.0F, -4.0F, 7.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(10.0F, -4.0F, 1.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(10.0F, -4.0F, -5.0F, 2.0F, 2.0F, 4.0F)
						.texOffs(0, 46).addBox(10.0F, -4.0F, -11.0F, 2.0F, 2.0F, 4.0F),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 128, 128);
	}

	@Override
	public void setupAnim(RaftRenderState renderState) {
		super.setupAnim(renderState);
	}
}