package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;

public class DebugCommand {
    public static void Register(){
        CommandUtils.RegisterSimple("toggle-hitboxes", DebugCommand::ToggleHitboxes);
    }

    public static void ToggleHitboxes(){
        Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
    }
}
