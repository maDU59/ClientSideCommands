package fr.madu59.Commands.GameModeCommands;

import fr.madu59.Commands.GameModeCommands.Util.GameModeUtils;
import fr.madu59.Utils.CommandUtils;

public class GamemodeCreativeCommand {
    public static void register() {
        CommandUtils.registerSimpleLvl2("gmc", () -> GameModeUtils.changeGameMode("creative"));
    }
}
