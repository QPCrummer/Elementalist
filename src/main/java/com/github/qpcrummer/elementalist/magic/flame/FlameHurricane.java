package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlameHurricane extends StaticSpell {
    public FlameHurricane(ServerPlayerEntity player, World world) {
        super(player, world, "Flame Hurricane", 400);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.LAVA, player, 20, 0.14D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SMALL_FLAME, targetEntity.getPos(), 10, 0.20D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d pos = targetEntity.getPos().subtract(0, 1.25D, 0);
        ParticleUtils.spiral(player.getWorld(), ParticleTypes.FLAME, pos, 4.0D, 0.5D, 180, 26, 0D);
        ParticleUtils.spiral(player.getWorld(), ParticleTypes.SMOKE, pos, 4.0D, 0.5D, 180, 4, 0.04D);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.LAVA, player, 1, 0.14D);
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.SMOKE, player, 1, 0.14D);
    }

    @Override
    public void immediateActivationTask() {
        super.immediateActivationTask();
    }
}
