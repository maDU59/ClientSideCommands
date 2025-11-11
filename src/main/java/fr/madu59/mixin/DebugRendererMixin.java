package fr.madu59.mixin;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.Commands.DebugRendererCommand;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.debug.DebugRenderer.SimpleDebugRenderer;

@Mixin(DebugRenderer.class)
public abstract class DebugRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    public void render(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, double d, double e, double f, CallbackInfo ci) {
        List<SimpleDebugRenderer> list = DebugRendererCommand.GetActiveRenderers();
        Iterator<SimpleDebugRenderer> var14 = list.iterator();

        while(var14.hasNext()) {
            SimpleDebugRenderer simpleDebugRenderer = (SimpleDebugRenderer)var14.next();
            simpleDebugRenderer.render(poseStack, bufferSource, d, e, f);
        }
    }
}