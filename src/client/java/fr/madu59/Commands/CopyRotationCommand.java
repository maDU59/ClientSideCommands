package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CopyRotationCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("copy-rotation", CopyRotationCommand::CopyRotation);
    }

    public static void CopyRotation(){
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        client.keyboardHandler.setClipboard(player.getYHeadRot() + ", " + player.getXRot());
        player.displayClientMessage(Component.translatable("rotation-copied"), false);
    }
}
