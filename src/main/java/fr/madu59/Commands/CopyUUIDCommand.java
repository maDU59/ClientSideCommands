package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class CopyUUIDCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "copy myUUID", CopyUUIDCommand::copyUUID);
    }

    public static void copyUUID(){
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        client.keyboardHandler.setClipboard(player.getUUID().toString());
        CommandUtils.feedbackMessage(Component.translatable("uuid-copied"));
    }
}
