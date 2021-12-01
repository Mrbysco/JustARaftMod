package com.mrbysco.justaraftmod.entities;

import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class RaftEntity extends Boat {
    public RaftEntity(EntityType<? extends RaftEntity> entityType, Level worldIn)
    {
        super(entityType, worldIn);
    }

    public RaftEntity(Level worldIn, double x, double y, double z) {
        this(RaftRegistry.RAFT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public RaftEntity(SpawnEntity spawnEntity, Level worldIn) {
        this(RaftRegistry.RAFT.get(), worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if(RaftConfig.SERVER.SinkTheRaft.get()) {
            if(this.getPassengers().size() > 1) {
                Vec3 motion = this.getDeltaMovement();
                double newY = motion.y - 0.035D;
                this.setDeltaMovement(motion.x, newY, motion.z);
            }
        }
    }

    @Override
    public Status getStatus() {
        Boat.Status boatentity$status = this.isUnderwater();
        if (boatentity$status != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return boatentity$status;
        } else if (this.checkInWater()) {
            return Boat.Status.IN_WATER;
        } else {
            float f = this.getGroundFriction();
            if (f > 0.0F) {
                this.landFriction = RaftConfig.SERVER.SlipperyFast.get() ? f : 0;
                return Boat.Status.ON_LAND;
            } else {
                return Boat.Status.IN_AIR;
            }
        }
    }

    @Override
    public void floatBoat() {
        double d0 = -0.03999999910593033D;
        double d1 = this.isNoGravity() ? 0.0D : d0;
        double d2 = 0.0D;
        this.invFriction = 0.05F;

        if (this.oldStatus == Boat.Status.IN_AIR && this.status != Boat.Status.IN_AIR && this.status != Boat.Status.ON_LAND) {
            this.waterLevel = this.getBoundingBox().minY + (double)this.getBbHeight();
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = Boat.Status.IN_WATER;
        } else {
            if (this.status == Boat.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getBoundingBox().minY + 0.1D) / (double)this.getBbHeight();
                this.invFriction = 0.9F;
            } else if (this.status == Boat.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.invFriction = 0.9F;
            } else if (this.status == Boat.Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.invFriction = 0.45F;
            } else if (this.status == Boat.Status.IN_AIR) {
                this.invFriction = 0.9F;
            } else if (this.status == Boat.Status.ON_LAND) {
                this.invFriction = this.landFriction;
                if (this.getControllingPassenger() instanceof Player) {
                    this.landFriction /= 2.0F;
                }
            }

            Vec3 Vector3d = this.getDeltaMovement();
            this.setDeltaMovement(Vector3d.x * (double)this.invFriction, Vector3d.y + d1, Vector3d.z * (double)this.invFriction);
            this.deltaRotation *= this.invFriction;
            if (d2 > 0.0D) {
                Vec3 Vector3d1 = this.getDeltaMovement();
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

            this.setYRot(this.getYRot() + this.deltaRotation);
            if (this.inputUp) {
                f += 0.04F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            if (this.inputDown) {
                f -= 0.005F * RaftConfig.SERVER.SpeedMultiplier.get();
            }

            this.setDeltaMovement(this.getDeltaMovement().add((double)(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f), 0.0D, (double)(Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f)));
            this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0D;
    }

    @Override
    public Item getDropItem() {
        return switch (this.getBoatType()) {
            default -> RaftRegistry.OAK_RAFT.get();
            case SPRUCE -> RaftRegistry.SPRUCE_RAFT.get();
            case BIRCH -> RaftRegistry.BIRCH_RAFT.get();
            case JUNGLE -> RaftRegistry.JUNGLE_RAFT.get();
            case ACACIA -> RaftRegistry.ACACIA_RAFT.get();
            case DARK_OAK -> RaftRegistry.DARK_OAK_RAFT.get();
        };
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}