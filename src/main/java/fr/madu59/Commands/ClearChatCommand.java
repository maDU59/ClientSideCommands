package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class ClearChatCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "clearchat", ClearChatCommand::clearChat);
    }

    public static void clearChat(){
        Minecraft.getInstance().gui.getChat().clearMessages(false);
    }
}
