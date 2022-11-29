package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PiercingJudgement extends Spell {

    public PiercingJudgement(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Piercing Judgement";
    }

    @Override
    public int getDistance() {
        return 10;
    }

    @Override
    public void spawnCastingParticles(Vec3d position) {
        Vec3d center = new Vec3d(player.getX(), player.getBodyY(0.67D), player.getZ());
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, center, 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, center, 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        ParticleUtils.line(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, start, targetEntity.getPos(), 0.125D, 0.0D);
        ParticleUtils.line(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, start, targetEntity.getPos(), 0.2D, 0.05D);
    }
}
