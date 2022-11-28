package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class WindsweptBeam extends Spell {
    public WindsweptBeam(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Windswept Beam";
    }

    @Override
    public int getDistance() {
        return 8;
    }
}
