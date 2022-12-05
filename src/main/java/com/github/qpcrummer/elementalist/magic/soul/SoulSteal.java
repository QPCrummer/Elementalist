package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class SoulSteal extends Spell {
    public SoulSteal(ServerPlayerEntity player, World world) {
        super(player, world, "Soul Steal", 200, 30);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideColumn(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, player.getPos(), 0.5D, 1.0D, 15, 0.15D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        if(targetEntity.age % 4 == 0) {
            // calculate start and end
            Vec3d start = player.getPos().add(0, player.getHeight() * 0.5D, 0);
            Vec3d end = targetEntity.getPos();
            ParticleUtils.line(player.getWorld(), new DustParticleEffect(new Vec3f(0, 0, 0), 2.0F), start, end, 0.25D, 0.05D);
            ParticleUtils.line(player.getWorld(), ParticleTypes.SCULK_CHARGE_POP, start, end, 0.5D, 0);
        }
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, targetEntity.getPos(), 0.2D, 1, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.SCULK_SOUL, targetEntity.getPos(), 0.25D, 1, 0);
    }
}
