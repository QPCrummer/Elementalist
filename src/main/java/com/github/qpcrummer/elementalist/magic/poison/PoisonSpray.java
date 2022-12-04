package com.github.qpcrummer.elementalist.magic.poison;

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

public class PoisonSpray extends Spell {
    public PoisonSpray(ServerPlayerEntity player, World world) {
        super(player, world, "Poison Spray", 300, 7);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SNEEZE, targetEntity.getPos(), 10, 0.1D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 8, 0.08D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0, 0.35F, 0), 1.0F);
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        Vec3d motion = targetEntity.getVelocity().normalize();
        Vec3d pos;
        for(int i = 0; i < 20; i++) {
            pos = start.add(
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 2.5D,
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 1.0D,
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 2.5D
            );
            player.getWorld().spawnParticles(ParticleTypes.SQUID_INK, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.7F);
            player.getWorld().spawnParticles(ParticleTypes.SNEEZE, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.4F);
        }
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 2.5D, 20, 0.02D);
    }
}
