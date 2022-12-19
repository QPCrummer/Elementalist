package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class HeartPurloin extends StaticSpell {
    public HeartPurloin(ServerPlayerEntity player, World world) {
        super(player, world, "Heart Purloin", 400);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.line(player.getWorld(), thin, targetEntity.getPos(), ParticleUtils.project(targetEntity.getPos(), targetEntity.getVelocity(), getDistance()), 0.125D, 0.02D);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        Vec3d pos = new Vec3d(result.getEntity().getX(), result.getEntity().getBodyY(0.67D), result.getEntity().getZ());
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), chunky, pos, 20, 0.12D);
        ParticleUtils.insideSphere(player.getWorld(), thin, pos, 0.5D, 30, 0.08D);
    }

    @Override
    public void immediateActivationTask() {
        super.immediateActivationTask();
    }
}
