package com.mrbysco.justaraftmod.entities;

import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class RaftEntity extends BoatEntity
{
    public RaftEntity(EntityType<? extends RaftEntity> entityType, World worldIn)
    {
        super(entityType, worldIn);
    }

    public RaftEntity(World worldIn, double x, double y, double z) {
        this(RaftRegistry.RAFT.get(), worldIn);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public RaftEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(RaftRegistry.RAFT.get(), worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if(RaftConfig.SERVER.SinkTheRaft.get()) {
            if(this.getPassengers().size() > 1) {
                Vector3d motion = this.getMotion();
                double newY = motion.y - 0.035D;
                this.setMotion(motion.x, newY, motion.z);
            }
        }
    }

    @Override
    public Status getBoatStatus() {
        BoatEntity.Status boatentity$status = this.getUnderwaterStatus();
        if (boatentity$status != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return boatentity$status;
        } else if (this.checkInWater()) {
            return BoatEntity.Status.IN_WATER;
        } else {
            float f = this.getBoatGlide();
            if (f > 0.0F) {
                this.boatGlide = RaftConfig.SERVER.SlipperyFast.get() ? f : 0;
                return BoatEntity.Status.ON_LAND;
            } else {
                return BoatEntity.Status.IN_AIR;
            }
        }
    }

    @Override
    public void updateMotion() {
        double d0 = -0.03999999910593033D;
        double d1 = this.hasNoGravity() ? 0.0D : d0;
        double d2 = 0.0D;
        this.momentum = 0.05F;

        if (this.previousStatus == BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.ON_LAND) {
            this.waterLevel = this.getBoundingBox().minY + (double)this.getHeight();
            this.setPosition(this.getPosX(), (double)(this.getWaterLevelAbove() - this.getHeight()) + 0.101D, this.getPosZ());
            this.setMotion(this.getMotion().mul(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = BoatEntity.Status.IN_WATER;
        } else {
            if (this.status == BoatEntity.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getBoundingBox().minY + 0.1D) / (double)this.getHeight();
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.momentum = 0.45F;
            } else if (this.status == BoatEntity.Status.IN_AIR) {
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.ON_LAND) {
                this.momentum = this.boatGlide;
                if (this.getControllingPassenger() instanceof PlayerEntity) {
                    this.boatGlide /= 2.0F;
                }
            }

            Vector3d Vector3d = this.getMotion();
            this.setMotion(Vector3d.x * (double)this.momentum, Vector3d.y + d1, Vector3d.z * (double)this.momentum);
            this.deltaRotation *= this.momentum;
            if (d2 > 0.0D) {
                Vector3d Vector3d1 = this.getMotion();
                this.setMotion(Vector3d1.x, (Vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, Vector3d1.z);
            }
        }
    }

    public void controlBoat() {
        if (this.isBeingRidden()) {
            float f = 0.0F;
            if (this.leftInputDown) {
                this.deltaRotation -= 1F * RaftConfig.SERVER.TurnMultiplier.get();
            }

            if (this.rightInputDown) {
                this.deltaRotation += 1F * RaftConfig.SERVER.TurnMultiplier.get();
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;
            if (this.forwardInputDown) {
                f += 0.04F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            if (this.backInputDown) {
                f -= 0.005F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            this.setMotion(this.getMotion().add((double)(MathHelper.sin(-this.rotationYaw * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * f)));
            this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
        }
    }

    @Override
    public double getMountedYOffset() {
        return 0D;
    }

    @Override
    public Item getItemBoat() {
        switch (this.getBoatType()) {
            default:
                return RaftRegistry.OAK_RAFT.get();
            case SPRUCE:
                return RaftRegistry.SPRUCE_RAFT.get();
            case BIRCH:
                return RaftRegistry.BIRCH_RAFT.get();
            case JUNGLE:
                return RaftRegistry.JUNGLE_RAFT.get();
            case ACACIA:
                return RaftRegistry.ACACIA_RAFT.get();
            case DARK_OAK:
                return RaftRegistry.DARK_OAK_RAFT.get();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}