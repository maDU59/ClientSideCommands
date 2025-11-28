package fr.madu59.Commands.GameModeCommands;

import fr.madu59.Commands.GameModeCommands.Util.GameModeUtils;
import fr.madu59.Utils.CommandUtils;

public class GamemodeSpectatorCommand {
    public static void register() {
        CommandUtils.registerSimpleLvl2("gmspec", () -> GameModeUtils.changeGameMode("spectator"));
    }
}
