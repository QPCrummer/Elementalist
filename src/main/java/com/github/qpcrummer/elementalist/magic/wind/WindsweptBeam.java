package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WindsweptBeam extends Spell {
    public WindsweptBeam(ServerPlayerEntity player, World world) {
        super(player, world, "Windswept Beam", 300, 8);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 15, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.CLOUD, targetEntity.getPos(), 10, 0.11D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        // air slashes
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        if(targetEntity.age % 4 == 0) {
            ParticleUtils.line(player.getWorld(), ParticleTypes.CLOUD, start, ParticleUtils.project(start, targetEntity.getVelocity(), getDistance()), 0.25D, 0.01D);
        }
        Vec3d motion = targetEntity.getVelocity().normalize();
        player.getWorld().spawnParticles(ParticleTypes.SWEEP_ATTACK, targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.05D);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, result.getPos(), 40, 0.18D);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.CLOUD, result.getEntity(), 20, 0.02D);
    }
}
