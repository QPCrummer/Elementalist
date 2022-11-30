package com.github.qpcrummer.elementalist.tome;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.ModelledPolymerItem;
import com.github.qpcrummer.elementalist.util.SpellAccessor;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class Tome extends ModelledPolymerItem {
    public static Spell spell;
    public static int currentSpell;
    public Tome(Settings settings, PolymerModelData customModelData) {
        super(settings, customModelData);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            cycleSpell(user);
        } else {
            if (spell == null) {
                spell = ((SpellAccessor)user).getSpells().get(currentSpell);
            }
            PersistentProjectileEntity entity = spell.spawnTrackerEntity();
            spell.castProjectile(entity);
            ((SpellAccessor)user).startCooldowns(currentSpell);
            user.getItemCooldownManager().set(this, spell.getCooldown());
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
}
