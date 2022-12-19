package com.github.qpcrummer.elementalist.tome;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ModelledPolymerItem;
import com.github.qpcrummer.elementalist.util.SpellAccessor;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import static com.github.qpcrummer.elementalist.util.GUI.launchGUI;

public class Tome extends ModelledPolymerItem {
    public static Spell spell;
    public static int currentSpell;
    public Tome(Settings settings, PolymerModelData customModelData) {
        super(settings, customModelData);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (((SpellAccessor)user).getLevel() != 0) {
            if (user.isSneaking()) {
                cycleSpell(user);
            } else {
                if (spell == null) {
                    spell = ((SpellAccessor) user).getSpells().get(currentSpell);
                }
                if (canCast((ServerPlayerEntity) user)) {
                    spell.cast();
                    ((SpellAccessor) user).startCooldowns(currentSpell);
                }
            }
        } else {
            launchGUI((ServerPlayerEntity) user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public static void cycleSpell(PlayerEntity user) {
            if (currentSpell >= ((SpellAccessor) user).getSpells().size() - 1) {
                currentSpell = 0;
            } else {
                currentSpell++;
            }
            spell = ((SpellAccessor)user).getSpells().get(currentSpell);
            ClearTitleS2CPacket clear = new ClearTitleS2CPacket(true);
            OverlayMessageS2CPacket title = new OverlayMessageS2CPacket(Text.literal(spell.getName()));
            ((ServerPlayerEntity)user).networkHandler.sendPacket(clear);
            ((ServerPlayerEntity)user).networkHandler.sendPacket(title);
    }

    public boolean canCast(ServerPlayerEntity player) {
        return ((SpellAccessor)player).getCooldown(currentSpell) == 0;
    }
}
