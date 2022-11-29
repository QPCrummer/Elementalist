package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StormyWeb extends Spell {

    public StormyWeb(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getName() {
        return "Stormy Web";
    }

    @Override
    public int getDistance() {
        return 4;
    }

    @Override
    public void spawnCastingParticles(Vec3d position) {
        Vec3d center = new Vec3d(player.getX(), player.getBodyY(0.67D), player.getZ());
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, center, 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, center, 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity entity) {
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, entity.getPos(), 4.0D, 10, 0);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, entity.getPos(), 4.0D, 25, 0);
    }
}
