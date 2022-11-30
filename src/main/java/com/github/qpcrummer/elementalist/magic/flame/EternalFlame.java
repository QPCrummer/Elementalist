package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class EternalFlame extends Spell {
    public EternalFlame(ServerPlayerEntity player, World world) {
        super(player, world, "Eternal Flame", 600, 8);
    }
}
