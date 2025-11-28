package fr.madu59.Commands.GameModeCommands;

import fr.madu59.Commands.GameModeCommands.Util.GameModeUtils;
import fr.madu59.Utils.CommandUtils;

public class GamemodeSurvivalCommand {
    public static void register() {
        CommandUtils.registerSimpleLvl2("gms", () -> GameModeUtils.changeGameMode("survival"));
    }
}
