package fr.madu59.mixin.client;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import fr.madu59.Commands.DebugRendererCommand;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.debug.DebugRenderer.SimpleDebugRenderer;
import net.minecraft.util.debug.DebugValueAccess;

@Mixin(DebugRenderer.class)
public abstract class DebugRendererMixin {
    @Inject(method = "emitGizmos", at = @At("TAIL"))
    public void render(Frustum frustum, double d, double e, double f, float g, CallbackInfo ci, @Local DebugValueAccess debugValueAccess) {
        List<SimpleDebugRenderer> list = DebugRendererCommand.GetActiveRenderers();
        Iterator<SimpleDebugRenderer> var14 = list.iterator();

        while(var14.hasNext()) {
            SimpleDebugRenderer simpleDebugRenderer = (SimpleDebugRenderer)var14.next();
            simpleDebugRenderer.emitGizmos(d, e, f, debugValueAccess, frustum, g);
        }
    }
}