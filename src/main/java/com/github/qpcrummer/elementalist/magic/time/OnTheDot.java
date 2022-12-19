package com.github.qpcrummer.elementalist.magic.time;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class OnTheDot extends Spell {
    public OnTheDot(ServerPlayerEntity player, World world) {
        super(player, world, "On The Dot", 200, 100, 0.65F, 0.0F, 0.0F);
    }

    //This move allows the player to teleport to where they're looking, arriving "on the dot"

}
