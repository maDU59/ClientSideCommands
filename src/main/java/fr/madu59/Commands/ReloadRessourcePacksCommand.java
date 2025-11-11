package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class ReloadRessourcePacksCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "reload ressourcepacks", ReloadRessourcePacksCommand::reloadRessourcePacks);
        CommandUtils.registerSimple(event, "rr", ReloadRessourcePacksCommand::reloadRessourcePacks);
    }

    public static void reloadRessourcePacks(){
        Minecraft.getInstance().reloadResourcePacks();
        CommandUtils.feedbackMessage(Component.translatable("ressourcepacks-reloaded"));
    }
}
