package fr.madu59.Commands.GameModeCommands.Util;

import net.minecraft.client.Minecraft;

import java.util.Map;

public class GameModeUtils {
    public static void changeGameMode(String gameMode) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.getConnection() == null) return;
        client.player.connection.sendCommand("gamemode " + gameMode);
    }
}
