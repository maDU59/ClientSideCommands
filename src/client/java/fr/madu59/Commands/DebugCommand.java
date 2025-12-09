package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.screens.debug.DebugOptionsScreen;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.Identifier;

public class DebugCommand {

    private static Minecraft client = Minecraft.getInstance();

    public static void register(){
        CommandUtils.registerSimple("toggle hitboxes", DebugCommand::toggleHitboxes);
        CommandUtils.registerSimple("toggle fps", DebugCommand::toggleFPS);
        CommandUtils.registerSimple("toggle 3Dcrosshair", DebugCommand::toggle3DCrosshair);
        CommandUtils.registerSimple("toggle f3menu", DebugCommand::toggleF3);
        CommandUtils.registerSimple("f3", DebugCommand::toggleF3);
        CommandUtils.registerSimple("toggle coordinates", DebugCommand::togglePosition);
        CommandUtils.registerSimple("toggle tps", DebugCommand::toggleTPS);
        //CommandUtils.registerSimple("toggle wireframe", DebugCommand::toggleWireframe);
        CommandUtils.registerSimple("toggle fog", DebugCommand::toggleFog);
        CommandUtils.registerSimple("toggle advancedtooltips", DebugCommand::toggleAdvancedTooltips);
        //CommandUtils.registerSimple("toggle debugmenu", DebugCommand::toggleDebugMenu);
    }

    public static void toggleHitboxes(){
        client.debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
        CommandUtils.feedbackMessage(I18n.get("entity-hitboxes-display"), client.debugEntries.getStatus(DebugScreenEntries.ENTITY_HITBOXES) == DebugScreenEntryStatus.ALWAYS_ON);
    }

    public static void toggleFPS(){
        client.debugEntries.setStatus(DebugScreenEntries.FPS, alternateToggle(DebugScreenEntries.FPS));
        CommandUtils.feedbackMessage(I18n.get("fps-display"), client.debugEntries.getStatus(DebugScreenEntries.FPS) == DebugScreenEntryStatus.ALWAYS_ON);
    }

    public static void toggleTPS(){
        client.debugEntries.setStatus(DebugScreenEntries.TPS, alternateToggle(DebugScreenEntries.TPS));
        CommandUtils.feedbackMessage(I18n.get("tps-display"), client.debugEntries.getStatus(DebugScreenEntries.TPS) == DebugScreenEntryStatus.ALWAYS_ON);
    }

    public static void togglePosition(){
        client.debugEntries.setStatus(DebugScreenEntries.PLAYER_POSITION, alternateToggle(DebugScreenEntries.PLAYER_POSITION));
        CommandUtils.feedbackMessage(I18n.get("player-pos-display"), client.debugEntries.getStatus(DebugScreenEntries.PLAYER_POSITION) == DebugScreenEntryStatus.ALWAYS_ON);
    }

    public static void toggle3DCrosshair(){
        client.debugEntries.toggleStatus(DebugScreenEntries.THREE_DIMENSIONAL_CROSSHAIR);
        CommandUtils.feedbackMessage(I18n.get("3D-crosshair"), client.debugEntries.getStatus(DebugScreenEntries.THREE_DIMENSIONAL_CROSSHAIR) == DebugScreenEntryStatus.ALWAYS_ON);

    }

    public static void toggleF3(){
        client.debugEntries.toggleDebugOverlay();
        CommandUtils.feedbackMessage(I18n.get("F3-menu"), client.debugEntries.isOverlayVisible());
    }

    public static void toggleWireframe(){
        client.wireframe = !client.wireframe;
        CommandUtils.feedbackMessage(I18n.get("wireframe"), client.wireframe);
    }

    public static void toggleFog(){
        CommandUtils.feedbackMessage(I18n.get("fog"), FogRenderer.toggleFog());
    }

    public static void toggleAdvancedTooltips(){
        client.options.advancedItemTooltips = !client.options.advancedItemTooltips;
        CommandUtils.feedbackMessage(I18n.get("advanced-tooltips"), client.options.advancedItemTooltips);
    }

    public static void toggleDebugMenu(){
        if (client.screen instanceof DebugOptionsScreen) {
            client.screen.onClose();
            CommandUtils.feedbackMessage(I18n.get("debug-menu"), false);
        } else if (client.canInterruptScreen()) {
            if (client.screen != null) {
                client.screen.onClose();
            }
            client.setScreen(new DebugOptionsScreen());
            CommandUtils.feedbackMessage(I18n.get("debug-menu"), true);
        }
    }

    public static DebugScreenEntryStatus alternateToggle(Identifier entry){
        if (client.debugEntries.getStatus(entry) == DebugScreenEntryStatus.ALWAYS_ON) {
            return DebugScreenEntryStatus.IN_OVERLAY;
        } else {
            return DebugScreenEntryStatus.ALWAYS_ON;
        }
    }
}
