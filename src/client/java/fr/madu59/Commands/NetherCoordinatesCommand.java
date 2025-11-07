package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class NetherCoordinatesCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("mynetherpos", NetherCoordinatesCommand::WriteNetherCoordinates);
    }

    public static void WriteNetherCoordinates(){
        Player player = Minecraft.getInstance().player;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if(Minecraft.getInstance().level.dimension() != Level.NETHER){
            x /= 8;
            z /= 8;
        }
        String copy = x + ", " + y + ", " + z;
        CommandUtils.FeedbackMessage(Component.literal("XYZ: " + x + " / " + y + "/" + z), copy);
    }
}
