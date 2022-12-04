package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class IceGiantsWall extends Spell {
    public IceGiantsWall(ServerPlayerEntity player, World world) {
        super(player, world, "Ice Giant's Wall", 300, 5);
    }

    @Override
    public void onCastSpell(PersistentProjectileEntity targetEntity) {
        super.onCastSpell(targetEntity);
        Vec3d pos = ParticleUtils.project(player.getEyePos(), player.getRotationVector(), getDistance() * 0.5D);
        targetEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
        // stop the projectile from moving
        targetEntity.setVelocity(0, 0, 0);
        // TODO qpc: find a way to despawn the spell after a certain amount of time even though it does not move
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
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.SNOWFLAKE, targetEntity.getPos(), 2.5D, 10, 0.06D);
        // wall particles
        final ParticleEffect largeDust = new DustParticleEffect(new Vec3f(0.809F, 1.0F, 1.0F), 6.0F);
        Vec3d pos;
        for(int i = 0; i < 45; i++) {
            pos = targetEntity.getPos().add(
                    ((player.getRandom().nextDouble() - 0.5D) * 2.0D) * 5.0D,
                    (player.getRandom().nextDouble()) * 7.0D - 2.0D,
                    ((player.getRandom().nextDouble() - 0.5D) * 2.0D) * 5.0D
            );
            for(int j = 0, n = player.getWorld().getPlayers().size(); j < n; ++j) {
                ServerPlayerEntity serverPlayerEntity = player.getWorld().getPlayers().get(j);
                ParticleS2CPacket packet;
                if(serverPlayerEntity == player) {
                    packet = new ParticleS2CPacket(ParticleTypes.AMBIENT_ENTITY_EFFECT, true, pos.getX(), pos.getY(), pos.getZ(), 0.709F, 1.0F, 1.0F, 1.0F, 0);
                } else {
                    packet = new ParticleS2CPacket(largeDust, true, pos.getX(), pos.getY(), pos.getZ(), 0.709F, 1.0F, 1.0F, 1.0F, 0);
                }
                player.getWorld().sendToPlayerIfNearby(serverPlayerEntity, true, pos.getX(), pos.getY(), pos.getZ(), packet);
            }
        }
    }
}
