package com.github.qpcrummer.elementalist.magic.lightning;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class PiercingJudgement extends Spell {

    public PiercingJudgement(PlayerEntity player, World world) {
        super(player, world);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getName() {
        return "Piercing Judgement";
    }
}
