package com.github.qpcrummer.elementalist.magic.spell_types;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class DelayedRangedSpell extends Spell {
    protected int delay;

    public DelayedRangedSpell(ServerPlayerEntity player, World world, String name, int cooldown, int distance, float speed, int delay, float pitch_offset, float yaw_offset) {
        super(player, world, name, cooldown, distance, speed, pitch_offset, yaw_offset);
        this.delay = delay;
    }

    @Override
    public void applyVelocity(ArrowEntity entity, float pitch_offset, float yaw_offset) {
        if (delay == 0) {
            super.applyVelocity(entity, pitch_offset, yaw_offset);
        }
    }

    @Override
    public void tick(PersistentProjectileEntity entity) {
        if (waitFor()) {
            applyVelocity((ArrowEntity) entity, pitch_offset, yaw_offset);
            waitingTasks();
        } else {
            super.tick(entity);
        }
    }

    /**
     * Waits for specified number of ticks
     * @return returns if waiting is still needed
     */
    public boolean waitFor() {
        delay--;
        return delay != -1;
    }

    /**
     * Gets the delay before velocity is applied to a Target
     * @return The delay in ticks
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Runs code while the target entity is waiting
     */
    public void waitingTasks() {
        //nothing
    }
}
