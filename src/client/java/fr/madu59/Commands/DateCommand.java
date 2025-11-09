package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class DateCommand {
    public static void register(){
        CommandUtils.registerSimple("date", DateCommand::writeDate);
    }

    public static void writeDate(){
        long time = Minecraft.getInstance().level.getDayTime();
        int days = (int)Math.floor(time / 24000);
        int hours = (int)Math.floor((time % 24000) / 1000);
        int minutes = (int)Math.floor((time % 1000) * 60 / 1000);
        CommandUtils.feedbackMessage(Component.translatable("time-value", Integer.toString(days), Integer.toString(hours), Integer.toString(minutes)));
    }
}