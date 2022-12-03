package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class IfritsSphere extends Spell {
    public IfritsSphere(ServerPlayerEntity player, World world) {
        super(player, world, "Ifrits Sphere", 200, 8);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity entity) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.LAVA, player, 20, 0.14D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SMALL_FLAME, entity.getPos(), 10, 0.20D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity entity) {
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.FLAME, entity.getPos(), 0.5D, 10, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.SMALL_FLAME, entity.getPos(), 0.5D, 20, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.SMOKE, entity.getPos(), 0.25D, 1, 0.1D);
    }
}
