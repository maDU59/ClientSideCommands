package fr.madu59.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.debug.BeeDebugRenderer;
import net.minecraft.client.renderer.debug.BrainDebugRenderer;
import net.minecraft.client.renderer.debug.ChunkBorderRenderer;
import net.minecraft.client.renderer.debug.CollisionBoxRenderer;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.debug.GameEventListenerRenderer;
import net.minecraft.client.renderer.debug.DebugRenderer.SimpleDebugRenderer;
import net.minecraft.client.renderer.debug.GoalSelectorDebugRenderer;
import net.minecraft.client.renderer.debug.HeightMapRenderer;
import net.minecraft.client.renderer.debug.LightDebugRenderer;
import net.minecraft.client.renderer.debug.LightSectionDebugRenderer;
import net.minecraft.client.renderer.debug.PathfindingRenderer;
import net.minecraft.client.renderer.debug.RaidDebugRenderer;
import net.minecraft.client.renderer.debug.SolidFaceRenderer;
import net.minecraft.client.renderer.debug.SupportBlockRenderer;
import net.minecraft.client.renderer.debug.WaterDebugRenderer;
import net.minecraft.client.renderer.debug.WorldGenAttemptRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.level.LightLayer;

public class DebugRendererCommand {
    private static final Map<String, DebugRenderer.SimpleDebugRenderer> rendererNames = new HashMap<>();
    private static final Map<String, Boolean> rendererStatus = new HashMap<>();

    public static void addRenderer(DebugRenderer.SimpleDebugRenderer renderer, String name) {
        rendererNames.put(name, renderer);
        rendererStatus.put(name, false);
    }

    public static void register(){
        CreateRenderers();
        List<String> options = rendererNames.keySet().stream().toList();
        CommandUtils.registerOneArg("toggle debugrenderer", options, DebugRendererCommand::ToggleRenderer);
    }

    public static void ToggleRenderer(String name){
        Boolean status = rendererStatus.get(name);
        if(status == null) {
            CommandUtils.feedbackMessage(Component.translatable("invalid-argument"));
            return;
        }
        rendererStatus.put(name, !status);
        CommandUtils.feedbackMessage(Component.literal("Debug Renderer '" + name + "' " + (!status ? I18n.get("enabled") : I18n.get("disabled"))));
    }

    public static List<SimpleDebugRenderer> GetActiveRenderers(){
        List<SimpleDebugRenderer> renderers = new java.util.ArrayList<>();
        for(Map.Entry<String, SimpleDebugRenderer> entry : rendererNames.entrySet()){
            String name = entry.getKey();
            SimpleDebugRenderer renderer = entry.getValue();
            Boolean status = rendererStatus.get(name);
            if(status != null && status){
                renderers.add(renderer);
            }
        }
        return renderers;
    }

    public static void CreateRenderers() {
        Minecraft client = Minecraft.getInstance();
        addRenderer(new PathfindingRenderer(), "pathfinding");
        addRenderer(new WaterDebugRenderer(client), "water");
        addRenderer(new HeightMapRenderer(client), "heightmap");
        addRenderer(new CollisionBoxRenderer(client), "collisionboxes");
        addRenderer(new WorldGenAttemptRenderer(), "worldgenattempts");
        addRenderer(new LightDebugRenderer(client), "lights");
        addRenderer(new LightSectionDebugRenderer(client, LightLayer.SKY), "lightsections");
        addRenderer(new BeeDebugRenderer(client), "bees");
        addRenderer(new GameEventListenerRenderer(client), "gameeventlisteners");
        addRenderer(new RaidDebugRenderer(client), "raids");
        addRenderer(new GoalSelectorDebugRenderer(client), "goalselectors");
        addRenderer(new ChunkBorderRenderer(client), "chunkborders");
        addRenderer(new BrainDebugRenderer(client), "brains");
        addRenderer(new SolidFaceRenderer(client), "solidfaces");
        addRenderer(new SupportBlockRenderer(client), "supportblocks");
    }
}
