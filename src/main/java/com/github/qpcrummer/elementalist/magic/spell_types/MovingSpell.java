package com.github.qpcrummer.elementalist.magic.spell_types;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class MovingSpell extends Spell {

    public MovingSpell(ServerPlayerEntity player, World world, String name, int cooldown, int distance, float speed, float pitch_offset, float yaw_offset) {
        super(player, world, name, cooldown, distance, speed, pitch_offset, yaw_offset);
    }
}
