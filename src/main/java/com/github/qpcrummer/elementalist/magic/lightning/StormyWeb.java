package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class StormyWeb extends Spell {

    public StormyWeb(ServerPlayerEntity player, World world) {
        super(player, world, "Stormy Web", 400, 4);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), dust, targetEntity.getPos(), 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity entity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.insideDisc(player.getWorld(), dust, entity.getPos(), 4.0D, 22, 0);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, entity.getPos(), 4.0D, 40, 0);
    }
}
