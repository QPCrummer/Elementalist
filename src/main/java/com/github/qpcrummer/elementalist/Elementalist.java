package com.github.qpcrummer.elementalist;

import net.fabricmc.api.ModInitializer;

import static com.github.qpcrummer.elementalist.tome.Items.init;
import static com.github.qpcrummer.elementalist.util.Command.registerCommand;
import static com.github.qpcrummer.elementalist.util.CreateSpellArray.populateElementsArray;

public class Elementalist implements ModInitializer {
    public static String modid = "elementalist";

    @Override
    public void onInitialize() {
        populateElementsArray();
        registerCommand();
        init();
    }
}
