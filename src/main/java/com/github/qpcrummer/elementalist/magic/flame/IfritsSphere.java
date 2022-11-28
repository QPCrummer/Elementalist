package com.github.qpcrummer.elementalist.magic.flame;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IfritsSphere extends Spell {
    public IfritsSphere(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Ifrits Sphere";
    }

    @Override
    public int getDistance() {
        return 8;
    }
}
