package com.github.qpcrummer.elementalist.util;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

/**
 * @author skyjay1
 * @since ${version}
 **/
public final class ParticleUtils {

    /**
     * Calculates the end position of the entity look angle
     * @param start the start position
     * @param rotationVector the rotation vector
     * @param distance the distance to project
     * @return the point at the given distance in the direction of the entity rotation
     */
    public static Vec3d project(final Vec3d start, Vec3d rotationVector, final double distance) {
        return start.add(rotationVector.multiply(distance));
    }

    /**
     * Spawns the given number of particles at random positions inside a cube
     * @param world the world
     * @param particle the particle
     * @param start the center position
     * @param deltaPos the maximum distance on each axis from the center position
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void insideCube(final ServerWorld world, final ParticleEffect particle, final Vec3d start,
                                 final double deltaPos, final int count, final double motion) {
        world.spawnParticles(particle, start.getX(), start.getY(), start.getZ(), count, deltaPos, deltaPos, deltaPos, motion);
    }

    /**
     * Spawns the given number of particles at random positions inside a sphere
     * @param world the world
     * @param particle the particle
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void insideSphere(final ServerWorld world, final ParticleEffect particle, final Vec3d center,
                              final double radius, final int count, final double motion) {
        double x, y, z;
        Vec3d posVec;
        for(int i = 0; i < count; i++) {
            x = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            y = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            z = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            posVec = center.add(new Vec3d(x, y, z).normalize().multiply(radius * Math.cbrt(world.getRandom().nextDouble())));
            world.spawnParticles(particle, posVec.getX(), posVec.getY(), posVec.getZ(), 1, 0, 0, 0, motion);
        }
    }

    /**
     * Spawns particles that move away from a central point
     * @param world the world
     * @param particle the particle
     * @param center the center point
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void fromCenter(final ServerWorld world, final ParticleEffect particle, final Vec3d center,
                                      final int count, final double motion) {
        double x, y, z;
        Vec3d motionVec;
        for(int i = 0; i < count; i++) {
            // calculate random point in sphere
            x = center.getX() + (world.getRandom().nextDouble() - 0.5D) * 2.0D;
            y = center.getY() + (world.getRandom().nextDouble() - 0.5D) * 2.0D;
            z = center.getZ() + (world.getRandom().nextDouble() - 0.5D) * 2.0D;
            motionVec = new Vec3d(x, y, z).subtract(center).normalize();
            world.spawnParticles(particle, center.getX(), center.getY(), center.getZ(),0, motionVec.getX(), motionVec.getY(), motionVec.getZ(), motion);
        }
    }

    /**
     * Spawns particles that move toward a central point
     * @param world the world
     * @param particle the particle
     * @param center the center point
     * @param radius the starting distance from the center
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void toCenter(final ServerWorld world, final ParticleEffect particle, final Vec3d center,
                                      final double radius, final int count, final double motion) {
        double x, y, z;
        Vec3d posVec;
        Vec3d motionVec;
        for(int i = 0; i < count; i++) {
            // calculate random point in sphere
            x = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            y = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            z = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            motionVec = new Vec3d(x, y, z).normalize();
            posVec = center.subtract(motionVec.multiply(radius));
            world.spawnParticles(particle, posVec.getX(), posVec.getY(), posVec.getZ(),0, motionVec.getX(), motionVec.getY(), motionVec.getZ(), motion);
        }
    }

    /**
     * Spawns the given number of particles at random distances from the given position within a disc
     * @param world the world
     * @param particle the particle
     * @param center the center of the disc
     * @param radius the radius of the disc
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void insideDisc(final ServerWorld world, final ParticleEffect particle, final Vec3d center,
                            final double radius, final int count, final double motion) {
        // DEBUG
        System.out.println("inside disc around " + center.toString());
        double x, z;
        Vec3d posVec;
        for(int i = 0; i < count; i++) {
            x = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            z = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            posVec = center.add(new Vec3d(x, 0, z).normalize().multiply(radius * Math.cbrt(world.getRandom().nextDouble())));
            world.spawnParticles(particle, posVec.getX(), posVec.getY(), posVec.getZ(), 1, 0, 0, 0, motion);
        }
    }

    /**
     * Spawns the given number of particles at a set distance from the given position within a disc
     * @param world the world
     * @param particle the particle
     * @param center the center of the disc
     * @param radius the radius of the disc
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void aroundDisc(final ServerWorld world, final ParticleEffect particle, final Vec3d center,
                                  final double radius, final int count, final double motion) {
        double x, z;
        Vec3d posVec;
        for(int i = 0; i < count; i++) {
            x = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            z = ((world.getRandom().nextDouble() - 0.5D) * 2.0D);
            posVec = center.add(new Vec3d(x, 0, z).normalize().multiply(radius));
            world.spawnParticles(particle, posVec.getX(), posVec.getY(), posVec.getZ(), 1, 0, 0, 0, motion);
        }
    }

    /**
     * Spawns a line of particles between the given positions
     * @param world the world
     * @param particle the particle
     * @param start the start position
     * @param end the end position
     * @param stepSize the distance between particles
     * @param motion the maximum particle motion
     */
    public static void line(final ServerWorld world, final ParticleEffect particle, final Vec3d start, final Vec3d end,
                           final double stepSize, final double motion) {
        final Vec3d difference = end.subtract(start);
        final double length = difference.length();
        Vec3d pos;
        for (double delta = 0; delta < length; delta += stepSize) {
            pos = start.lerp(end, delta);
            world.spawnParticles(particle, pos.getX(), pos.getY(), pos.getZ(), 1, 0, 0, 0, motion);
        }
    }

    /**
     * Spawns particles within an entity bounding box
     * @param world the world
     * @param particle the particle
     * @param entity the entity
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    public static void aroundEntity(final ServerWorld world, final ParticleEffect particle, final Entity entity,
                                      final int count, final double motion) {
        double halfHeight = entity.getHeight() * 0.5D;
        world.spawnParticles(particle, entity.getX(), entity.getY() + halfHeight, entity.getZ(), count, entity.getWidth(), halfHeight, entity.getWidth(), motion);
    }
}
