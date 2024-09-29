package com.mrbysco.justaraftmod.entities;

import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class Raft extends Boat {
	public Raft(EntityType<? extends Raft> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public Raft(Level worldIn, double x, double y, double z) {
		this(RaftRegistry.RAFT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public Raft(SpawnEntity spawnEntity, Level worldIn) {
		this(RaftRegistry.RAFT.get(), worldIn);
	}

	protected void addAdditionalSaveData(CompoundTag tag) {
		tag.putString("Type", this.getRaftType().getName());
	}

	protected void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("Type", 8)) {
			this.setRaftType(RaftType.byName(tag.getString("Type")));
		}
	}

	protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGround) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Boat.Status.ON_LAND) {
						this.resetFallDistance();
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, this.damageSources().fall());
					if (!this.level().isClientSide && !this.isRemoved()) {
						this.kill();
						if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							for (int i = 0; i < 3; ++i) {
								this.spawnAtLocation(this.getRaftType().getPlanks());
							}

							for (int j = 0; j < 2; ++j) {
								this.spawnAtLocation(Items.STICK);
							}
						}
					}
				}

				this.resetFallDistance();
			} else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance -= (float) y;
			}

		}
	}

	@Override
	public void tick() {
		super.tick();
		if (RaftConfig.SERVER.SinkTheRaft.get()) {
			if (this.getPassengers().size() > 1) {
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
		double d1 = this.isNoGravity() ? 0.0D : (double) -0.04F;
		double d2 = 0.0D;
		this.invFriction = 0.05F;
		if (this.oldStatus == Boat.Status.IN_AIR && this.status != Boat.Status.IN_AIR && this.status != Boat.Status.ON_LAND) {
			this.waterLevel = this.getBoundingBox().minY + (double) this.getBbHeight();
			this.setPos(this.getX(), (double) (this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
			this.lastYd = 0.0D;
			this.status = Boat.Status.IN_WATER;
		} else {
			if (this.status == Boat.Status.IN_WATER) {
				d2 = (this.waterLevel - this.getBoundingBox().minY + 0.1D) / (double) this.getBbHeight();
				this.invFriction = 0.9F;
			} else if (this.status == Boat.Status.UNDER_FLOWING_WATER) {
				d1 = -7.0E-4D;
				this.invFriction = 0.9F;
			} else if (this.status == Boat.Status.UNDER_WATER) {
				d2 = (double) 0.01F;
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
			this.setDeltaMovement(Vector3d.x * (double) this.invFriction, Vector3d.y + d1, Vector3d.z * (double) this.invFriction);
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
				this.deltaRotation -= RaftConfig.SERVER.TurnMultiplier.get().floatValue();
			}

			if (this.inputRight) {
				this.deltaRotation += RaftConfig.SERVER.TurnMultiplier.get().floatValue();
			}

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
				f += 0.005F;
			}

			this.setYRot(this.getYRot() + this.deltaRotation);
			if (this.inputUp) {
				f += 0.04F * RaftConfig.SERVER.SpeedMultiplier.get().floatValue();
			}

			if (this.inputDown) {
				f -= 0.005F * RaftConfig.SERVER.SpeedMultiplier.get().floatValue();
			}

			this.setDeltaMovement(this.getDeltaMovement().add((double) (Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f), 0.0D, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f)));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
		}
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0D;
	}

	@Override
	public Item getDropItem() {
		return this.getRaftType().getRaft().get();
	}

	public void setRaftType(RaftType type) {
		this.entityData.set(DATA_ID_TYPE, type.getId());
	}

	public RaftType getRaftType() {
		return RaftType.byId(this.entityData.get(DATA_ID_TYPE));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}