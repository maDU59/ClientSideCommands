package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;

public class PositionCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("position", PositionCommand::WritePosition);
    }

    public static void WritePosition(){
        Player player = Minecraft.getInstance().player;
        String copy = player.getX() + ", " + player.getY() + ", " + player.getZ();
        player.displayClientMessage(Component.literal("XYZ: " + player.getX() + " / " + player.getY() + " / " + player.getZ()).withStyle(
                Style.EMPTY
                    .withHoverEvent(
                        new HoverEvent.ShowText(
                            Component.translatable("copy")
                        )
                    )
                    .withClickEvent(
                        new ClickEvent.CopyToClipboard(
                            copy
                        )
                    )
            ), false);
    }
}
