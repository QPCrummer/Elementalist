package com.github.qpcrummer.elementalist.magic.spell_types;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class DelayedStaticSpell extends StaticSpell{
    protected int delay;

    public DelayedStaticSpell(ServerPlayerEntity player, World world, String name, int cooldown, int delay) {
        super(player, world, name, cooldown);
        this.delay = delay;
    }

    @Override
    public void tick(PersistentProjectileEntity entity) {
        if (waitFor()) {
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
