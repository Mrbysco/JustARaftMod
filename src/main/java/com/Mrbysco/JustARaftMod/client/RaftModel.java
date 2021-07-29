package com.mrbysco.justaraftmod.client;

import com.mrbysco.justaraftmod.entities.RaftEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RaftModel extends HierarchicalModel<RaftEntity> {
	private final ModelPart raft;
	private final ModelPart logs;
	private final ModelPart stringFront;
	private final ModelPart stringBack;

	public RaftModel(ModelPart part) {
		raft = part;
		logs = part.getChild("logs");
		stringFront = part.getChild("string_front");
		stringBack = part.getChild("string_back");
	}

	public static LayerDefinition createRaftDefenition() {
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
	public void setupAnim(RaftEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public ModelPart root() {
		return raft;
	}
}