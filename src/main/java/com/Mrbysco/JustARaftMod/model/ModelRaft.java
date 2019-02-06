package com.mrbysco.justaraftmod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

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
    public ModelRenderer RopeLeft5;
    public ModelRenderer RopeLeft6;
    public ModelRenderer RopeLeft7;
    public ModelRenderer RopeLeft8;
    public ModelRenderer RopeLeft9;
    public ModelRenderer RopeLeft10;
    public ModelRenderer RopeLeft11;
    public ModelRenderer RopeLeft12;
    public ModelRenderer RopeLeft13;
    public ModelRenderer RopeRight1;
    public ModelRenderer RopeRight2;
    public ModelRenderer RopeRight3;
    public ModelRenderer RopeRight4;
    public ModelRenderer RopeRight5;
    public ModelRenderer RopeRight6;
    public ModelRenderer RopeRight7;
    public ModelRenderer RopeRight8;
    public ModelRenderer RopeRight9;
    public ModelRenderer RopeRight10;
    public ModelRenderer RopeRight11;
    public ModelRenderer RopeRight12;
    public ModelRenderer RopeRight13;

    public ModelRaft() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.RopeLeft11 = new ModelRenderer(this, 0, 20);
        this.RopeLeft11.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft11.addBox(6.0F, -1.0F, -19.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft11, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft12 = new ModelRenderer(this, 0, 20);
        this.RopeLeft12.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft12.addBox(6.0F, 0.0F, -20.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft12, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft1 = new ModelRenderer(this, 0, 20);
        this.RopeLeft1.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft1.addBox(6.0F, -1.0F, -15.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight4 = new ModelRenderer(this, 0, 20);
        this.RopeRight4.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight4.addBox(-18.0F, 0.0F, -8.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeRight4, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight1 = new ModelRenderer(this, 0, 20);
        this.RopeRight1.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight1.addBox(-18.0F, -1.0F, -15.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight3 = new ModelRenderer(this, 0, 20);
        this.RopeRight3.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight3.addBox(-18.0F, -1.0F, -7.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight3, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log4 = new ModelRenderer(this, 0, 0);
        this.Log4.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log4.addBox(-12.0F, -1.0F, 6.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log4, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight12 = new ModelRenderer(this, 0, 20);
        this.RopeRight12.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight12.addBox(-18.0F, 0.0F, -20.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeRight12, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight9 = new ModelRenderer(this, 0, 20);
        this.RopeRight9.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight9.addBox(-18.0F, 3.0F, -11.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight9, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight13 = new ModelRenderer(this, 0, 20);
        this.RopeRight13.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight13.addBox(-18.0F, 0.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeRight13, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft9 = new ModelRenderer(this, 0, 20);
        this.RopeLeft9.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft9.addBox(6.0F, 3.0F, -11.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft9, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight8 = new ModelRenderer(this, 0, 20);
        this.RopeRight8.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight8.addBox(-18.0F, 3.0F, -15.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight8, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log1 = new ModelRenderer(this, 0, 0);
        this.Log1.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log1.addBox(-12.0F, -1.0F, 18.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft4 = new ModelRenderer(this, 0, 20);
        this.RopeLeft4.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft4.addBox(6.0F, 0.0F, -8.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft4, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft5 = new ModelRenderer(this, 0, 20);
        this.RopeLeft5.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft5.addBox(6.0F, 0.0F, -12.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft5, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight5 = new ModelRenderer(this, 0, 20);
        this.RopeRight5.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight5.addBox(-18.0F, 0.0F, -12.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeRight5, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight11 = new ModelRenderer(this, 0, 20);
        this.RopeRight11.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight11.addBox(-18.0F, -1.0F, -19.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight11, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight10 = new ModelRenderer(this, 0, 20);
        this.RopeRight10.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight10.addBox(-18.0F, 3.0F, -7.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight10, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log3 = new ModelRenderer(this, 0, 0);
        this.Log3.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log3.addBox(-12.0F, -1.0F, 14.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log3, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft8 = new ModelRenderer(this, 0, 20);
        this.RopeLeft8.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft8.addBox(6.0F, 3.0F, -15.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft8, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight7 = new ModelRenderer(this, 0, 20);
        this.RopeRight7.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight7.addBox(-18.0F, 3.0F, -19.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight7, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight6 = new ModelRenderer(this, 0, 20);
        this.RopeRight6.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight6.addBox(-18.0F, 0.0F, -16.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeRight6, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft2 = new ModelRenderer(this, 0, 20);
        this.RopeLeft2.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft2.addBox(6.0F, -1.0F, -11.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft2, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft3 = new ModelRenderer(this, 0, 20);
        this.RopeLeft3.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft3.addBox(6.0F, -1.0F, -7.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft3, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft7 = new ModelRenderer(this, 0, 20);
        this.RopeLeft7.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft7.addBox(6.0F, 3.0F, -19.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft7, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeRight2 = new ModelRenderer(this, 0, 20);
        this.RopeRight2.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeRight2.addBox(-18.0F, -1.0F, -11.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeRight2, 0.0F, 1.5707963267948966F, 0.0F);
        this.Log2 = new ModelRenderer(this, 0, 0);
        this.Log2.setRotationPoint(-14.0F, 3.0F, 1.0F);
        this.Log2.addBox(-12.0F, -1.0F, 10.0F, 27, 3, 3, 0.0F);
        this.setRotateAngle(Log2, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft10 = new ModelRenderer(this, 0, 20);
        this.RopeLeft10.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft10.addBox(6.0F, 3.0F, -7.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RopeLeft10, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft6 = new ModelRenderer(this, 0, 20);
        this.RopeLeft6.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft6.addBox(6.0F, 0.0F, -16.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft6, 0.0F, 1.5707963267948966F, 0.0F);
        this.RopeLeft13 = new ModelRenderer(this, 0, 20);
        this.RopeLeft13.setRotationPoint(11.0F, 2.0F, -6.0F);
        this.RopeLeft13.addBox(6.0F, 0.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(RopeLeft13, 0.0F, 1.5707963267948966F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RopeLeft11.render(f5);
        this.RopeLeft12.render(f5);
        this.RopeLeft1.render(f5);
        this.RopeRight4.render(f5);
        this.RopeRight1.render(f5);
        this.RopeRight3.render(f5);
        this.Log4.render(f5);
        this.RopeRight12.render(f5);
        this.RopeRight9.render(f5);
        this.RopeRight13.render(f5);
        this.RopeLeft9.render(f5);
        this.RopeRight8.render(f5);
        this.Log1.render(f5);
        this.RopeLeft4.render(f5);
        this.RopeLeft5.render(f5);
        this.RopeRight5.render(f5);
        this.RopeRight11.render(f5);
        this.RopeRight10.render(f5);
        this.Log3.render(f5);
        this.RopeLeft8.render(f5);
        this.RopeRight7.render(f5);
        this.RopeRight6.render(f5);
        this.RopeLeft2.render(f5);
        this.RopeLeft3.render(f5);
        this.RopeLeft7.render(f5);
        this.RopeRight2.render(f5);
        this.Log2.render(f5);
        this.RopeLeft10.render(f5);
        this.RopeLeft6.render(f5);
        this.RopeLeft13.render(f5);
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