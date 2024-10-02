package com.mrbysco.justaraftmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mrbysco.justaraftmod.Reference;
import com.mrbysco.justaraftmod.entities.Raft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class RaftRenderer extends EntityRenderer<Raft> {
	private static final ResourceLocation DEFAULT_TEXTURE = Reference.modLoc("textures/entity/raft/oak_raft.png");
	private final RaftModel model;

	public RaftRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new RaftModel(context.bakeLayer(ClientHandler.RAFT));
		this.shadowRadius = 0.5F;
	}

	public void render(Raft raft, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn) {
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.375D, 0.0D);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) raft.getHurtTime() - partialTicks;
		float f1 = raft.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) raft.getHurtDir()));
		}

		float f2 = raft.getBubbleAngle(partialTicks);
		if (!Mth.equal(f2, 0.0F)) {
			poseStack.mulPose((new Quaternionf()).setAngleAxis(raft.getBubbleAngle(partialTicks) * ((float) Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
		this.model.setupAnim(raft, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(raft)));
		this.model.renderToBuffer(poseStack, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
		super.render(raft, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
	}

	public ResourceLocation getTextureLocation(Raft entity) {
		if (entity.getRaftType().getTextureLocation() == null) {
			return DEFAULT_TEXTURE;
		}
		return entity.getRaftType().getTextureLocation();
	}
}
