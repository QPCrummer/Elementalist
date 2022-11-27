package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DivineRetribution extends Spell {

    public DivineRetribution(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getName() {
        return "Divine Retribution";
    }

    @Override
    public void spawnCastingParticles() {
        Vec3d center = new Vec3d(player.getX(), player.getBodyY(0.67D), player.getZ());
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, center, 0.25D, 20, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, center, 1.0D, 40, 0);
    }

    @Override
    public void spawnTrailParticle(Vec3d position) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.END_ROD, position, 0, 1, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, position, 0.25D, 10, 0);
        ParticleUtils.insideSphere(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, position, 1.0D, 20, 0);
    }

    @Override
    public void spawnBlockImpactParticle(BlockHitResult result) {
        super.spawnBlockImpactParticle(result);
    }
}
