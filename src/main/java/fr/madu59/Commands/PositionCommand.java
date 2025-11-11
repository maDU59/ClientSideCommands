package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraft.network.chat.Component;

public class PositionCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "mypos", PositionCommand::writePosition);
    }

    public static void writePosition(){
        Player player = Minecraft.getInstance().player;
        String copy = player.getX() + ", " + player.getY() + ", " + player.getZ();
        CommandUtils.feedbackMessage(Component.literal("XYZ: " + player.getX() + " / " + player.getY() + " / " + player.getZ()), copy);
    }
}
