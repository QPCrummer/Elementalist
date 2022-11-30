package com.github.qpcrummer.elementalist.magic.poison;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlaguedSufferance extends Spell {
    public PlaguedSufferance(ServerPlayerEntity player, World world) {
        super(player, world, "Plagued Sufferance", 300, 10);
    }
}
