package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.spell_types.DelayedRangedSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class DivineRetribution extends DelayedRangedSpell {

    public DivineRetribution(ServerPlayerEntity player, World world) {
        super(player, world, "Divine Retribution", 800, 100, 0.65F, 100, 0.0F, 0.0F);
        speed = 0.85F;
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, targetEntity.getPos(), 0.25D, 20, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 1.0D, 40, 0);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity entity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, entity.getPos(), 0, 1, 0);
        ParticleUtils.insideSphere(player.getWorld(), dust, entity.getPos(), 0.34D, 10, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, entity.getPos(), 1.0D, 20, 0);
    }

    @Override
    public void spawnBlockImpactParticle(BlockHitResult result) {
        spawnImpactParticle(result);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        spawnImpactParticle(result);
    }

    @Override
    public void waitingTasks() {
        //Enlarge
    }

    private void spawnImpactParticle(HitResult result) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, result.getPos(), 25, 0.20D);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.EXPLOSION, result.getPos(), 2.0D, 10, 0.0D);
    }
}
