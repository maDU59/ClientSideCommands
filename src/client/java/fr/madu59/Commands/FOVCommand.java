package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

public class FOVCommand {
    public static void Register(){
        CommandUtils.RegisterOneArg("set-fov", FOVCommand::SetFOV);
    }

    public static void SetFOV(String fov){
        Player player = Minecraft.getInstance().player;
        int value;
        try {
            value = Integer.parseInt(fov);
        }
        catch (Exception e) {
            player.displayClientMessage(Component.translatable("invalid-fov"), false);
            return;
        }
        Minecraft.getInstance().options.fov().value = value;
        player.displayClientMessage(Component.translatable("fov-set", fov), false);
    }
}
