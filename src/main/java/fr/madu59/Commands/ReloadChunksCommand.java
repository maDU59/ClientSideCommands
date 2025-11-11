package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class ReloadChunksCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "reload chunks", ReloadChunksCommand::reloadChunks);
        CommandUtils.registerSimple(event, "rc", ReloadChunksCommand::reloadChunks);
    }

    public static void reloadChunks(){
        Minecraft.getInstance().levelRenderer.allChanged();
        CommandUtils.feedbackMessage(Component.translatable("chunks-reloaded"));
    }
}
