package com.github.qpcrummer.elementalist.magic.poison;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlaguedSufferance extends Spell {
    public PlaguedSufferance(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Plagued Sufferance";
    }

    @Override
    public int getDistance() {
        return 10;
    }
}
