package com.Mrbysco.JustARaftMod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * ModelRaft - Svennieke
 * Created using Tabula 6.0.0
 */
public class ModelRaft extends ModelBase {
    public ModelRenderer Log4;
    public ModelRenderer Log3;
    public ModelRenderer Log2;
    public ModelRenderer Log1;
    public ModelRenderer RopeLeft4;
    public ModelRenderer RopeLeft3;
    public ModelRenderer RopeLeft2;
    public ModelRenderer RopeLeft1;
    public ModelRenderer RopeRight1;
    public ModelRenderer RopeRight2;
    public ModelRenderer RopeRight3;
    public ModelRenderer RopeRight4;

    public ModelRaft() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Log4 = new ModelRenderer(this, 0, 0);
        this.Log4.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log4.addBox(0.0F, 0.0F, -7.0F, 27, 3, 3, 0.0F);
        this.RopeRight4 = new ModelRenderer(this, 0, 20);
        this.RopeRight4.setRotationPoint(-13.0F, 3.0F, 8.0F);
        this.RopeRight4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Log2 = new ModelRenderer(this, 0, 0);
        this.Log2.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log2.addBox(0.0F, 0.0F, 0.2F, 27, 3, 3, 0.0F);
        this.RopeRight1 = new ModelRenderer(this, 0, 20);
        this.RopeRight1.setRotationPoint(-13.0F, 2.0F, -6.0F);
        this.RopeRight1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.RopeLeft3 = new ModelRenderer(this, 0, 20);
        this.RopeLeft3.setRotationPoint(11.0F, 3.0F, -7.0F);
        this.RopeLeft3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Log1 = new ModelRenderer(this, 0, 0);
        this.Log1.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log1.addBox(0.0F, 0.0F, 4.0F, 27, 3, 3, 0.0F);
        this.Log3 = new ModelRenderer(this, 0, 0);
        this.Log3.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log3.addBox(0.0F, 0.0F, -3.5F, 27, 3, 3, 0.0F);
        this.RopeLeft1 = new ModelRenderer(this, 0, 20);
        this.RopeLeft1.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.RopeRight2 = new ModelRenderer(this, 0, 20);
        this.RopeRight2.setRotationPoint(-13.0F, 6.0F, -6.0F);
        this.RopeRight2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.RopeRight3 = new ModelRenderer(this, 0, 20);
        this.RopeRight3.setRotationPoint(-13.0F, 3.0F, -7.0F);
        this.RopeRight3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.RopeLeft4 = new ModelRenderer(this, 0, 20);
        this.RopeLeft4.setRotationPoint(11.0F, 3.0F, 8.0F);
        this.RopeLeft4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.RopeLeft2 = new ModelRenderer(this, 0, 20);
        this.RopeLeft2.setRotationPoint(11.0F, 6.0F, -6.0F);
        this.RopeLeft2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Log4.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeRight4.offsetX, this.RopeRight4.offsetY, this.RopeRight4.offsetZ);
        GlStateManager.translate(this.RopeRight4.rotationPointX * f5, this.RopeRight4.rotationPointY * f5, this.RopeRight4.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeRight4.offsetX, -this.RopeRight4.offsetY, -this.RopeRight4.offsetZ);
        GlStateManager.translate(-this.RopeRight4.rotationPointX * f5, -this.RopeRight4.rotationPointY * f5, -this.RopeRight4.rotationPointZ * f5);
        this.RopeRight4.render(f5);
        GlStateManager.popMatrix();
        this.Log2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeRight1.offsetX, this.RopeRight1.offsetY, this.RopeRight1.offsetZ);
        GlStateManager.translate(this.RopeRight1.rotationPointX * f5, this.RopeRight1.rotationPointY * f5, this.RopeRight1.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeRight1.offsetX, -this.RopeRight1.offsetY, -this.RopeRight1.offsetZ);
        GlStateManager.translate(-this.RopeRight1.rotationPointX * f5, -this.RopeRight1.rotationPointY * f5, -this.RopeRight1.rotationPointZ * f5);
        this.RopeRight1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeLeft3.offsetX, this.RopeLeft3.offsetY, this.RopeLeft3.offsetZ);
        GlStateManager.translate(this.RopeLeft3.rotationPointX * f5, this.RopeLeft3.rotationPointY * f5, this.RopeLeft3.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeLeft3.offsetX, -this.RopeLeft3.offsetY, -this.RopeLeft3.offsetZ);
        GlStateManager.translate(-this.RopeLeft3.rotationPointX * f5, -this.RopeLeft3.rotationPointY * f5, -this.RopeLeft3.rotationPointZ * f5);
        this.RopeLeft3.render(f5);
        GlStateManager.popMatrix();
        this.Log1.render(f5);
        this.Log3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeLeft1.offsetX, this.RopeLeft1.offsetY, this.RopeLeft1.offsetZ);
        GlStateManager.translate(this.RopeLeft1.rotationPointX * f5, this.RopeLeft1.rotationPointY * f5, this.RopeLeft1.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeLeft1.offsetX, -this.RopeLeft1.offsetY, -this.RopeLeft1.offsetZ);
        GlStateManager.translate(-this.RopeLeft1.rotationPointX * f5, -this.RopeLeft1.rotationPointY * f5, -this.RopeLeft1.rotationPointZ * f5);
        this.RopeLeft1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeRight2.offsetX, this.RopeRight2.offsetY, this.RopeRight2.offsetZ);
        GlStateManager.translate(this.RopeRight2.rotationPointX * f5, this.RopeRight2.rotationPointY * f5, this.RopeRight2.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeRight2.offsetX, -this.RopeRight2.offsetY, -this.RopeRight2.offsetZ);
        GlStateManager.translate(-this.RopeRight2.rotationPointX * f5, -this.RopeRight2.rotationPointY * f5, -this.RopeRight2.rotationPointZ * f5);
        this.RopeRight2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeRight3.offsetX, this.RopeRight3.offsetY, this.RopeRight3.offsetZ);
        GlStateManager.translate(this.RopeRight3.rotationPointX * f5, this.RopeRight3.rotationPointY * f5, this.RopeRight3.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeRight3.offsetX, -this.RopeRight3.offsetY, -this.RopeRight3.offsetZ);
        GlStateManager.translate(-this.RopeRight3.rotationPointX * f5, -this.RopeRight3.rotationPointY * f5, -this.RopeRight3.rotationPointZ * f5);
        this.RopeRight3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeLeft4.offsetX, this.RopeLeft4.offsetY, this.RopeLeft4.offsetZ);
        GlStateManager.translate(this.RopeLeft4.rotationPointX * f5, this.RopeLeft4.rotationPointY * f5, this.RopeLeft4.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeLeft4.offsetX, -this.RopeLeft4.offsetY, -this.RopeLeft4.offsetZ);
        GlStateManager.translate(-this.RopeLeft4.rotationPointX * f5, -this.RopeLeft4.rotationPointY * f5, -this.RopeLeft4.rotationPointZ * f5);
        this.RopeLeft4.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RopeLeft2.offsetX, this.RopeLeft2.offsetY, this.RopeLeft2.offsetZ);
        GlStateManager.translate(this.RopeLeft2.rotationPointX * f5, this.RopeLeft2.rotationPointY * f5, this.RopeLeft2.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.09D, 1.0D);
        GlStateManager.translate(-this.RopeLeft2.offsetX, -this.RopeLeft2.offsetY, -this.RopeLeft2.offsetZ);
        GlStateManager.translate(-this.RopeLeft2.rotationPointX * f5, -this.RopeLeft2.rotationPointY * f5, -this.RopeLeft2.rotationPointZ * f5);
        this.RopeLeft2.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
