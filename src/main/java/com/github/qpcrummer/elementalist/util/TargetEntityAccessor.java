package com.github.qpcrummer.elementalist.util;

import com.github.qpcrummer.elementalist.magic.Spell;
import net.minecraft.util.math.Vec3d;

public interface TargetEntityAccessor {
    void setSpell(Spell spell);
    void setStartPos(Vec3d pos);
}
