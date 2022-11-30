package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SinisterDestruction extends Spell {
    public SinisterDestruction(ServerPlayerEntity player, World world) {
        super(player, world, "Sinister Destruction", 800, 8);
    }
}
