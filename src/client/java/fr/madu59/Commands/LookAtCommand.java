package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

public class LookAtCommand {
    public static void register(){
        CommandUtils.registerTwoArgsFloat("lookat", LookAtCommand::lookAt);
    }

    public static void lookAt(float yaw, float pitch){
        Player player = Minecraft.getInstance().player;
        player.setYHeadRot(yaw);
        player.setYRot(yaw);
        player.setXRot(pitch);
        CommandUtils.feedbackMessage(Component.literal(I18n.get("rotation-set") + ": " + yaw + "/" + pitch));
    }
}
