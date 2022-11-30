package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IceAge extends Spell {
    public IceAge(ServerPlayerEntity player, World world) {
        super(player, world, "Ice Age", 800, 7);
    }
}
