package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class ApollosLyre extends Spell {
    public ApollosLyre(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Apollo's Lyre";
    }

    @Override
    public int getDistance() {
        return 20;
    }

    @Override
    public boolean noClip(boolean noclip) {
        return true;
    }
}
