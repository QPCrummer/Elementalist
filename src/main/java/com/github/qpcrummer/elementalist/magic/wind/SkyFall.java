package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SkyFall extends Spell {
    public SkyFall(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Sky Fall";
    }

    @Override
    public int getDistance() {
        return 16;
    }
}
