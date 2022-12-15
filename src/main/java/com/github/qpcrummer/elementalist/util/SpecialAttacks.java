package com.github.qpcrummer.elementalist.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;

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
     * @param result The entity that was hit
     * @param time The time in ticks the stun should last
     * @param dmg The damage the entity should take
     */
    public static void stun(EntityHitResult result, int time, float dmg) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity) {
            entity.damage(DamageSource.MAGIC, dmg);
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, time, 50));
        }
    }

    public static Vec3d lunge(EntityHitResult result, Vec3d current, double height, float dmg) {
        Entity entity = result.getEntity();
        Vec3d destination = entity.getPos();
        double gravity = 1.0;
        double distance = current.distanceTo(destination);
        return new Vec3d(destination.x - current.x, destination.y - current.y + gravity*distance, destination.z - current.z);
    }
}
