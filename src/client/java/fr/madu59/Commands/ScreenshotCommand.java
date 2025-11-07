package fr.madu59.Commands;

import fr.madu59.Events.FrameEvent.FrameEnd;
import fr.madu59.Utils.CommandUtils;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.ChatVisiblity;

public class ScreenshotCommand {

    private static Minecraft client = Minecraft.getInstance();
    private static boolean pendingScreenshot = false;
    private static boolean guiVisibility = false;
    private static ChatVisiblity chatVisiblity = ChatVisiblity.FULL;
    
    public static void register(){
        CommandUtils.registerSimple("ss", ScreenshotCommand::screenshot);
        CommandUtils.registerSimple("ss nogui", ScreenshotCommand::screenshotNoGUI);

        FrameEnd.EVENT.register(() -> {
            if (pendingScreenshot) {
                pendingScreenshot = false;

                Screenshot.grab(client.gameDirectory, client.getMainRenderTarget(), message ->
                    client.execute(() -> {
                        postScreenshot(message, guiVisibility, chatVisiblity);
                    })
                );
            }
        });
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
