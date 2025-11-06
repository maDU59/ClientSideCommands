package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;

public class RotationCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("rotation", RotationCommand::WriteRotation);
        CommandUtils.RegisterSimple("facing", RotationCommand::WriteRotation);
    }

    public static void WriteRotation(){
        Player player = Minecraft.getInstance().player;
        String copy = player.getYHeadRot() + ", " + player.getXRot();
        player.displayClientMessage(Component.literal("Facing: " + player.getYHeadRot() + " / " + player.getXRot()).withStyle(
                Style.EMPTY
                    .withHoverEvent(
                        new HoverEvent.ShowText(
                            Component.literal("Click to copy!")
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
