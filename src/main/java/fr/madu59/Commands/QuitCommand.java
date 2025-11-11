package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class QuitCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "quit", QuitCommand::quit);
    }

    public static void quit(){
        Minecraft.getInstance().clearLevel();
    }
}
