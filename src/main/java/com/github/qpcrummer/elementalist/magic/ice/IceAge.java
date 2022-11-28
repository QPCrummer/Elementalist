package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IceAge extends Spell {
    public IceAge(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getName() {
        return "Ice Age";
    }

    @Override
    public int getDistance() {
        return 7;
    }
}
