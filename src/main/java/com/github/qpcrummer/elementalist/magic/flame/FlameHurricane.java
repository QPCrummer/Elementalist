package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class FlameHurricane extends Spell {
    public FlameHurricane(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getName() {
        return "Flame Hurricane";
    }

    @Override
    public int getDistance() {
        return 0;
    }
}
