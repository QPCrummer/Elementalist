package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SinisterDestruction extends Spell {
    public SinisterDestruction(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getName() {
        return "Sinister Destruction";
    }

    @Override
    public int getDistance() {
        return 8;
    }
}
