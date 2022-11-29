package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AirSlashBarrage extends Spell {
    public AirSlashBarrage(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Air Slash Barrage";
    }

    @Override
    public int getDistance() {
        return 2;
    }


    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        // air slashes
        Vec3d pos = targetEntity.getPos();
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.CLOUD, pos, 0.25D, 6, 0.01D);
        Vec3d motion = targetEntity.getVelocity().normalize();
        player.getWorld().spawnParticles(ParticleTypes.SWEEP_ATTACK, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.05D);
    }
}
