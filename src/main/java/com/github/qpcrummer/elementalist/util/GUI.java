package com.github.qpcrummer.elementalist.util;

import eu.pb4.sgui.api.ClickType;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.elements.GuiElementInterface;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static com.github.qpcrummer.elementalist.util.CreateSpellArray.levelUp;

public class GUI {

    public static SimpleGui gui;

    public static void launchGUI(ServerPlayerEntity player) {
        gui = new SimpleGui(ScreenHandlerType.GENERIC_9X3, player, false) {
            @Override
            public boolean onClick(int index, ClickType type, SlotActionType action, GuiElementInterface element) {
                String elementAsString = element.getItemStack().getName().getString();
                ((SpellAccessor) player).setElement(elementAsString);
                levelUp(player, player.getEntityWorld(), elementAsString);
                gui.close();
                return super.onClick(index, type, action, element);
            }
        };
        gui.setTitle(Text.literal("Choose Your Element"));
        for (int i = 0; i < CreateSpellArray.elements.size(); i++) {
            gui.setSlot(i, new GuiElementBuilder(CreateSpellArray.icons.get(i)).setCount(1).setName(Text.literal(CreateSpellArray.elements.get(i))));
        }
        gui.open();
    }
}
