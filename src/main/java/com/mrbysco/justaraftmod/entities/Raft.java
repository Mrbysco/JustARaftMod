package com.mrbysco.justaraftmod.entities;

import com.mrbysco.justaraftmod.config.RaftConfig;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class Raft extends AbstractBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Raft.class, EntityDataSerializers.INT);

	public Raft(EntityType<? extends Raft> entityType, Level level) {
		super(entityType, level, () -> Items.STICK);
		this.dropItem = this::getDrop;
	}

	public Raft(Level level, double x, double y, double z) {
		this(RaftRegistry.RAFT.get(), level);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_TYPE, 0);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.putString("Type", this.getRaftType().getName());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		this.setRaftType(RaftType.byName(input.getStringOr("Type", "")));
	}

	@Override
	protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGround) {
				if (this.fallDistance > 3.0F) {
					if (this.status != AbstractBoat.Status.ON_LAND) {
						this.resetFallDistance();
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, this.damageSources().fall());
					if (this.level() instanceof ServerLevel serverLevel && !this.isRemoved()) {
						this.kill(serverLevel);
						if (serverLevel.getGameRules().get(GameRules.ENTITY_DROPS)) {
							for (int i = 0; i < 3; ++i) {
								this.spawnAtLocation(serverLevel, this.getRaftType().getPlanks());
							}

							for (int j = 0; j < 2; ++j) {
								this.spawnAtLocation(serverLevel, Items.STICK);
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
	public AbstractBoat.Status getStatus() {
		AbstractBoat.Status boatStatus = this.isUnderwater();
		if (boatStatus != null) {
			this.waterLevel = this.getBoundingBox().maxY;
			return boatStatus;
		} else if (this.checkInWater()) {
			return AbstractBoat.Status.IN_WATER;
		} else {
			float f = this.getGroundFriction();
			if (f > 0.0F) {
				this.landFriction = RaftConfig.SERVER.SlipperyFast.get() ? f : 0;
				return AbstractBoat.Status.ON_LAND;
			} else {
				return AbstractBoat.Status.IN_AIR;
			}
		}
	}

	@Override
	public void floatBoat() {
		double d1 = this.isNoGravity() ? 0.0D : (double) -0.04F;
		double d2 = 0.0D;
		float f = 0.05F;
		if (this.oldStatus == AbstractBoat.Status.IN_AIR && this.status != AbstractBoat.Status.IN_AIR && this.status != AbstractBoat.Status.ON_LAND) {
			this.waterLevel = this.getBoundingBox().minY + (double) this.getBbHeight();
			this.setPos(this.getX(), (double) (this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
			this.lastYd = 0.0D;
			this.status = AbstractBoat.Status.IN_WATER;
		} else {
			if (this.status == AbstractBoat.Status.IN_WATER) {
				d2 = (this.waterLevel - this.getBoundingBox().minY + 0.1D) / (double) this.getBbHeight();
				f = 0.9F;
			} else if (this.status == AbstractBoat.Status.UNDER_FLOWING_WATER) {
				d1 = -7.0E-4D;
				f = 0.9F;
			} else if (this.status == AbstractBoat.Status.UNDER_WATER) {
				d2 = 0.01F;
				f = 0.45F;
			} else if (this.status == AbstractBoat.Status.IN_AIR) {
				f = 0.9F;
			} else if (this.status == AbstractBoat.Status.ON_LAND) {
				f = this.landFriction;
				if (this.getControllingPassenger() instanceof Player) {
					this.landFriction /= 2.0F;
				}
			}

			Vec3 Vector3d = this.getDeltaMovement();
			this.setDeltaMovement(Vector3d.x * (double) f, Vector3d.y + d1, Vector3d.z * (double) f);
			this.deltaRotation *= f;
			if (d2 > 0.0D) {
				Vec3 Vector3d1 = this.getDeltaMovement();
				this.setDeltaMovement(Vector3d1.x, (Vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, Vector3d1.z);
			}
		}
	}

	@Override
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

			this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
		}
	}

	@Override
	public float getSinglePassengerXOffset() {
		return 0.0F;
	}

	public Item getDrop() {
		if (this.getRaftType() == null) {
			return Items.STICK;
		}
		return this.getRaftType().getRaft().value();
	}

	public void setRaftType(RaftType type) {
		this.entityData.set(DATA_ID_TYPE, type.getId());
	}

	public RaftType getRaftType() {
		return RaftType.byId(this.entityData.get(DATA_ID_TYPE));
	}

	@Override
	protected double rideHeight(EntityDimensions dimensions) {
		return (double) (dimensions.height() * 0.8888889F);
	}
}