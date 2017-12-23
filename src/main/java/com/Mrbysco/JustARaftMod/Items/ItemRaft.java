package com.Mrbysco.JustARaftMod.Items;

import java.util.List;

import com.Mrbysco.JustARaftMod.JustARaftMod;
import com.Mrbysco.JustARaftMod.entity.EntityRaft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemRaft extends Item{
	
	private final EntityRaft.Type type;
	
	public ItemRaft(EntityRaft.Type typeIn) 
	{
		this.type = typeIn;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.TRANSPORTATION);
		this.setCreativeTab(JustARaftMod.tabRaft);
		this.setUnlocalizedName(typeIn.getName() + "raft");
		this.setRegistryName(typeIn.getName() + "_raft");
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        float f = 1.0F;
        float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * 1.0F;
        float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * 1.0F;
        double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * 1.0D;
        double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * 1.0D + (double)playerIn.getEyeHeight();
        double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * 1.0D;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3d vec3d1 = vec3d.addVector((double)f7 * 5.0D, (double)f6 * 5.0D, (double)f8 * 5.0D);
        RayTraceResult raytraceresult = worldIn.rayTraceBlocks(vec3d, vec3d1, true);

        if (raytraceresult == null)
        {
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        else
        {
            Vec3d vec3d2 = playerIn.getLook(1.0F);
            boolean flag = false;
            List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getEntityBoundingBox().addCoord(vec3d2.xCoord * 5.0D, vec3d2.yCoord * 5.0D, vec3d2.zCoord * 5.0D).expandXyz(1.0D));

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity = (Entity)list.get(i);

                if (entity.canBeCollidedWith())
                {
                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expandXyz((double)entity.getCollisionBorderSize());

                    if (axisalignedbb.isVecInside(vec3d))
                    {
                        flag = true;
                    }
                }
            }

            if (flag)
            {
                return new ActionResult(EnumActionResult.PASS, itemStackIn);
            }
            else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
            {
                return new ActionResult(EnumActionResult.PASS, itemStackIn);
            }
            else
            {
                Block block = worldIn.getBlockState(raytraceresult.getBlockPos()).getBlock();
                boolean flag1 = block == Blocks.WATER || block == Blocks.FLOWING_WATER;
                EntityRaft entityraft = new EntityRaft(worldIn, raytraceresult.hitVec.xCoord, flag1 ? raytraceresult.hitVec.yCoord - 0.12D : raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
                entityraft.setRaftType(this.type);
                entityraft.rotationYaw = playerIn.rotationYaw;

                if (!worldIn.getCollisionBoxes(entityraft, entityraft.getEntityBoundingBox().expandXyz(-0.1D)).isEmpty())
                {
                    return new ActionResult(EnumActionResult.FAIL, itemStackIn);
                }
                else
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.spawnEntityInWorld(entityraft);
                    }

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        --itemStackIn.stackSize;
                    }

                    playerIn.addStat(StatList.getObjectUseStats(this));
                    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
                }
            }
        }
    }
}