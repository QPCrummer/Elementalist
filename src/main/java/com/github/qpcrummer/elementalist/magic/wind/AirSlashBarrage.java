package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AirSlashBarrage extends Spell {
    public AirSlashBarrage(ServerPlayerEntity player, World world) {
        super(player, world, "Air Slash Barrage", 300, 2);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity entity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, entity.getPos(), 15, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.CLOUD, entity.getPos(), 10, 0.11D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        // air slashes
        Vec3d pos = targetEntity.getPos();
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.CLOUD, pos, 0.2D, 1, 0.01D);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, pos, 0.4D, 1, 0.01D);
        Vec3d motion = targetEntity.getVelocity().normalize();
        player.getWorld().spawnParticles(ParticleTypes.SWEEP_ATTACK, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.05D);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.CLOUD, result.getEntity(), 18, 0.02D);
    }
}
