package com.github.qpcrummer.elementalist.magic.poison;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PlaguedSufferance extends Spell {
    public PlaguedSufferance(ServerPlayerEntity player, World world) {
        super(player, world, "Plagued Sufferance", 300, 10);
        entity_count = 4;
        accumulating_offsets = true;
        pitch_offset = 90;
    }

    @Override
    public void spawnCastingParticles(PersistentProjectileEntity targetEntity) {
        //final ParticleEffect dust = new DustParticleEffect(new Vec3f(0, 0.35F, 0), 1.0F);
        final ParticleEffect dust = new DustParticleEffect(new Vec3f(0, 0.35F, 0), 2.0F);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SNEEZE, targetEntity.getPos(), 10, 0.1D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.SQUID_INK, targetEntity.getPos(), 8, 0.12D);
        // calculate start and end
        Vec3d start = player.getPos().add(0, player.getHeight() * 0.5D, 0);
        Vec3d end = ParticleUtils.project(start, targetEntity.getRotationVector(), getDistance());
        ParticleUtils.line(player.getWorld(), dust, start, end, 0.0625D, 0.01D);
        ParticleUtils.line(player.getWorld(), ParticleTypes.SNEEZE, start, end, 0.15D, 0.01D);
        ParticleUtils.line(player.getWorld(), ParticleTypes.INSTANT_EFFECT, start, end, 0.5D, 0);
        /*Vec3d difference = end.subtract(start).multiply(1.0D, 0.0D, 1.0D);
        // create list of targets
        List<Vec3d> targets = new ArrayList<>();
        targets.add(start.add(difference.getX(), difference.getY(), difference.getZ()));
        targets.add(start.add(-difference.getX(), difference.getY(), -difference.getZ()));
        targets.add(start.add(-difference.getZ(), difference.getY(), difference.getX()));
        targets.add(start.add(difference.getZ(), difference.getY(), -difference.getX()));
        // create particle lines
        for(Vec3d vec : targets) {
            ParticleUtils.line(player.getWorld(), dust, start, vec, 0.0625D, 0.01D);
            ParticleUtils.line(player.getWorld(), ParticleTypes.SNEEZE, start, vec, 0.15D, 0.01D);
            ParticleUtils.line(player.getWorld(), ParticleTypes.INSTANT_EFFECT, start, vec, 0.5D, 0);
        }*/
    }
}
