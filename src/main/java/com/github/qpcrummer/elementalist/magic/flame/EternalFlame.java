package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class EternalFlame extends Spell {
    public EternalFlame(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Eternal Flame";
    }

    @Override
    public int getDistance() {
        return 8;
    }
}
