package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.player.PlayerEntity;
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
    public void spawnCastingParticles() {
        Vec3d center = new Vec3d(player.getX(), player.getBodyY(0.67D), player.getZ());
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, center, 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, center, 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(Vec3d position) {
        Vec3d start = player.getPos();
        ParticleUtils.line(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, start, position, 0.125D, 0.0D);
        ParticleUtils.line(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, start, position, 0.2D, 0.05D);
    }
}
