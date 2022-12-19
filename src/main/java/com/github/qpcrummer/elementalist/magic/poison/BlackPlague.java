package com.github.qpcrummer.elementalist.magic.poison;

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

public class BlackPlague extends Spell {
    public BlackPlague(ServerPlayerEntity player, World world) {
        super(player, world, "Black Plague", 700, 10, 0.65F, 0.0F, 0.0F);
        //TODO Figure out the appropriate size for this attack
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SNEEZE, targetEntity.getPos(), 10, 0.2D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SQUID_INK, targetEntity.getPos(), 8, 0.1D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0, 0.35F, 0), 1.0F);
        ParticleUtils.insideDisc(player.getWorld(), dust, targetEntity.getPos(), getDistance(), 30, 0.05);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.SQUID_INK, targetEntity.getPos(), getDistance(), 20, 0.05);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.SNEEZE, targetEntity.getPos(), getDistance(), 40, 0);
    }
}
