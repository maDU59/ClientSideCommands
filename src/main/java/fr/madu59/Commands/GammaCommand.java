package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraft.network.chat.Component;

public class GammaCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerOneArg(event, "set gamma", GammaCommand::setGamma);
    }

    public static void setGamma(String gamma){
        Player player = Minecraft.getInstance().player;
        double value;
        try {
            value = Double.parseDouble(gamma);
        }
        catch (Exception e) {
            player.displayClientMessage(Component.translatable("invalid-value"), false);
            return;
        }
        Minecraft.getInstance().options.gamma().value = value;
        Minecraft.getInstance().options.save();
        CommandUtils.feedbackMessage(Component.translatable("gamma-set", gamma));
    }
}
