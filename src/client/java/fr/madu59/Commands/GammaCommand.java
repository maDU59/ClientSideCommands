package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

public class GammaCommand {
    public static void Register(){
        CommandUtils.RegisterOneArg("set-gamma", GammaCommand::SetGamma);
    }

    public static void SetGamma(String gamma){
        Player player = Minecraft.getInstance().player;
        double value;
        try {
            value = Double.parseDouble(gamma);
        }
        catch (Exception e) {
            player.displayClientMessage(Component.translatable("invalid-gamma"), false);
            return;
        }
        Minecraft.getInstance().options.gamma().value = value;
        player.displayClientMessage(Component.translatable("gamma-set", gamma), false);
    }
}
