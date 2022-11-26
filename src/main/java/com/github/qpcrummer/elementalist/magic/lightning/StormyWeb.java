package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class StormyWeb extends Spell {

    public StormyWeb(PlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getName() {
        return "Stormy Web";
    }
}
