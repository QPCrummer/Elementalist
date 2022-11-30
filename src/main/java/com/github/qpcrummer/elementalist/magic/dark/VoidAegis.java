package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class VoidAegis extends Spell {
    public VoidAegis(ServerPlayerEntity player, World world) {
        super(player, world, "Void Aegis", 300, 0);
    }
}
