package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DebugCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("toggle hitboxes", DebugCommand::ToggleHitboxes);
        CommandUtils.RegisterSimple("toggle fps", DebugCommand::ToggleFPS);
        CommandUtils.RegisterSimple("toggle 3Dcrosshair", DebugCommand::Toggle3DCrosshair);
        CommandUtils.RegisterSimple("toggle f3menu", DebugCommand::ToggleF3);
        CommandUtils.RegisterSimple("toggle coordinates", DebugCommand::TogglePosition);
        CommandUtils.RegisterSimple("toggle tps", DebugCommand::ToggleTPS);
    }

    public static void ToggleHitboxes(){
        Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("entity-hitboxes-display") + " " + (Minecraft.getInstance().debugEntries.getStatus(DebugScreenEntries.ENTITY_HITBOXES) == DebugScreenEntryStatus.ALWAYS_ON ? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static void ToggleFPS(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.FPS, AlternateToggle(DebugScreenEntries.FPS));
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("fps-display") + " " +(Minecraft.getInstance().debugEntries.getStatus(DebugScreenEntries.FPS) == DebugScreenEntryStatus.ALWAYS_ON ? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static void ToggleTPS(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.TPS, AlternateToggle(DebugScreenEntries.TPS));
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("tps-display") + " " +(Minecraft.getInstance().debugEntries.getStatus(DebugScreenEntries.TPS) == DebugScreenEntryStatus.ALWAYS_ON ? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static void TogglePosition(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.PLAYER_POSITION, AlternateToggle(DebugScreenEntries.PLAYER_POSITION));
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("player-pos-display") + " " +(Minecraft.getInstance().debugEntries.getStatus(DebugScreenEntries.PLAYER_POSITION) == DebugScreenEntryStatus.ALWAYS_ON ? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static void Toggle3DCrosshair(){
        Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.THREE_DIMENSIONAL_CROSSHAIR);
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("3D-crosshair") + " " +(Minecraft.getInstance().debugEntries.getStatus(DebugScreenEntries.THREE_DIMENSIONAL_CROSSHAIR) == DebugScreenEntryStatus.ALWAYS_ON ? I18n.get("enabled") : I18n.get("disabled"))));

    }

    public static void ToggleF3(){
        Minecraft.getInstance().debugEntries.toggleF3Visible();
        CommandUtils.FeedbackMessage(Component.literal(I18n.get("F3-menu") + " " +(Minecraft.getInstance().debugEntries.isF3Visible()? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static DebugScreenEntryStatus AlternateToggle(ResourceLocation entry){
        if (Minecraft.getInstance().debugEntries.getStatus(entry) == DebugScreenEntryStatus.ALWAYS_ON) {
            return DebugScreenEntryStatus.IN_F3;
        } else {
            return DebugScreenEntryStatus.ALWAYS_ON;
        }
    }
}
