package fr.madu59.Commands;

import java.io.IOException;
import java.nio.file.Path;

import com.mojang.blaze3d.platform.NativeImage;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class UpdateWorldIconCommand {

    private static Minecraft client = Minecraft.getInstance();
    private static boolean pendingScreenshot = false;
    private static boolean guiVisibility = false;
    private static long lastCaptureTime = 0;
    
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerSimple(event, "update worldicon", UpdateWorldIconCommand::updateWorldIcon);
        CommandUtils.registerSimple(event, "uw", UpdateWorldIconCommand::updateWorldIcon);
    }

    public static void callMe(){
        if (pendingScreenshot && System.currentTimeMillis() - lastCaptureTime > 1000) {
            pendingScreenshot = false;
            lastCaptureTime = System.currentTimeMillis();

            if(client.getSingleplayerServer() != null && client.isSingleplayer() && client.player != null){
                NativeImage nativeImage = Screenshot.takeScreenshot(client.getMainRenderTarget());
                Path path = client.getSingleplayerServer().getWorldPath(LevelResource.ICON_FILE);

                Util.ioPool().execute(() -> {
                    int i = nativeImage.getWidth();
                    int j = nativeImage.getHeight();
                    int k = 0;
                    int l = 0;
                    if (i > j) {
                        k = (i - j) / 2;
                        i = j;
                    } else {
                        l = (j - i) / 2;
                        j = i;
                    }

                    try (NativeImage resized = new NativeImage(64, 64, false)) {
                        nativeImage.resizeSubRectTo(k, l, i, j, resized);
                        resized.writeToFile(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                        CommandUtils.feedbackMessage(Component.translatable("error"));
                        return;
                    } finally {
                        nativeImage.close();
                    }
                });
                postScreenshot(null, guiVisibility);
            }
        }
    }

    public static void updateWorldIcon(){
        guiVisibility = client.options.hideGui;
        client.options.hideGui = true;
        pendingScreenshot = true;
    }

    private static void postScreenshot(Component message, boolean guiVisibility) {
        client.options.hideGui = guiVisibility;
        CommandUtils.feedbackMessage(Component.translatable("worldicon-updated"));
    }
}
