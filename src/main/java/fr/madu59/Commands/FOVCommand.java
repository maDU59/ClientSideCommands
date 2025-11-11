package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraft.network.chat.Component;

public class FOVCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerOneArg(event, "set fov", FOVCommand::setFOV);
    }

    public static void setFOV(String fov){
        Player player = Minecraft.getInstance().player;
        int value;
        try {
            value = Integer.parseInt(fov);
        }
        catch (Exception e) {
            player.displayClientMessage(Component.translatable("invalid-value"), false);
            return;
        }
        Minecraft.getInstance().options.fov().value = value;
        Minecraft.getInstance().options.save();
        CommandUtils.feedbackMessage(Component.translatable("fov-set", fov));
    }
}
