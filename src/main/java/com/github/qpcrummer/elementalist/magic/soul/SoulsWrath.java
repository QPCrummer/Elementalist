package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SoulsWrath extends Spell {
    public SoulsWrath(ServerPlayerEntity player, World world) {
        super(player, world, "Soul's Wrath", 600, 100);
    }
}
