package com.github.qpcrummer.elementalist.util;

import com.github.qpcrummer.elementalist.magic.Spell;

import java.util.ArrayList;

public interface SpellAccessor {
    ArrayList<Spell> getSpells();
    int getLevel();
    void setLevel(int level);
    String getElement();
    void setElement(String element);
    void firstJoin();
    void resetCooldowns(int spell);
    int getCooldown(int spell);
}
