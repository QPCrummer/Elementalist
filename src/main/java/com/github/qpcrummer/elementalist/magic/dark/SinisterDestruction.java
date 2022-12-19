package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.spell_types.DelayedRangedSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class SinisterDestruction extends DelayedRangedSpell {
    public SinisterDestruction(ServerPlayerEntity player, World world) {
        super(player, world, "Sinister Destruction", 800, 8, 0.65F, 120, 0.0F, 0.0F);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 1.0F);
        ParticleUtils.aroundEntity(player.getWorld(), dust, player, 20, 0.04D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, targetEntity.getPos(), 15, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 2.0F);
        if(targetEntity.age % 5 == 0) {
            ParticleUtils.insideCube(player.getWorld(), ParticleTypes.FLASH, targetEntity.getPos(), 0.25D, 1, 0);
        }
        ParticleUtils.insideSphere(player.getWorld(), dust, targetEntity.getPos(), 0.75D, 28, 0);
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
       //Grow larger
    }

    private void spawnImpactParticle(HitResult result) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.FLASH, result.getPos(), 0.25D, 1, 0);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, result.getPos(), 35, 0.24D);
        ParticleUtils.insideCube(player.getWorld(), new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OBSIDIAN.getDefaultState()), result.getPos(), 0.4D, 30, 0.18D);
    }
}
