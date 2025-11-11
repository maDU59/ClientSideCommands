package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraft.network.chat.Component;

public class NetherCoordinatesCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "mynetherpos", NetherCoordinatesCommand::writeNetherCoordinates);
    }

    public static void writeNetherCoordinates(){
        Player player = Minecraft.getInstance().player;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if(Minecraft.getInstance().level.dimension() != Level.NETHER){
            x /= 8;
            z /= 8;
        }
        String copy = x + ", " + y + ", " + z;
        CommandUtils.feedbackMessage(Component.literal("XYZ: " + x + " / " + y + "/" + z), copy);
    }
}
