package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WindsweptBeam extends Spell {
    public WindsweptBeam(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Windswept Beam";
    }

    @Override
    public int getDistance() {
        return 8;
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        // air slashes
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        ParticleUtils.line(player.getWorld(), ParticleTypes.CLOUD, start, targetEntity.getPos(), 0.125D, 0.01D);
        Vec3d motion = targetEntity.getVelocity().normalize();
        player.getWorld().spawnParticles(ParticleTypes.SWEEP_ATTACK, targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.05D);
    }
}
