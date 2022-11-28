package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IceGiantsWall extends Spell {
    public IceGiantsWall(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 300;
    }

    @Override
    public String getName() {
        return "Ice Giant's Wall";
    }

    @Override
    public int getDistance() {
        return 5;
    }
}
