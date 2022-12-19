package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class HolySwordBarrage extends StaticSpell {
    public HolySwordBarrage(ServerPlayerEntity player, World world) {
        super(player, world, "Holy Sword Barrage", 200);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 10, 0.18D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity targetEntity) {
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 0.25D, 2, 0);
        ParticleUtils.insideCube(player.getWorld(), ParticleTypes.INSTANT_EFFECT, targetEntity.getPos(), 0, 8, 0);
    }

    @Override
    public void spawnEntityImpactParticle(EntityHitResult result) {
        ParticleUtils.toCenter(player.getWorld(), ParticleTypes.END_ROD, result.getPos(), 1.0D, 20, 0.18D);
        ParticleUtils.aroundEntity(player.getWorld(), new DustParticleEffect(new Vec3f(1.0F, 1.0F, 0.35F), 1.0F), result.getEntity(), 20, 0);
    }

    @Override
    public void immediateActivationTask() {
        super.immediateActivationTask();
    }
}
