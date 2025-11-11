package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class CopyPositionCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "copy mypos", CopyPositionCommand::copyPosition);
    }

    public static void copyPosition(){
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        client.keyboardHandler.setClipboard(player.getX() + ", " + player.getY() + ", " + player.getZ());
        CommandUtils.feedbackMessage(Component.translatable("position-copied"));
    }
}
