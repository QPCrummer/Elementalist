package com.github.qpcrummer.elementalist.magic.time;

import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class TimeWarp extends StaticSpell {
    public TimeWarp(ServerPlayerEntity player, World world) {
        super(player, world, "Time Warp", 600);
        noclip = true;
    }

    //This allows the player to noclip through walls for 5 seconds, while also freezing players in a 10X10 sphere for that period

}
