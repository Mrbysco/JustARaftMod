package com.mrbysco.justaraftmod.items;

import com.mrbysco.justaraftmod.entities.Raft;
import com.mrbysco.justaraftmod.entities.RaftType;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RaftItem extends Item {
	private final RaftType type;

	public RaftItem(RaftType typeIn, Item.Properties properties) {
		super(properties);
		this.type = typeIn;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (hitResult.getType() == HitResult.Type.MISS) {
			return InteractionResult.PASS;
		} else {
			Vec3 vec3 = player.getViewVector(1.0F);
			List<Entity> list = level.getEntities(
					player, player.getBoundingBox().expandTowards(vec3.scale(5.0)).inflate(1.0), EntitySelector.CAN_BE_PICKED
			);
			if (!list.isEmpty()) {
				Vec3 eyePos = player.getEyePosition(1.0F);

				for (Entity entity : list) {
					AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (aabb.contains(eyePos)) {
						return InteractionResult.PASS;
					}
				}
			}

			if (hitResult.getType() == HitResult.Type.BLOCK) {
				Raft raft = new Raft(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
				raft.setRaftType(this.type);
				raft.setYRot(player.getYRot());
				if (!level.noCollision(raft, raft.getBoundingBox().inflate(-0.1D))) {
					return InteractionResult.FAIL;
				} else {
					if (!level.isClientSide) {
						level.addFreshEntity(raft);
						level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
						if (!player.getAbilities().instabuild) {
							stack.shrink(1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResult.SUCCESS;
				}
			} else {
				return InteractionResult.PASS;
			}
		}
	}
}
