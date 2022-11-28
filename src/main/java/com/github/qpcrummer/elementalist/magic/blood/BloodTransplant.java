package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class BloodTransplant extends Spell {
    public BloodTransplant(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Blood Transplant";
    }

    @Override
    public int getDistance() {
        return 10;
    }
}
