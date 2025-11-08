package fr.madu59.Commands;

import com.mojang.realmsclient.RealmsMainScreen;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;

public class QuitCommand {
    public static void register(){
        CommandUtils.registerSimple("quit", QuitCommand::quit);
    }

    public static void quit(){
        //Minecraft.getInstance().disconnect(Component.literal("Quitting world..."));

        Minecraft minecraft = Minecraft.getInstance();

        boolean bl = minecraft.isLocalServer();
        ServerData serverData = minecraft.getCurrentServer();
        if (minecraft.level != null) {
            minecraft.level.disconnect(Component.literal("Quitting world..."));
        }

        if (bl) {
            minecraft.disconnectWithSavingScreen();
        } else {
            minecraft.disconnectWithProgressScreen();
        }

        TitleScreen titleScreen = new TitleScreen();
        if (bl) {
            minecraft.setScreen(titleScreen);
        } else if (serverData != null && serverData.isRealm()) {
            minecraft.setScreen(new RealmsMainScreen(titleScreen));
        } else {
            minecraft.setScreen(new JoinMultiplayerScreen(titleScreen));
        }
    }
}
