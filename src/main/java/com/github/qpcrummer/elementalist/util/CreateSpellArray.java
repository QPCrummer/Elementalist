package com.github.qpcrummer.elementalist.util;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.magic.lightning.DivineRetribution;
import com.github.qpcrummer.elementalist.magic.lightning.PiercingJudgement;
import com.github.qpcrummer.elementalist.magic.lightning.StormyWeb;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CreateSpellArray {
    public static ArrayList<String> elements = new ArrayList<>();
    public static ArrayList<Item> icons = new ArrayList<>();

    public static void populateElementsArray() {
        String[] elementsString = {"light", "darkness", "blood", "poison", "flame", "ice", "wind", "soul", "lightning", "water"};
        Item[] iconItems = {Items.SEA_LANTERN, Items.BLACK_CONCRETE, Items.REDSTONE_BLOCK, Items.SPIDER_EYE, Items.FIRE_CHARGE, Items.ICE, Items.ELYTRA, Items.WITHER_SKELETON_SKULL, Items.LIGHTNING_ROD, Items.WATER_BUCKET};
        elements.addAll(List.of(elementsString));
        icons.addAll(List.of(iconItems));
    }
    public static ArrayList<Spell> elementSpells(String element, ServerPlayerEntity player, World world) {
        ArrayList<Spell> spells = new ArrayList<>();
        switch (element) {
            case "light": {

            }
            case "darkness" : {

            }
            case "blood" : {

            }
            case "poison" : {

            }
            case "flame" : {

            }
            case "ice" : {

            }
            case "wind" : {

            }
            case "soul" : {

            }
            case "lightning" : {
                spells.add(new PiercingJudgement(player, world));
                spells.add(new StormyWeb(player, world));
                spells.add(new DivineRetribution(player, world));
            }
            case "water" : {

            }
            case "time" : {

            }
            case "" : {
            }
        }
        return spells;
    }
    public static void levelUp(int level, ServerPlayerEntity player, World world, String element) {
        ((SpellAccessor)player).setLevel(level);
        ((SpellAccessor)player).getSpells().add(elementSpells(element, player, world).get(level - 1));
        player.sendMessage(Text.literal("Congratulations, you are now a level "+level+ " " + element + " elementalist!"));
    }
}
