package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class BloodTransplant extends Spell {
    public BloodTransplant(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Blood Transplant";
    }

    @Override
    public int getDistance() {
        return 10;
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.67D, 0);
        ParticleEffect chunky = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState());
        ParticleEffect thin = new DustParticleEffect(new Vec3f(0.709F, 0.0F, 0.0F), 1.0F);
        ParticleUtils.line(player.getWorld(), thin, start, targetEntity.getPos(), 0.25D, 0);
        ParticleUtils.aroundDisc(player.getWorld(), chunky, targetEntity.getPos(), 1.0D, 40, 0);
    }
}
