package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;

public class DebugCommand {

    private static Minecraft client = Minecraft.getInstance();

    public static void register(){
        CommandUtils.registerSimple("toggle hitboxes", DebugCommand::toggleHitboxes);
        CommandUtils.registerSimple("toggle f3menu", DebugCommand::toggleF3);
        CommandUtils.registerSimple("f3", DebugCommand::toggleF3);
        CommandUtils.registerSimple("toggle post-effect", DebugCommand::toggleFog);
        CommandUtils.registerSimple("toggle advancedtooltips", DebugCommand::toggleAdvancedTooltips);
        CommandUtils.registerSimple("toggle wireframe", DebugCommand::toggleWireframe);
    }

    public static void toggleHitboxes(){
        boolean bl = !client.getEntityRenderDispatcher().shouldRenderHitBoxes();
		client.getEntityRenderDispatcher().setRenderHitBoxes(bl);
        CommandUtils.feedbackMessage(I18n.get("entity-hitboxes-display"), bl);
    }

    public static void toggleF3(){
        client.options.renderDebug = !client.options.renderDebug;
        CommandUtils.feedbackMessage(I18n.get("F3-menu"), client.options.renderDebug);
    }

    public static void toggleWireframe(){
        client.wireframe = !client.wireframe;
        CommandUtils.feedbackMessage(I18n.get("wireframe"), client.wireframe);
    }

    public static void toggleFog(){
        client.gameRenderer.togglePostEffect();
        CommandUtils.feedbackMessage(I18n.get("post-effect"), client.gameRenderer.effectActive);
    }

    public static void toggleAdvancedTooltips(){
        client.options.advancedItemTooltips = !client.options.advancedItemTooltips;
        CommandUtils.feedbackMessage(I18n.get("advanced-tooltips"), client.options.advancedItemTooltips);
    }
}
