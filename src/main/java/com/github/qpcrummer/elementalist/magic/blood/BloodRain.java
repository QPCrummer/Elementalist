package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class BloodRain extends Spell {
    public BloodRain(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getName() {
        return "Blood Rain";
    }

    @Override
    public int getDistance() {
        return 15;
    }
}
