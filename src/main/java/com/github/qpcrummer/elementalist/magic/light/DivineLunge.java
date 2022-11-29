package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class DivineLunge extends Spell {
    public DivineLunge(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Divine Lunge";
    }

    @Override
    public int getDistance() {
        return 10;
    }

    @Override
    public void spawnCastingParticles(Vec3d position) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, position, 20, 0.25D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.END_ROD, position, 10, 0.18D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, player, 5, 0);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.INSTANT_EFFECT, player, 5, 0);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        ParticleUtils.aroundEntity(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), result.getEntity(), 20, 0);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.END_ROD, result.getPos(), 40, 0.30D);
    }
}
