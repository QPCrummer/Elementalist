package com.github.qpcrummer.elementalist.magic.wind;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class AirSlashBarrage extends Spell {
    public AirSlashBarrage(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Air Slash Barrage";
    }

    @Override
    public int getDistance() {
        return 2;
    }
}
