package com.github.qpcrummer.elementalist.mixin;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.tome.Items;
import com.github.qpcrummer.elementalist.util.SpellAccessor;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Objects;

import static com.github.qpcrummer.elementalist.tome.Tome.spell;
import static com.github.qpcrummer.elementalist.util.CreateSpellArray.elementSpells;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements SpellAccessor {

    @Shadow public abstract void sendMessage(Text message);

    @Shadow public ServerPlayNetworkHandler networkHandler;
    private static final ArrayList<Spell> spells = new ArrayList<>();
    private static String element = "";
    private static int level;
    private static int cooldown1;
    private static int cooldown2;
    private static int cooldown3;
    private static int cooldown1d;
    private static int cooldown2d;
    private static int cooldown3d;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    @Override
    public ArrayList<Spell> getSpells() {
        return spells;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level1) {
        level = level1;
    }

    @Override
    public String getElement() {
        return element;
    }

    @Override
    public void setElement(String element1) {
        element = element1;
    }

    @Override
    public void firstJoin() {
        ((ServerPlayerEntity)(Object)this).sendMessage(Text.literal("Welcome to your elemental journey. To start, type /element"));
    }

    @Override
    public int getCooldown(int spell) {
        return switch (spell) {
            case 0 -> cooldown1;
            case 1 -> cooldown2;
            case 2 -> cooldown3;
            default -> 0;
        };
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.getInt("elemental_level") != 0 && !Objects.equals(nbt.getString("element"), "")) {
            level = nbt.getInt("elemental_level");
            element = nbt.getString("element");
            level2Spells(level, element);
        } else {
            firstJoin();
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("elemental_level", level);
        nbt.putString("element", element);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (this.isHolding(Items.tome) && spell != null) {
            OverlayMessageS2CPacket msg;
            if (cooldown1 != 0) {
                cooldown1--;
                msg = new OverlayMessageS2CPacket(Text.literal(spell.getName() + " | " + cooldown1));
                this.networkHandler.sendPacket(msg);
            } else if (cooldown2 != 0) {
                cooldown2--;
                msg = new OverlayMessageS2CPacket(Text.literal(spell.getName() + " | " + cooldown2));
                this.networkHandler.sendPacket(msg);
            } else if (cooldown3 != 0) {
                cooldown3--;
                msg = new OverlayMessageS2CPacket(Text.literal(spell.getName() + " | " + cooldown3));
                this.networkHandler.sendPacket(msg);
            } else {
                msg = new OverlayMessageS2CPacket(Text.literal(spell.getName()));
                this.networkHandler.sendPacket(msg);
            }
        }
    }

    private void level2Spells(int level, String element) {
        for (int i = 0; i < level; i++) {
            Spell spell = elementSpells(element, ((ServerPlayerEntity)(Object)this), world).get(i);
            switch (i) {
                case 0 -> cooldown1d = spell.getCooldown();
                case 1 -> cooldown2d = spell.getCooldown();
                case 2 -> cooldown3d = spell.getCooldown();
            }
            spells.add(spell);
        }
    }
    @Override
    public void resetCooldowns(int spell) {
        switch (spell) {
            case 0 -> cooldown1 = cooldown1d;
            case 1 -> cooldown2 = cooldown2d;
            case 2 -> cooldown3 = cooldown3d;
        }
    }
}
