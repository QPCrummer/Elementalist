package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class DimensionSplittingSlash extends Spell {
    public DimensionSplittingSlash(ServerPlayerEntity player, World world) {
        super(player, world, "Dimension Splitting Slash", 400, 1);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 1.0F);
        ParticleUtils.aroundEntity(player.getWorld(), dust, player, 20, 0.04D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, targetEntity.getPos(), 15, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.235F, 0.0F, 0.309F), 1.0F);
        ParticleUtils.insideColumn(player.getWorld(), dust, targetEntity.getPos().subtract(0, 1.0D, 0), 0.5D, 10.0D, 180, 0.22D);
    }
}
