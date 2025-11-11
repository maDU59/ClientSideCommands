package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class ScreenshotCommand {

    private static Minecraft client = Minecraft.getInstance();
    private static boolean pendingScreenshot = false;
    private static boolean guiVisibility = false;
    private static ChatVisiblity chatVisiblity = ChatVisiblity.FULL;
    
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "ss", ScreenshotCommand::screenshot);
        CommandUtils.registerSimple(event, "ss nogui", ScreenshotCommand::screenshotNoGUI);
    }

    public static void callMe(){
        if (pendingScreenshot) {
            pendingScreenshot = false;

            Screenshot.grab(client.gameDirectory, client.getMainRenderTarget(), message ->
                client.execute(() -> {
                    postScreenshot(message, guiVisibility, chatVisiblity);
                })
            );
        }
    }

    public static void screenshot(){
        guiVisibility = client.options.hideGui;
        chatVisiblity = client.options.chatVisibility().get();
        client.options.chatVisibility().set(ChatVisiblity.HIDDEN);
        pendingScreenshot = true;
    }

    public static void screenshotNoGUI(){
        guiVisibility = client.options.hideGui;
        chatVisiblity = client.options.chatVisibility().get();
        client.options.hideGui = true;
        pendingScreenshot = true;
    }

    private static void postScreenshot(Component message, boolean guiVisibility, ChatVisiblity chatVisiblity) {
        client.options.hideGui = guiVisibility;
        client.options.chatVisibility().set(chatVisiblity);
        CommandUtils.feedbackMessage(message);
    }
}
