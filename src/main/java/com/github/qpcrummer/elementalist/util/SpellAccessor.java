package com.github.qpcrummer.elementalist.util;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.util.hit.EntityHitResult;

import java.util.ArrayList;

public interface SpellAccessor {
    ArrayList<Spell> getSpells();
    int getLevel();
    void setLevel(int level);
    String getElement();
    void setElement(String element);
    void startCooldowns(int spell);
    int getCooldown(int spell);
}
