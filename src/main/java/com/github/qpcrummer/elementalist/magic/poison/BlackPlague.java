package com.github.qpcrummer.elementalist.magic.poison;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class BlackPlague extends Spell {
    public BlackPlague(ServerPlayerEntity player, World world) {
        super(player, world, "Black Plague", 700, 40);
        //TODO Figure out the appropriate size for this attack
    }
}
