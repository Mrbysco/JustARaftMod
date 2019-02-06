package com.mrbysco.justaraftmod.render;

import com.mrbysco.justaraftmod.entity.EntityRaft;
import com.mrbysco.justaraftmod.model.ModelRaft;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRaft extends Render<EntityRaft>
{
	public static final Factory FACTORY = new Factory();
	
    private static final ResourceLocation[] RAFT_TEXTURES = new ResourceLocation[] {new ResourceLocation("jarm:textures/entity/raft/raft_oak.png"), new ResourceLocation("jarm:textures/entity/raft/raft_spruce.png"), new ResourceLocation("jarm:textures/entity/raft/raft_birch.png"), new ResourceLocation("jarm:textures/entity/raft/raft_jungle.png"), new ResourceLocation("jarm:textures/entity/raft/raft_acacia.png"), new ResourceLocation("jarm:textures/entity/raft/raft_darkoak.png")};
    /** instance of ModelRaft for rendering */
    protected ModelBase modelRaft = new ModelRaft();

    public RenderRaft(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityRaft entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.modelRaft.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(EntityRaft raft, float float1, float float2)
    {
        GlStateManager.rotate(180.0F - float1, 0.0F, 1.0F, 0.0F);
        float f = (float)raft.getTimeSinceHit() - float2;
        float f1 = (float)raft.getDamageTaken() - float2;

        if (f1 < 0.0F)
            f1 = 0.0F;

        if (f > 0.0F)
            GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)raft.getForwardDirection(), 1.0F, 0.0F, 0.0F);

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z)
    {
        GlStateManager.translate((float)x, (float)y + 0.375F, (float)z);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityRaft entity)
    {
        return RAFT_TEXTURES[entity.getRaftType().ordinal()];
    }

    public static class Factory implements IRenderFactory<EntityRaft> {
    	@Override
	    public Render<? super EntityRaft> createRenderFor(RenderManager manager) {
	      return new RenderRaft(manager);
    	}
    }
}