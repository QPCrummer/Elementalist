package com.github.qpcrummer.elementalist.magic.soul;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SoulsWrath extends Spell {
    public SoulsWrath(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getName() {
        return "Soul's Wrath";
    }

    @Override
    public int getDistance() {
        return 100;
    }
}
