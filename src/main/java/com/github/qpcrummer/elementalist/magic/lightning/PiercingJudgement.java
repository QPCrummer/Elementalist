package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import com.github.qpcrummer.elementalist.util.SpecialAttacks;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class PiercingJudgement extends Spell {

    public PiercingJudgement(ServerPlayerEntity player, World world) {
        super(player, world, "Piercing Judgement", 200, 10);
        speed = 0.85F;
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), dust, targetEntity.getPos(), 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        ParticleUtils.line(player.getWorld(), dust, start, targetEntity.getPos(), 0.125D, 0.0D);
        ParticleUtils.line(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, start, targetEntity.getPos(), 0.2D, 0.05D);
    }

    @Override
    public boolean onHitEntity(EntityHitResult result) {
        SpecialAttacks.stun(result, SpecialAttacks.sec2Ticks(4), 3.0f);
        return super.onHitEntity(result);
    }
}
