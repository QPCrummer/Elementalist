package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import com.github.qpcrummer.elementalist.util.ParticleUtils;
import com.github.qpcrummer.elementalist.util.SpecialAttacks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.List;

public class StormyWeb extends StaticSpell {

    public StormyWeb(ServerPlayerEntity player, World world) {
        super(player, world, "Stormy Web", 400);
    }

    @Override
    public void spawnCastingParticles(Entity targetEntity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.fromCenter(player.getWorld(), dust, targetEntity.getPos(), 10, 0.02D);
        ParticleUtils.fromCenter(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, targetEntity.getPos(), 20, 0.12D);
    }

    @Override
    public void spawnTrailParticle(PersistentProjectileEntity entity) {
        ParticleEffect dust = new DustParticleEffect(new Vec3f(0.0F, 0.976F, 0.961F), 1.0F);
        ParticleUtils.insideDisc(player.getWorld(), dust, entity.getPos(), 4.0D, 22, 0);
        ParticleUtils.insideDisc(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, entity.getPos(), 4.0D, 40, 0);
    }

    @Override
    public void immediateActivationTask() {
        List<LivingEntity> entities = SpecialAttacks.summonEntityInCircle(EntityType.LIGHTNING_BOLT, world, player, 4, 4, true);
        if (entities != null) {
            for (LivingEntity entity : entities) {
                SpecialAttacks.stun(entity, 2, 10.0f);
            }
        }
        super.immediateActivationTask();
    }
}
