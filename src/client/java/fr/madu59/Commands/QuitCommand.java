package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;

public class QuitCommand {
    public static void register(){
        CommandUtils.registerSimple("quit", QuitCommand::quit);
    }

    public static void quit(){
        Minecraft.getInstance().disconnect();
    }
}
