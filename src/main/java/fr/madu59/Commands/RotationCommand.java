package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraft.network.chat.Component;

public class RotationCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "myrot", RotationCommand::writeRotation);
    }

    public static void writeRotation(){
        Player player = Minecraft.getInstance().player;
        String copy = player.getYHeadRot() + ", " + player.getXRot();
        CommandUtils.feedbackMessage(Component.literal("YX: " + player.getYHeadRot() + " / " + player.getXRot()), copy);
    }
}
