package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class BloodTransplant extends Spell {
    public BloodTransplant(ServerPlayerEntity player, World world) {
        super(player, world, "Blood Transplant", 200, 10);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        if(targetEntity.age % 4 == 0) {
            ParticleUtils.line(player.getWorld(), chunky, start, targetEntity.getPos(), 0.25D, 0);
            ParticleUtils.line(player.getWorld(), thin, start, targetEntity.getPos(), 0.125D, 0);
        }
        ParticleUtils.aroundDisc(player.getWorld(), thin, targetEntity.getPos(), 1.0D, 40, 0);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        Vec3d pos = new Vec3d(result.getEntity().getX(), result.getEntity().getBodyY(0.67D), result.getEntity().getZ());
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), chunky, pos, 20, 0.12D);
        ParticleUtils.insideSphere(player.getWorld(), thin, pos, 0.5D, 30, 0.08D);
    }
}
