package fr.madu59.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.Events.FrameEvent.FrameEnd;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public abstract class LevelRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private static void afterMain(DeltaTracker deltaTracker, boolean bl, CallbackInfo ci) {
        FrameEnd.EVENT.invoker().onFrameEnd();
	}
}