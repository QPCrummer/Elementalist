package com.github.qpcrummer.elementalist.magic.ice;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class IceGiantsWall extends Spell {
    public IceGiantsWall(ServerPlayerEntity player, World world) {
        super(player, world, "Ice Giant's Wall", 300, 5);
    }
}
