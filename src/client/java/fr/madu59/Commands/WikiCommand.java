package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;

public class WikiCommand {
    public static void register(){
        CommandUtils.registerSimple("wiki", WikiCommand::openWiki);
    }

    public static void openWiki(){
        Item item = Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        Util.getPlatform().openUri("https://minecraft.wiki/w/" + BuiltInRegistries.ITEM.getKey(item).getPath());
    }
}
