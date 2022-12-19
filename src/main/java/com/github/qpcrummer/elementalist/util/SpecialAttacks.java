package com.github.qpcrummer.elementalist.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SpecialAttacks {
    /**
     * Converts seconds to ticks
     * @param sec The amount of seconds
     * @return The amount of seconds in terms of ticks
     */
    public static int sec2Ticks(int sec) {
        return sec*20;
    }

    /**
     * Freezes the entity and deals damage
     * @param entity The entity being stunned
     * @param time The time in seconds the stun should last
     * @param dmg The damage the entity should take
     */
    public static void stun(Entity entity, int time, float dmg) {;
        if (entity instanceof LivingEntity) {
            entity.damage(DamageSource.MAGIC, dmg);
            time = sec2Ticks(time);
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, time, 50));
        }
    }

    /**
     * Launches the player to their target
     * @param result EntityHitResult
     * @param current Current position of the User
     * @param height Extra height on the lunge
     * @param dmg Damage dealt on arrival
     * @return Returns the Vec3d of the velocity required
     */
    public static Vec3d lunge(EntityHitResult result, Vec3d current, double height, float dmg) {
        Entity entity = result.getEntity();
        Vec3d destination = entity.getPos();
        double gravity = 1.0;
        double distance = current.distanceTo(destination);
        return new Vec3d(destination.x - current.x, destination.y - current.y + gravity*distance, destination.z - current.z);
    }

    /**
     * Summons a circle of entities
     * @param type EntityType
     * @param world Current world
     * @param user Entity using spell
     * @param radius Radius of the circle from the center
     * @param entity_count Total amount of entities in the circumference
     * @param search_for_entities Enables searching for entities within the circle
     * @return Returns any LivingEntity inside this circle
     */
    public static List<LivingEntity> summonEntityInCircle(EntityType type, World world, ServerPlayerEntity user, int radius, int entity_count, boolean search_for_entities) {
        double circle = Math.PI * 2;
        double step = circle / (360/entity_count);
        for(double value = 0D; value < circle; value += step) {
            double x = Math.fma(Math.sin(value), radius, user.getX());
            double z = Math.fma(Math.cos(value), radius, user.getZ());
            world.spawnEntity(type.spawnFromItemStack((ServerWorld) world, ItemStack.EMPTY, user, new BlockPos(x, user.getY(), z), SpawnReason.TRIGGERED, true, false));
        }
        if (search_for_entities) {
            Box area = new Box(user.getBlockPos().add(-radius, -radius, -radius), user.getBlockPos().add(radius, radius, radius));
            List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, area);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i).distanceTo(user) > radius || entities.get(i) == user) {
                    entities.remove(i);
                }
            }
            return entities;
        }
        return null;
    }
}
