package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.spell_types.DelayedRangedSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class ApollosLyre extends DelayedRangedSpell {
    public ApollosLyre(ServerPlayerEntity player, World world) {
        super(player, world, "Apollo's Lyre", 600, 20, 0.65F, 60, 0.0F, 0.0F);
        // TODO fix speed when noclip is on
        // noclip = true;
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 20, 0.15D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 16, 0.1D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 1.0D, 12, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 0.5D, 6, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, targetEntity.getPos(), 0.5D, 2, 0);
        ParticleUtils.aroundDisc(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), targetEntity.getPos(), 1.5D, 40, 0);
    }

    @Override
    public void spawnBlockImpactParticle(BlockHitResult result) {
        ParticleUtils.insideCube(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), result.getPos(), 0.5D, 25, 0);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.END_ROD, result.getPos(), 25, 0.26F);
    }

    @Override
    public void waitingTasks() {
        //Enlarge
    }
}
