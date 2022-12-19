package com.github.qpcrummer.elementalist.magic.spell_types;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class MultishotSpell extends Spell {

    protected int targets;

    public MultishotSpell(ServerPlayerEntity player, World world, String name, int cooldown, int distance, float speed, float pitch_offset, float yaw_offset, int targets) {
        super(player, world, name, cooldown, distance, speed, pitch_offset, yaw_offset);
        this.targets = targets;
    }


}
