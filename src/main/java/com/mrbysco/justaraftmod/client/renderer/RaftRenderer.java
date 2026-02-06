package com.mrbysco.justaraftmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.client.ClientHandler;
import com.mrbysco.justaraftmod.client.model.RaftModel;
import com.mrbysco.justaraftmod.client.state.RaftRenderState;
import com.mrbysco.justaraftmod.entities.Raft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class RaftRenderer extends EntityRenderer<Raft, RaftRenderState> {
	private static final Identifier DEFAULT_TEXTURE = Reference.modLoc("textures/entity/raft/oak_raft.png");
	private final RaftModel model;

	public RaftRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new RaftModel(context.bakeLayer(ClientHandler.RAFT));
		this.shadowRadius = 0.5F;
	}

	@Override
	public RaftRenderState createRenderState() {
		return new RaftRenderState();
	}

	@Override
	public void extractRenderState(Raft raft, RaftRenderState renderState, float partialTick) {
		super.extractRenderState(raft, renderState, partialTick);
		renderState.yRot = raft.getYRot(partialTick);
		renderState.hurtTime = (float) raft.getHurtTime() - partialTick;
		renderState.hurtDir = raft.getHurtDir();
		renderState.damageTime = Math.max(raft.getDamage() - partialTick, 0.0F);
		renderState.bubbleAngle = raft.getBubbleAngle(partialTick);
		renderState.isUnderWater = raft.isUnderWater();
		renderState.rowingTimeLeft = raft.getRowingTime(0, partialTick);
		renderState.rowingTimeRight = raft.getRowingTime(1, partialTick);

		renderState.textureLocation = getTextureLocation(raft);
	}

	@Override
	public void submit(RaftRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.translate(0.0F, 0.375F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - renderState.yRot));
		float f = renderState.hurtTime;
		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * renderState.damageTime / 10.0F * renderState.hurtDir));
		}

		if (!renderState.isUnderWater && !Mth.equal(renderState.bubbleAngle, 0.0F)) {
			poseStack.mulPose(new Quaternionf().setAngleAxis(renderState.bubbleAngle * (float) (Math.PI / 180.0), 1.0F, 0.0F, 1.0F));
		}

		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
		RenderType renderType = this.model.renderType(renderState.textureLocation);
		nodeCollector.submitModel(
				this.model, renderState, poseStack, renderType, renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null
		);
		poseStack.popPose();
		super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
	}

	public Identifier getTextureLocation(Raft entity) {
		if (entity.getRaftType().getTextureLocation() == null) {
			return DEFAULT_TEXTURE;
		}
		return entity.getRaftType().getTextureLocation();
	}
}
