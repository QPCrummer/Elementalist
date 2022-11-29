package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class ApollosLyre extends Spell {
    public ApollosLyre(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Apollo's Lyre";
    }

    @Override
    public int getDistance() {
        return 20;
    }

    @Override
    public boolean noClip(boolean noclip) {
        return true;
    }

    @Override
    public void spawnCastingParticles(Vec3d position) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, position, 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, position, 16, 0.1D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 1.0D, 12, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 0.5D, 6, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, targetEntity.getPos(), 0.5D, 2, 0);
        ParticleUtils.insideCube(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), targetEntity.getPos(), 0.5D, 5, 0);
    }

    @Override
    public void spawnBlockImpactParticle(BlockHitResult result) {
        ParticleUtils.insideCube(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), result.getPos(), 0.5D, 25, 0);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.END_ROD, result.getPos(), 25, 0.26F);
    }
}
