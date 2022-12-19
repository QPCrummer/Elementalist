package com.github.qpcrummer.elementalist.magic.time;

import com.github.qpcrummer.elementalist.magic.spell_types.StaticSpell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class Relativity extends StaticSpell {
    public Relativity(ServerPlayerEntity player, World world) {
        super(player, world, "Relativity", 6000);
    }

    //This extremely powerful move that removes gravity... yeah. However, if it removes gravity, that means no oxygen so... (: - 1 health per second unless the player has respiration. In that case,
    //only do - (1 - 0.2 * respiration level). This move lasts 30 seconds, but has a massive cooldown of 5 minutes. The user of this spell does not take damage.

}
