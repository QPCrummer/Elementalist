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

public class BloodRain extends StaticSpell {
    public BloodRain(ServerPlayerEntity player, World world) {
        super(player, world, "Blood Rain", 800);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.insideColumn(player.getWorld(), chunky, player.getPos(), 0.5D, 6.0D, 40, 0.30D);
        ParticleUtils.insideColumn(player.getWorld(), thin, player.getPos(), 0.5D, 6.0D, 60, 0.35D);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        Vec3d pos = new Vec3d(result.getEntity().getX(), result.getEntity().getBodyY(0.67D), result.getEntity().getZ());
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), chunky, pos, 30, 0.15D);
        ParticleUtils.insideSphere(player.getWorld(), thin, pos, result.getEntity().getWidth() * 0.65D, 40, 0.08D);
    }

    @Override
    public void immediateActivationTask() {
        super.immediateActivationTask();
    }
}
