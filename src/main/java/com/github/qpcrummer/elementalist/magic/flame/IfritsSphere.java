package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IfritsSphere extends Spell {
    public IfritsSphere(ServerPlayerEntity player, World world) {
        super(player, world, "Ifrits Sphere", 200, 8);
    }
}
