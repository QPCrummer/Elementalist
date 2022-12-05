package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class SoulsPain extends Spell {
    public SoulsPain(ServerPlayerEntity player, World world) {
        super(player, world, "Soul's Pain", 300, 2);
    }

    @Override
    public void onCastSpell(PersistentProjectileEntity targetEntity) {
        Vec3d pos = ParticleUtils.project(player.getEyePos(), player.getRotationVector(), getDistance());
        targetEntity.refreshPositionAfterTeleport(pos);
        super.onCastSpell(targetEntity);
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideColumn(player.getWorld(), ParticleTypes.SOUL_FIRE_FLAME, player.getPos(), 0.5D, 1.0D, 15, 0.15D);
        // column particles
        Vec3d start = targetEntity.getPos().subtract(0, 2.0D, 0);
        Box bounds = new Box(-0.5D, -2.0D, -0.5D, 0.5D, 4.0D, 0.5D).offset(targetEntity.getPos());
        ParticleUtils.insideBounds(player.getWorld(), ParticleTypes.SOUL, bounds, 30, 0.01D);
        ParticleUtils.insideBounds(player.getWorld(), new DustParticleEffect(new Vec3f(0, 0, 0), 1.5F), bounds, 60, 0.01D);
        ParticleUtils.insideBounds(player.getWorld(), ParticleTypes.SCULK_SOUL, bounds, 50, 0.01D);
        ParticleUtils.insideBounds(player.getWorld(), ParticleTypes.END_ROD, bounds, 20, 0.01D);

    }
}
