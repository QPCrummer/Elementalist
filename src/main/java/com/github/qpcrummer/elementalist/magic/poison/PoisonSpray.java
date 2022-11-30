package com.github.qpcrummer.elementalist.magic.poison;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PoisonSpray extends Spell {
    public PoisonSpray(ServerPlayerEntity player, World world) {
        super(player, world, "Poison Spray", 300, 7);
    }
}
