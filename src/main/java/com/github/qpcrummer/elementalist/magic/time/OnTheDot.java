package com.github.qpcrummer.elementalist.magic.time;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class OnTheDot extends Spell {
    public OnTheDot(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    //This move allows the player to teleport to where they're looking, arriving "on the dot"

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "On The Dot";
    }

    @Override
    public int getDistance() {
        return 100;
    }
}
