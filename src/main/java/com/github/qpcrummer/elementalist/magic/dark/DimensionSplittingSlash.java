package com.github.qpcrummer.elementalist.magic.dark;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class DimensionSplittingSlash extends Spell {
    public DimensionSplittingSlash(ServerPlayerEntity player, World world) {
        super(player, world, "Dimension Splitting Slash", 400, 1);
    }
}
