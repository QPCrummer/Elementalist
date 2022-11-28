package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class VoidAegis extends Spell {
    public VoidAegis(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Void Aegis";
    }

    @Override
    public int getDistance() {
        return 0;
    }
}
