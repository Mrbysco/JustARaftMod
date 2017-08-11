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
    public ModelRenderer RopeLeft1;
    public ModelRenderer RopeLeft2;
    public ModelRenderer RopeLeft3;
    public ModelRenderer RopeLeft4;
    public ModelRenderer RopeBack1;
    public ModelRenderer RopeBack2;
    public ModelRenderer RopeBack3;
    public ModelRenderer RopeBack4;

    public ModelRaft() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Log1 = new ModelRenderer(this, 0, 0);
        this.Log1.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log1.addBox(-12.0F, 0.0F, 18.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeBack4 = new ModelRenderer(this, 0, 20);
        this.RopeBack4.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeBack4.addBox(-18.0F, 1.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeBack4, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft3 = new ModelRenderer(this, 0, 20);
        this.RopeLeft3.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft3.addBox(6.0F, 4.0F, -19.0F, 1, 1, 15, 0.0F);
        this.setRotateAngle(RopeLeft3, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log4 = new ModelRenderer(this, 0, 0);
        this.Log4.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log4.addBox(-12.0F, 0.0F, 6.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log4, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log3 = new ModelRenderer(this, 0, 0);
        this.Log3.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log3.addBox(-12.0F, 0.0F, 14.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log3, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log2 = new ModelRenderer(this, 0, 0);
        this.Log2.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log2.addBox(-12.0F, 0.0F, 10.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log2, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeBack1 = new ModelRenderer(this, 0, 20);
        this.RopeBack1.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeBack1.addBox(-18.0F, 0.0F, -19.0F, 1, 1, 15, 0.0F);
        this.setRotateAngle(RopeBack1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft4 = new ModelRenderer(this, 0, 20);
        this.RopeLeft4.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft4.addBox(6.0F, 1.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft4, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeBack3 = new ModelRenderer(this, 0, 20);
        this.RopeBack3.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeBack3.addBox(-18.0F, 4.0F, -19.0F, 1, 1, 15, 0.0F);
        this.setRotateAngle(RopeBack3, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft1 = new ModelRenderer(this, 0, 20);
        this.RopeLeft1.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft1.addBox(6.0F, 0.0F, -19.0F, 1, 1, 15, 0.0F);
        this.setRotateAngle(RopeLeft1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft2 = new ModelRenderer(this, 0, 20);
        this.RopeLeft2.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft2.addBox(6.0F, 1.0F, -20.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft2, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeBack2 = new ModelRenderer(this, 0, 20);
        this.RopeBack2.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeBack2.addBox(-18.0F, 1.0F, -20.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeBack2, 0.0F, 1.5707963267948966F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Log1.render(f5);
        this.RopeBack4.render(f5);
        this.RopeLeft3.render(f5);
        this.Log4.render(f5);
        this.Log3.render(f5);
        this.Log2.render(f5);
        this.RopeBack1.render(f5);
        this.RopeLeft4.render(f5);
        this.RopeBack3.render(f5);
        this.RopeLeft1.render(f5);
        this.RopeLeft2.render(f5);
        this.RopeBack2.render(f5);
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
