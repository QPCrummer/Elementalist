package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class DimensionSplittingSlash extends Spell {
    public DimensionSplittingSlash(ServerPlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getName() {
        return "Dimension Splitting Slash";
    }

    @Override
    public int getDistance() {
        return 1;
    }
}
