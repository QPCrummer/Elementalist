package com.github.qpcrummer.elementalist.util;

import eu.pb4.sgui.api.ClickType;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.elements.GuiElementInterface;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static com.github.qpcrummer.elementalist.util.CreateSpellArray.levelUp;
import static com.github.qpcrummer.elementalist.util.GUI.launchGUI;

public class Command {
    public static void registerCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> dispatcher.register(CommandManager.literal("element")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    launchGUI(player);
                    return 1;
                })
                .then(CommandManager.literal("levelup")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            assert player != null;
                            String elementAsString = ((SpellAccessor)player).getElement();
                            levelUp(player, player.getEntityWorld(), elementAsString);
                            return 1;
                        }))
                .then(CommandManager.literal("reset")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            assert player != null;
                            ((SpellAccessor)player).setLevel(0);
                            ((SpellAccessor)player).setElement("");
                            ((SpellAccessor)player).getSpells().clear();
                            player.sendMessage(Text.literal("Your elemental status has been wiped"));
                            launchGUI(player);
                            return 1;
                        }))));
    }
}
