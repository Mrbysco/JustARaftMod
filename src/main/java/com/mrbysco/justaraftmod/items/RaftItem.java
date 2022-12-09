package com.mrbysco.justaraftmod.items;

import com.mrbysco.justaraftmod.entities.Raft;
import com.mrbysco.justaraftmod.init.RaftTab;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class RaftItem extends Item {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private final Raft.Type type;

	public RaftItem(Raft.Type typeIn, Item.Properties properties) {
		super(properties);
		this.type = typeIn;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		HitResult hitResult = getPlayerPOVHitResult(level, playerIn, ClipContext.Fluid.ANY);
		if (hitResult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(stack);
		} else {
			Vec3 Vector3d = playerIn.getViewVector(1.0F);
			List<Entity> list = level.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(Vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
			if (!list.isEmpty()) {
				Vec3 eyePos = playerIn.getEyePosition(1.0F);

				for (Entity entity : list) {
					AABB axisalignedbb = entity.getBoundingBox().inflate((double) entity.getPickRadius());
					if (axisalignedbb.contains(eyePos)) {
						return InteractionResultHolder.pass(stack);
					}
				}
			}

			if (hitResult.getType() == HitResult.Type.BLOCK) {
				Raft raft = new Raft(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
				raft.setRaftType(this.type);
				raft.setYRot(raft.getYRot());
				if (!level.noCollision(raft, raft.getBoundingBox().inflate(-0.1D))) {
					return InteractionResultHolder.fail(stack);
				} else {
					if (!level.isClientSide) {
						level.addFreshEntity(raft);
						level.gameEvent(playerIn, GameEvent.ENTITY_PLACE, new BlockPos(hitResult.getLocation()));
						if (!playerIn.getAbilities().instabuild) {
							stack.shrink(1);
						}
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
				}
			} else {
				return InteractionResultHolder.pass(stack);
			}
		}
	}
}
