package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class BoreasSpears extends Spell {
    public BoreasSpears(ServerPlayerEntity player, World world) {
        super(player, world, "Boreas' Spears", 300, 100, 0.65F, 0.0F, 0.0F);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.709F, 1.0F, 1.0F), 1.0F);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.SNOWFLAKE, targetEntity, 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), dust, targetEntity.getPos(), 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 8, 0.1D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0.709F, 1.0F, 1.0F), 1.25F);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.SNOWFLAKE, targetEntity.getPos(), 1.0D, 12, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 0.5D, 6, 0);
        ParticleUtils.aroundDisc(player.getWorld(), dust, targetEntity.getPos(), 1.5D, 40, 0);
    }
}
