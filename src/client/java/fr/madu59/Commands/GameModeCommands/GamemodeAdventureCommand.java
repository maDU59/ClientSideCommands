package fr.madu59.Commands.GameModeCommands;

import fr.madu59.Commands.GameModeCommands.Util.GameModeUtils;
import fr.madu59.Utils.CommandUtils;

public class GamemodeAdventureCommand {
    public static void register() {
        CommandUtils.registerSimpleLvl2("gma", () -> GameModeUtils.changeGameMode("adventure"));
    }
}
