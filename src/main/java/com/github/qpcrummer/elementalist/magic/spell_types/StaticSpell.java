package com.github.qpcrummer.elementalist.magic.spell_types;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class StaticSpell extends Spell {

    public StaticSpell(ServerPlayerEntity player, World world, String name, int cooldown) {
        super(player, world, name, cooldown);
    }

    @Override
    public void applyVelocity(ArrowEntity entity, float pitch_offset, float yaw_offset) {
    }

    @Override
    public void tick(PersistentProjectileEntity entity) {
        immediateActivationTask();
    }

    /**
     * Immediately runs code till the tracker entity is killed
     */
    public void immediateActivationTask() {
        //nothing
    }
}
