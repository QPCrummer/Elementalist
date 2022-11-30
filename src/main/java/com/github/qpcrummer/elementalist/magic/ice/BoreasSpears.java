package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class BoreasSpears extends Spell {
    public BoreasSpears(ServerPlayerEntity player, World world) {
        super(player, world, "Boreas' Spears", 300, 100);
    }
}
