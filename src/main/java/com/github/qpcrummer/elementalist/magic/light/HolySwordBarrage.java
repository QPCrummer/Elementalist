package com.github.qpcrummer.elementalist.magic.light;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class HolySwordBarrage extends Spell {
    public HolySwordBarrage(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Holy Sword Barrage";
    }

    @Override
    public int getDistance() {
        return 10;
    }
}
