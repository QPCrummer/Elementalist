package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class VoidAegis extends Spell {
    public VoidAegis(ServerPlayerEntity player, World world) {
        super(player, world, "Void Aegis", 300, 0);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 1.0F);
        ParticleUtils.aroundEntity(player.getWorld(), dust, player, 20, 0.04D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, targetEntity.getPos(), 15, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 4.0F);
        aroundSphere(player, ParticleTypes.AMBIENT_ENTITY_EFFECT, dust, targetEntity.getPos(), 2.0D, 100, 0);
    }

    /**
     * Spawns the given number of particles at random positions around a sphere,
     * with the option of different particles for a single player
     * @param owner the owner player
     * @param ownerParticle the particle to display for the owner
     * @param particle the particle
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @param count the number of particles
     * @param motion the maximum particle motion
     */
    private static void aroundSphere(final ServerPlayerEntity owner, final ParticleEffect ownerParticle,
                                     final ParticleEffect particle, final Vec3d center,
                                    final double radius, final int count, final double motion) {
        double x, y, z;
        Vec3d posVec;
        for(int i = 0; i < count; i++) {
            x = ((owner.getRandom().nextDouble() - 0.5D) * 2.0D);
            y = ((owner.getRandom().nextDouble() - 0.5D) * 2.0D);
            z = ((owner.getRandom().nextDouble() - 0.5D) * 2.0D);
            posVec = center.add(new Vec3d(x, y, z).normalize().multiply(radius));

            for(int j = 0, n = owner.getWorld().getPlayers().size(); j < n; ++j) {
                ServerPlayerEntity serverPlayerEntity = owner.getWorld().getPlayers().get(j);
                ParticleS2CPacket packet;
                if(serverPlayerEntity == owner) {
                    packet = new ParticleS2CPacket(ownerParticle, true, posVec.getX(), posVec.getY(), posVec.getZ(), 0, 0, 0, (float) motion, 1);
                } else {
                    packet = new ParticleS2CPacket(particle, true, posVec.getX(), posVec.getY(), posVec.getZ(), 0, 0, 0, (float) motion, 1);
                }
                owner.getWorld().sendToPlayerIfNearby(serverPlayerEntity, true, posVec.getX(), posVec.getY(), posVec.getZ(), packet);
            }
        }
    }
}
