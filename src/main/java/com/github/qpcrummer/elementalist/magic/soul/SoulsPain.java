package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SoulsPain extends Spell {
    public SoulsPain(ServerPlayerEntity player, World world) {
        super(player, world, "Soul's Pain", 300, 2);
    }
}
