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

import net.minecraft.entity.item.BoatEntity.Status;

public class RaftEntity extends BoatEntity
{
    public RaftEntity(EntityType<? extends RaftEntity> entityType, World worldIn)
    {
        super(entityType, worldIn);
    }

    public RaftEntity(World worldIn, double x, double y, double z) {
        this(RaftRegistry.RAFT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public RaftEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(RaftRegistry.RAFT.get(), worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if(RaftConfig.SERVER.SinkTheRaft.get()) {
            if(this.getPassengers().size() > 1) {
                Vector3d motion = this.getDeltaMovement();
                double newY = motion.y - 0.035D;
                this.setDeltaMovement(motion.x, newY, motion.z);
            }
        }
    }

    @Override
    public Status getStatus() {
        BoatEntity.Status boatentity$status = this.isUnderwater();
        if (boatentity$status != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return boatentity$status;
        } else if (this.checkInWater()) {
            return BoatEntity.Status.IN_WATER;
        } else {
            float f = this.getGroundFriction();
            if (f > 0.0F) {
                this.landFriction = RaftConfig.SERVER.SlipperyFast.get() ? f : 0;
                return BoatEntity.Status.ON_LAND;
            } else {
                return BoatEntity.Status.IN_AIR;
            }
        }
    }

    @Override
    public void floatBoat() {
        double d0 = -0.03999999910593033D;
        double d1 = this.isNoGravity() ? 0.0D : d0;
        double d2 = 0.0D;
        this.invFriction = 0.05F;

        if (this.oldStatus == BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.ON_LAND) {
            this.waterLevel = this.getBoundingBox().minY + (double)this.getBbHeight();
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = BoatEntity.Status.IN_WATER;
        } else {
            if (this.status == BoatEntity.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getBoundingBox().minY + 0.1D) / (double)this.getBbHeight();
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.invFriction = 0.45F;
            } else if (this.status == BoatEntity.Status.IN_AIR) {
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.ON_LAND) {
                this.invFriction = this.landFriction;
                if (this.getControllingPassenger() instanceof PlayerEntity) {
                    this.landFriction /= 2.0F;
                }
            }

            Vector3d Vector3d = this.getDeltaMovement();
            this.setDeltaMovement(Vector3d.x * (double)this.invFriction, Vector3d.y + d1, Vector3d.z * (double)this.invFriction);
            this.deltaRotation *= this.invFriction;
            if (d2 > 0.0D) {
                Vector3d Vector3d1 = this.getDeltaMovement();
                this.setDeltaMovement(Vector3d1.x, (Vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, Vector3d1.z);
            }
        }
    }

    public void controlBoat() {
        if (this.isVehicle()) {
            float f = 0.0F;
            if (this.inputLeft) {
                this.deltaRotation -= 1F * RaftConfig.SERVER.TurnMultiplier.get();
            }

            if (this.inputRight) {
                this.deltaRotation += 1F * RaftConfig.SERVER.TurnMultiplier.get();
            }

            if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
                f += 0.005F;
            }

            this.yRot += this.deltaRotation;
            if (this.inputUp) {
                f += 0.04F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            if (this.inputDown) {
                f -= 0.005F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            this.setDeltaMovement(this.getDeltaMovement().add((double)(MathHelper.sin(-this.yRot * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.yRot * ((float)Math.PI / 180F)) * f)));
            this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0D;
    }

    @Override
    public Item getDropItem() {
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}