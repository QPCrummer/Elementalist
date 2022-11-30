package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SoulSteal extends Spell {
    public SoulSteal(ServerPlayerEntity player, World world) {
        super(player, world, "Soul Steal", 200, 100);
    }
}
