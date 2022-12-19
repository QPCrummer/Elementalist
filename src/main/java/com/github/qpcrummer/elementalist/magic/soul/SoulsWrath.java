package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.spell_types.MovingSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SoulsWrath extends MovingSpell {
    public SoulsWrath(ServerPlayerEntity player, World world) {
        super(player, world, "Soul's Wrath", 600, 100, 0.65F, 0.0F, 0.0F);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.insideColumn(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, player.getPos(), 0.5D, 1.0D, 15, 0.15D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        Vec3d motion = targetEntity.getVelocity().normalize();
        Vec3d pos;
        for(int i = 0; i < 20; i++) {
            pos = start.add(
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 2.0D,
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 1.0D,
                    (player.getRandom().nextDouble() - 0.5D) * 2.0D * 2.0D
            );
            player.getWorld().spawnParticles(ParticleTypes.SCULK_CHARGE_POP, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.5F);
            player.getWorld().spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX(), pos.getY(), pos.getZ(), 0, motion.getX(), motion.getY(), motion.getZ(), 0.5F);
        }
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, targetEntity.getPos(), 1.25D, 20, 0.02D);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        ParticleUtils.aroundEntity(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, result.getEntity(), 15, 0);
    }
}
