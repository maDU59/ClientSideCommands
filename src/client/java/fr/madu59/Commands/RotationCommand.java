package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

public class RotationCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("myrot", RotationCommand::WriteRotation);
    }

    public static void WriteRotation(){
        Player player = Minecraft.getInstance().player;
        String copy = player.getYHeadRot() + ", " + player.getXRot();
        CommandUtils.FeedbackMessage(Component.literal("YX: " + player.getYHeadRot() + " / " + player.getXRot()), copy);
    }
}
