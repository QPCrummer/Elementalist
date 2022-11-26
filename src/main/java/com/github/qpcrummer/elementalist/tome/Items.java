package com.github.qpcrummer.elementalist.tome;

import com.github.qpcrummer.elementalist.util.PolyLustUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Items {

    public static final Item tome = tome("tome");

    public static Item tome(String path) {
        return PolyLustUtils.ofModelled(path, net.minecraft.item.Items.ENCHANTED_BOOK, ItemGroup.COMBAT,
                (settings, modelData) -> new Tome(settings.maxCount(1), modelData));
    }

    public static void init() {
    }

    private Items() {
        throw new UnsupportedOperationException();
    }
}
