package com.github.qpcrummer.elementalist.magic.blood;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class HeartPurloin extends Spell {
    public HeartPurloin(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getName() {
        return "Heart Purloin";
    }

    @Override
    public int getDistance() {
        return 5;
    }
}
