package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SkyFall extends Spell {
    public SkyFall(ServerPlayerEntity player, World world) {
        super(player, world, "Sky Fall", 600, 16);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity entity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, entity.getPos(), 15, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.CLOUD, entity.getPos(), 10, 0.11D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 0.25D, 1, 0.01D);
    }

    @Override
    public void spawnBlockImpactParticle(BlockHitResult result) {
        spawnImpactParticle(result);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        spawnImpactParticle(result);
    }

    private void spawnImpactParticle(HitResult result) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, result.getPos(), 40, 0.18D);
        ParticleUtils.insideColumn(player.getWorld(), ParticleTypes.CLOUD, result.getPos(), 1.0D, 8.0D, 180, -0.36D);
    }
}
