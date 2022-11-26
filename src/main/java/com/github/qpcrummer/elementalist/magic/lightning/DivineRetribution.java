package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class DivineRetribution extends Spell {

    public DivineRetribution(PlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getName() {
        return "Divine Retribution";
    }
}
