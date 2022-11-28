package com.github.qpcrummer.elementalist.magic.time;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class TimeWarp extends Spell {
    public TimeWarp(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    //This allows the player to noclip through walls for 5 seconds, while also freezing players in a 10X10 sphere for that period

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Time Warp";
    }

    @Override
    public int getDistance() {
        return 10;
    }

    @Override
    public boolean noClip(boolean noclip) {
        return true;
    }
}
