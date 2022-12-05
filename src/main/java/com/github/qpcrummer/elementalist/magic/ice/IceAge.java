package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class IceAge extends Spell {
    public IceAge(ServerPlayerEntity player, World world) {
        super(player, world, "Ice Age", 800, 7);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.709F, 1.0F, 1.0F), 1.0F);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.SNOWFLAKE, targetEntity, 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), dust, targetEntity.getPos(), 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 8, 0.1D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.709F, 1.0F, 1.0F), 2.0F);
        ParticleUtils.insideDisc(player.getWorld(), dust, targetEntity.getPos(), getDistance(), 20, 0.05);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.SNOWFLAKE, targetEntity.getPos(), getDistance(), 30, 0.05);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.END_ROD, targetEntity.getPos(), getDistance(), 6, 0);
    }
}
