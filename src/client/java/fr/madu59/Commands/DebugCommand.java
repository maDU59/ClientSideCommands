package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.resources.ResourceLocation;

public class DebugCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("toggle-hitboxes", DebugCommand::ToggleHitboxes);
        CommandUtils.RegisterSimple("toggle-fps", DebugCommand::ToggleFPS);
        CommandUtils.RegisterSimple("toggle-3Dcrosshair", DebugCommand::Toggle3DCrosshair);
        CommandUtils.RegisterSimple("toggle-debughud", DebugCommand::ToggleF3);
        CommandUtils.RegisterSimple("toggle-coordinates", DebugCommand::TogglePosition);
        CommandUtils.RegisterSimple("toggle-tps", DebugCommand::ToggleTPS);
    }

    public static void ToggleHitboxes(){
        Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
    }

    public static void ToggleFPS(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.FPS, AlternateToggle(DebugScreenEntries.FPS));
    }

    public static void ToggleTPS(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.TPS, AlternateToggle(DebugScreenEntries.TPS));
    }

    public static void TogglePosition(){
        Minecraft.getInstance().debugEntries.setStatus(DebugScreenEntries.PLAYER_POSITION, AlternateToggle(DebugScreenEntries.PLAYER_POSITION));
    }

    public static void Toggle3DCrosshair(){
        Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.THREE_DIMENSIONAL_CROSSHAIR);
    }

    public static void ToggleF3(){
        Minecraft.getInstance().debugEntries.toggleF3Visible();
    }

    public static DebugScreenEntryStatus AlternateToggle(ResourceLocation entry){
        if (Minecraft.getInstance().debugEntries.getStatus(entry) == DebugScreenEntryStatus.ALWAYS_ON) {
            return DebugScreenEntryStatus.IN_F3;
        } else {
            return DebugScreenEntryStatus.ALWAYS_ON;
        }
    }
}
