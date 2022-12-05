package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EternalFlame extends Spell {
    public EternalFlame(ServerPlayerEntity player, World world) {
        super(player, world, "Eternal Flame", 600, 8);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.LAVA, player, 20, 0.14D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SMALL_FLAME, targetEntity.getPos(), 10, 0.20D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        Vec3d motion = targetEntity.getVelocity().normalize();
        Vec3d pos;
        for(int i = 0; i < 60; i++) {
            pos = start.add(
                (player.getRandom().nextDouble() - 0.5D) * 2.0D * 4.0D,
                (player.getRandom().nextDouble() - 0.5D) * 2.0D * 2.0D,
                (player.getRandom().nextDouble() - 0.5D) * 2.0D * 4.0D
            );
            player.getWorld().spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), getSpeed());
        }
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.SMOKE, targetEntity.getPos(), 4.0D, 20, 0.02D);
    }
}
