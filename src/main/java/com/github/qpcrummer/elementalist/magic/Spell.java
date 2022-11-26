package com.github.qpcrummer.elementalist.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Spell {
    protected final PlayerEntity player;
    protected final World world;
    protected int cooldown;
    protected final String name = "";

    public Spell(PlayerEntity player, World world) {
        this.player = player;
        this.world = world;
    }

    public void castProjectile(float roll, float speed, float divergence) {
        setVelocity(roll, speed, divergence);
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setVelocity(float roll, float speed, float divergence) {

    }

    public String getName() {
        return name;
    }
}
