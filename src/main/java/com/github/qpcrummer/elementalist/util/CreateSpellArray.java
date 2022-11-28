package com.github.qpcrummer.elementalist.util;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.magic.blood.BloodRain;
import com.github.qpcrummer.elementalist.magic.blood.BloodTransplant;
import com.github.qpcrummer.elementalist.magic.blood.HeartPurloin;
import com.github.qpcrummer.elementalist.magic.dark.DimensionSplittingSlash;
import com.github.qpcrummer.elementalist.magic.dark.SinisterDestruction;
import com.github.qpcrummer.elementalist.magic.dark.VoidAegis;
import com.github.qpcrummer.elementalist.magic.flame.EternalFlame;
import com.github.qpcrummer.elementalist.magic.flame.FlameHurricane;
import com.github.qpcrummer.elementalist.magic.flame.IfritsSphere;
import com.github.qpcrummer.elementalist.magic.ice.BoreasSpears;
import com.github.qpcrummer.elementalist.magic.ice.IceAge;
import com.github.qpcrummer.elementalist.magic.ice.IceGiantsWall;
import com.github.qpcrummer.elementalist.magic.light.ApollosLyre;
import com.github.qpcrummer.elementalist.magic.light.DivineLunge;
import com.github.qpcrummer.elementalist.magic.light.HolySwordBarrage;
import com.github.qpcrummer.elementalist.magic.lightning.DivineRetribution;
import com.github.qpcrummer.elementalist.magic.lightning.PiercingJudgement;
import com.github.qpcrummer.elementalist.magic.lightning.StormyWeb;
import com.github.qpcrummer.elementalist.magic.poison.BlackPlague;
import com.github.qpcrummer.elementalist.magic.poison.PlaguedSufferance;
import com.github.qpcrummer.elementalist.magic.poison.PoisonSpray;
import com.github.qpcrummer.elementalist.magic.soul.SoulSteal;
import com.github.qpcrummer.elementalist.magic.soul.SoulsPain;
import com.github.qpcrummer.elementalist.magic.soul.SoulsWrath;
import com.github.qpcrummer.elementalist.magic.time.OnTheDot;
import com.github.qpcrummer.elementalist.magic.time.Relativity;
import com.github.qpcrummer.elementalist.magic.time.TimeWarp;
import com.github.qpcrummer.elementalist.magic.wind.AirSlashBarrage;
import com.github.qpcrummer.elementalist.magic.wind.SkyFall;
import com.github.qpcrummer.elementalist.magic.wind.WindsweptBeam;
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
                spells.add(new HolySwordBarrage(player, world));
                spells.add(new DivineLunge(player, world));
                spells.add(new ApollosLyre(player, world));
            }
            case "darkness" : {
                spells.add(new DimensionSplittingSlash(player, world));
                spells.add(new VoidAegis(player, world));
                spells.add(new SinisterDestruction(player, world));
            }
            case "blood" : {
                spells.add(new BloodTransplant(player, world));
                spells.add(new HeartPurloin(player, world));
                spells.add(new BloodRain(player, world));
            }
            case "poison" : {
                spells.add(new PlaguedSufferance(player, world));
                spells.add(new PoisonSpray(player, world));
                spells.add(new BlackPlague(player, world));
            }
            case "flame" : {
                spells.add(new IfritsSphere(player, world));
                spells.add(new FlameHurricane(player, world));
                spells.add(new EternalFlame(player, world));
            }
            case "ice" : {
                spells.add(new BoreasSpears(player, world));
                spells.add(new IceGiantsWall(player, world));
                spells.add(new IceAge(player, world));
            }
            case "wind" : {
                spells.add(new WindsweptBeam(player, world));
                spells.add(new AirSlashBarrage(player, world));
                spells.add(new SkyFall(player, world));
            }
            case "soul" : {
                spells.add(new SoulSteal(player, world));
                spells.add(new SoulsPain(player, world));
                spells.add(new SoulsWrath(player, world));
            }
            case "lightning" : {
                spells.add(new PiercingJudgement(player, world));
                spells.add(new StormyWeb(player, world));
                spells.add(new DivineRetribution(player, world));
            }
            case "water" : {
                //Does not exist as of making this
            }
            case "time" : {
                spells.add(new OnTheDot(player, world));
                spells.add(new TimeWarp(player, world));
                spells.add(new Relativity(player, world));
            }
            case "" : {
            }
        }
        return spells;
    }
    public static void levelUp(ServerPlayerEntity player, World world, String element) {
        int level = ((SpellAccessor)player).getLevel()+1;
        ((SpellAccessor)player).setLevel(level);
        ((SpellAccessor)player).getSpells().add(elementSpells(element, player, world).get(level - 1));
        player.sendMessage(Text.literal("Congratulations, you are now a level "+level+ " " + element + " elementalist!"));
    }
}
